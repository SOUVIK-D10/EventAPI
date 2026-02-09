package com.proj.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proj.api.DTO.LoginDTO;
import com.proj.api.DTO.TokenDTO;
import com.proj.api.DTO.UserRegisterDTO;
import com.proj.api.Exception.AuthorizationFailureException;
import com.proj.api.Model.RefreshToken;
import com.proj.api.Model.User;
import com.proj.api.Model.UserPrincipals;
import com.proj.api.Repo.RefreshTokenRepo;
import com.proj.api.Repo.UserRepo;
import com.proj.api.Standards.Context;
import com.proj.api.Standards.Role;



@Service
public class UserService implements UserDetailsService {
    private UserRepo db;
    private JWTService jwtService;
    private RefreshTokenService rtService;
    private RefreshTokenRepo db2;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    public UserService(UserRepo db,JWTService jwtService,RefreshTokenRepo db2,RefreshTokenService rtService){
        this.db=db;
        this.db2=db2;
        this.jwtService=jwtService;
        this.rtService=rtService;
    }

    public void register(UserRegisterDTO dto) {
        
    }

    public TokenDTO login(LoginDTO dto) throws AuthorizationFailureException {
        User user = db.findByRollNo(dto.rollNo());
        if(user==null) throw new AuthorizationFailureException("No user found");
        AuthenticationManager manager = Context.get(AuthenticationManager.class);
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getRollNo(), user.getPassword()));
        if(!auth.isAuthenticated()) throw new AuthorizationFailureException("Authentication Failed:Password Mistake");
        if(!user.isAccountActive()) throw new AuthorizationFailureException("Account is Deactivated");
        return generateToken(user.getRollNo(), user.getId());
    }
    private TokenDTO generateToken(String username,int id){
        String accessToken = jwtService.generateToken(username);
        String refreshToken = rtService.generateToken(username);
        db2.save(new RefreshToken(id,refreshToken));
        return new TokenDTO(accessToken, refreshToken);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = db.findByRollNo(email);
        if(user==null) throw new UsernameNotFoundException("Username is not registered in our database");
        return new UserPrincipals(user);
    }
    public TokenDTO refresh(String refreshToken) throws AuthorizationFailureException {
        RefreshToken token = db2.findByToken(refreshToken);
        if(token==null || rtService.isTokenExpired(refreshToken)) throw new AuthorizationFailureException("Invalid Token");
        User user = db.findById(token.getUserId()).get();
        if(user==null || !user.getRollNo().equals(rtService.extractUserName(refreshToken))) throw new AuthorizationFailureException("Invalid User");
        return generateToken(user.getRollNo(), user.getId());
    }
    public void endSession(UserDetails usd) throws AuthorizationFailureException{
        User user = db.findByEmail(usd.getUsername());
        if(user == null) throw new AuthorizationFailureException("Invalid User");
        db2.deleteById(user.getId());
    }
}