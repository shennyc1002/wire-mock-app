package com.motel666.usereventservice.controller;

import com.motel666.usereventservice.entity.Event;
import com.motel666.usereventservice.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/events")
public class EventController {

    private UserEventRepository userEventRepository;

    @Autowired
    public EventController(UserEventRepository userEventRepository)
    {
        this.userEventRepository = userEventRepository;
    }

    @PostMapping(value="/create")
    public Event create(@RequestBody Event event)
    {

        event.setEventTime(new Date());
        return userEventRepository.save(event);
    }

    @GetMapping(value="/userid/{userid}")
    public Iterable<Event> findAllEvents(@PathVariable ("userid") String userid)
    {
        return userEventRepository.findByUserId(userid);

    }

    @GetMapping(value="/getbydate/{datefrom}/{dateto}")
    public Iterable<Event> findByDateRange(@PathVariable ("datefrom") String datefrom, @PathVariable("dateto") String dateto)
    {
        try
        {
            Date date1 =new SimpleDateFormat("yyyy-MM-dd").parse(datefrom);
            Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(dateto);
            return userEventRepository.findByDate(date1, date2);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
