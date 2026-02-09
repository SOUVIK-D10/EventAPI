package com.proj.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proj.api.DTO.EventOverviewDTO;
import com.proj.api.DTO.SearchDTO;
import com.proj.api.Repo.EventRepo;
import com.proj.api.Repo.RegistrationRepo;
import com.proj.api.Repo.UserRepo;

@Service
public class EventService {
    private EventRepo db1;
    private RegistrationRepo db2;
    private UserRepo db3;
    @Autowired
    public EventService(EventRepo db1, RegistrationRepo db2, UserRepo db3) {
        this.db1 = db1;
        this.db2 = db2;
        this.db3 = db3;
    }
    public List<EventOverviewDTO> search(int pgno,int size,Sort sort,SearchDTO dto){
        Pageable pageable = PageRequest.of(pgno, size, sort);
        Page<EventOverviewDTO> page = db1.searchEvents(
            dto.id(),
            dto.title(),
            dto.description(),
            dto.clubName(),
            dto.location(),
            dto.eventDate(),
            dto.registrationStart(),
            dto.registrationEnd(),
            dto.isStarted(),
            dto.seatRemaining(),
            pageable
        );
        return page.toList();
    }
}
