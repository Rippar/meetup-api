package by.murzo.meetup_api.service.impl;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.exception.MeetupNotFoundException;
import by.murzo.meetup_api.mapper.MeetupMapper;
import by.murzo.meetup_api.repository.MeetupRepository;
import by.murzo.meetup_api.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return meetupMapper.mapEntityListToDtoList(meetups);
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
        meetupRepository.addMeetup(meetup);
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
}
