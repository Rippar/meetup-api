package by.murzo.meetup_api.service.impl;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.exception.IncorrectTimeInputException;
import by.murzo.meetup_api.exception.MeetupNotFoundException;
import by.murzo.meetup_api.mapper.MeetupMapper;
import by.murzo.meetup_api.repository.MeetupRepository;
import by.murzo.meetup_api.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class MeetupServiceImpl implements MeetupService {

    private MeetupRepository meetupRepository;
    private MeetupMapper meetupMapper;

    @Autowired
    public MeetupServiceImpl(MeetupRepository meetupRepository, MeetupMapper meetupMapper) {
        this.meetupRepository = meetupRepository;
        this.meetupMapper = meetupMapper;
    }

    @Override
    public List<MeetupDto> findAllMeetups() {
        List<Meetup> meetups = meetupRepository.getAllMeetups();
        return meetups.stream().map(meetupMapper::mapEntityToDto).toList();
    }

    @Override
    public MeetupDto findMeetupById(Long id) {
        Meetup meetup = meetupRepository.getById(id).orElseThrow(() -> new MeetupNotFoundException("Meetup entity " +
                "with id " + id + " hasn't been found"));

        return meetupMapper.mapEntityToDto(meetup);

    }

    @Override
    public void addMeetup(MeetupDto meetupDto) {
        Meetup meetup = meetupMapper.mapDtoToEntity(meetupDto);
        meetupRepository.saveMeetup(meetup);
    }

    @Override
    @Transactional
    public void updateMeetup(MeetupDto meetupDto) {
        Meetup meetup = meetupMapper.mapDtoToEntity(meetupDto);
        meetupRepository.updateMeetup(meetup);
    }

    @Override
    @Transactional
    public void deleteMeetup(MeetupDto meetupDto) {
        Meetup meetup = meetupMapper.mapDtoToEntity(meetupDto);
        meetupRepository.deleteMeetup(meetup);
    }

    @Override
    @Transactional
    public List<MeetupDto> findFilteredMeetups(String topic, String organizer, String time, String sortedBy) {

        LocalDateTime localDateTime;

        try {
            localDateTime = (time == null ? null : LocalDateTime.parse(time, DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm")));

        } catch (DateTimeParseException e) {
            throw new IncorrectTimeInputException("Time parameter must match yyyy-MM-dd HH:mm pattern", e);
        }


        List<Meetup> meetups = meetupRepository.getFilteredMeetups(topic, organizer, localDateTime, sortedBy);

        return meetups.stream().map(meetupMapper::mapEntityToDto).toList();
    }
}
