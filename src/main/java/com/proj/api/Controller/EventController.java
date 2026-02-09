package com.proj.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        if(dto.id()!=null) return new ResponseEntity<>(8,HttpStatus.OK);
        Sort sort = null;
        if(size > 20) size=20;
        if(dir.equalsIgnoreCase("desc")) sort = Sort.by(sortby).descending();
        else sort = Sort.by(sortby).ascending();
        return new ResponseEntity<>(service.search(page,size,sort,dto),HttpStatus.OK);
    }
}
