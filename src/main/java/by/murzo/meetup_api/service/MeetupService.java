package by.murzo.meetup_api.service;

import by.murzo.meetup_api.dto.MeetupDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetupService {
    List<MeetupDto> findAllMeetups();

    MeetupDto findMeetupById(Long id);

    void addMeetup(MeetupDto meetupDto);

    void updateMeetup(MeetupDto meetupDto);

    void deleteMeetup(MeetupDto meetupDto);

    List<MeetupDto> findFilteredMeetups(String topic, String organizer, LocalDateTime localDateTime, String sortedBy);


}
