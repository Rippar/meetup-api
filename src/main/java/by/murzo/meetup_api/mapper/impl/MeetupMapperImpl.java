package by.murzo.meetup_api.mapper.impl;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.mapper.MeetupMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeetupMapperImpl implements MeetupMapper {

    @Override
    public MeetupDto mapEntityToDto(Meetup meetup) {
        return new MeetupDto(meetup.getId(), meetup.getTopic(), meetup.getDescription(), meetup.getOrganizer(),
                meetup.getTime(), meetup.getLocation());
    }

    @Override
    public Meetup mapDtoToEntity(MeetupDto meetupDto) {
        return new Meetup(meetupDto.getId(), meetupDto.getTopic(), meetupDto.getDescription(), meetupDto.getOrganizer(),
                meetupDto.getTime(), meetupDto.getLocation());
    }
}
