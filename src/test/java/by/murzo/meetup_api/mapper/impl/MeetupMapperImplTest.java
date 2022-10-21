package by.murzo.meetup_api.mapper.impl;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MeetupMapperImplTest {

    MeetupMapperImpl meetupMapper;

    @Autowired
    public MeetupMapperImplTest(MeetupMapperImpl meetupMapper) {
        this.meetupMapper = meetupMapper;
    }

    @Test
    void mapEntityToDto_shouldReturnDto() {
        Meetup meetupEntity = new Meetup(1L, "Topic", "description", "organizer", LocalDateTime.MIN, "location");
        MeetupDto meetupDto = new MeetupDto(1L, "Topic", "description", "organizer", LocalDateTime.MIN, "location");

        assertEquals(meetupDto, meetupMapper.mapEntityToDto(meetupEntity));
    }

    @Test
    void mapDtoToEntity_shouldReturnEntity() {
        Meetup meetupEntity = new Meetup(1L, "Topic", "description", "organizer", LocalDateTime.now(), "location");
        MeetupDto meetupDto = new MeetupDto(1L, "Topic", "description", "organizer", LocalDateTime.now(), "location");

        assertEquals(meetupEntity, meetupMapper.mapDtoToEntity(meetupDto));
    }
}
