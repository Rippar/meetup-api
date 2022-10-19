package by.murzo.meetup_api.repository;

import by.murzo.meetup_api.entity.Meetup;

import java.util.List;
import java.util.Optional;

public interface MeetupRepository {
    List<Meetup> getAllMeetups();

    Optional<Meetup> getById(Long id);

    void addMeetup(Meetup meetup);

    void updateMeetup(Meetup meetup);

    void deleteMeetup(Meetup meetup);
}
