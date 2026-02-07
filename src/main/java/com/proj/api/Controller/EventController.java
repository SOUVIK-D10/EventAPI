package com.proj.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.api.DTO.EventDTO;
import com.proj.api.DTO.RegisterDTO;
import com.proj.api.DTO.SearchDTO;
import com.proj.api.Service.EventService;
@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventService service;
    @GetMapping("/find")
    public ResponseEntity<?> getData(
    @RequestBody
    SearchDTO dto,
    @RequestParam(value="page",defaultValue = "0",required = false)
    Integer page,
    @RequestParam(value="size",defaultValue = "10",required = false) 
    Integer size,
    @RequestParam(value="sortby",defaultValue = "id",required = false) 
    String sortby,
    @RequestParam(value="direction",defaultValue = "asc",required = false) 
    String dir
    ){
        if(dto.id()!=null) return new ResponseEntity<>(service.getbyid(dto.id()),HttpStatus.OK);
        Sort sort = null;
        if(size > 20) size=20;
        if(dir.equalsIgnoreCase("desc")) sort = Sort.by(sortby).descending();
        else sort = Sort.by(sortby).ascending();
        return new ResponseEntity<>(service.search(page,size,sort,dto),HttpStatus.OK);
    }
    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody RegisterDTO dto){
        service.register(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/reg")
    public ResponseEntity<?> CancelRegistration(@RequestParam(value = "id",required = true) Integer id){
        service.regCancel(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/event")
    public ResponseEntity<?> newEvent(@RequestBody EventDTO dto){
        service.saveEvent(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/event")
    public ResponseEntity<?> cancelEvent(@RequestParam(value = "id",required = true) Integer id){
        service.cancelEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
