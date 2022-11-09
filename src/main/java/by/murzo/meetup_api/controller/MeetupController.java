package by.murzo.meetup_api.controller;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/meetups", produces = "application/json")
public class MeetupController {

    private MeetupService meetupService;

    @Autowired
    public MeetupController(MeetupService meetupService) {
        this.meetupService = meetupService;
    }

    @GetMapping
    public List<MeetupDto> getAllMeetups() {
        return meetupService.findAllMeetups();
    }

    @GetMapping(path = "/{meetupId}")
    public MeetupDto getMeetupById(@PathVariable("meetupId") Long id) {
        return meetupService.findMeetupById(id);
    }

    @PostMapping(consumes = "application/json")
    public void addMeetup(@Validated @RequestBody MeetupDto meetupDto) {
        meetupService.addMeetup(meetupDto);
    }

    @PutMapping(path = "/{meetupId}", consumes = "application/json")
    public void updateMeetup(@PathVariable("meetupId") Long meetupId, @Validated @RequestBody MeetupDto meetupDto) {
        meetupDto.setId(meetupId);
        meetupService.updateMeetup(meetupDto);
    }

    @DeleteMapping(path ="/{meetupId}")
    public void deleteMeetup(@PathVariable("meetupId") Long meetupId, @Validated @RequestBody MeetupDto meetupDto) {
        meetupDto.setId(meetupId);
        meetupService.deleteMeetup(meetupDto);
    }

    @GetMapping(path = "/filters")
    public List<MeetupDto> getFilteredMeetups(@RequestParam(value = "topic", required = false) String topic,
                                              @RequestParam(value = "organizer", required = false) String organizer,
                                              @RequestParam(value = "time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
                                              @RequestParam(value = "orderby", required = false) String orderBy) {

        return meetupService.findFilteredMeetups(topic, organizer, time, orderBy);
    }


}
