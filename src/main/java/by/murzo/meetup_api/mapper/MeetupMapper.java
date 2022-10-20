package by.murzo.meetup_api.mapper;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;

import java.util.List;

public interface MeetupMapper {
    MeetupDto mapEntityToDto(Meetup meetup);
    Meetup mapDtoToEntity(MeetupDto meetupDto);


}
