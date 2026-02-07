package com.proj.api.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proj.api.DTO.EventDTO;
import com.proj.api.DTO.EventOverviewDTO;
import com.proj.api.DTO.RegisterDTO;
import com.proj.api.DTO.SearchDTO;
import com.proj.api.Model.Event;
import com.proj.api.Model.Registration;
import com.proj.api.Model.Student;
import com.proj.api.Repo.EventRepo;
import com.proj.api.Repo.RegistrationRepo;
import com.proj.api.Repo.StudentRepo;
@Service
public class EventService {
    private EventRepo db1;
    private RegistrationRepo db2;
    private StudentRepo db3;
    @Autowired
    public EventService(EventRepo db1, RegistrationRepo db2, StudentRepo db3) {
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
    public void register(RegisterDTO dto) {

    Event event = db1.findById(dto.eventId())
            .orElseThrow(() -> new RuntimeException("404"));

    if (event.getSeatRemaining() <= 0)
        throw new RuntimeException("403:0 seet");
    LocalDateTime now = LocalDateTime.now();
    if (event.getRegistrationEnd().isBefore(now)
            || event.getRegistrationStart().isAfter(now))
        throw new RuntimeException("403:expired");
    Student student = db3.findByRollNo(dto.rollNo());
    if (student != null) {
        Registration existing = db2
                .findByStudentIdAndEventId(student.getId(), event.getId());

        if (existing != null && existing.whatStatus())
            throw new RuntimeException("409");

        if (existing != null) {
            existing.setStatus(true);
            db2.save(existing);
        }
    } else {
        student = db3.save(
                new Student(dto.name(), dto.email(), dto.rollNo())
        );
        Registration reg = new Registration(event.getId(), student.getId());
        db2.save(reg);
    }
    event.setSeatRemaining(event.getSeatRemaining() - 1).setRegisteredCount(event.getRegisteredCount()+1);
    db1.save(event);
}

    public void saveEvent(EventDTO dto){
        Event event = new Event(dto.title(), dto.description(), dto.clubName(), dto.eventDate(), dto.registrationStart(),
        dto.registrationEnd(), dto.location(), dto.seatRemaining());
        db1.save(event);
    }
    public void regCancel(int id){
        Registration reg = db2.getReferenceById(id);
        if(reg == null) throw new RuntimeException("400");
        reg.setStatus(false);
        db2.save(reg);
    }
    public void cancelEvent(int id){
        if(!db1.existsById(id)) throw new RuntimeException("400");
        db1.deleteById(id);
        db2.deleteById(id);
        db3.deleteById(id);
    }
    public Event getbyid(Integer id) {
        if(!db1.existsById(id)) throw new RuntimeException("400");
        return db1.getReferenceById(id);
    }
}
