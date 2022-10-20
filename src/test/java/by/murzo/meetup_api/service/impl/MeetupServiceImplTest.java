package by.murzo.meetup_api.service.impl;

import by.murzo.meetup_api.dto.MeetupDto;
import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.exception.MeetupNotFoundException;
import by.murzo.meetup_api.mapper.MeetupMapper;
import by.murzo.meetup_api.repository.MeetupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MeetupServiceImplTest {

    @Mock
    MeetupRepository meetupRepository;

    @Mock
    MeetupMapper meetupMapper;

    @InjectMocks
    MeetupServiceImpl meetupService;

    @Test
    void findAllMeetups_shouldReturnAllExistMeetups() {
        Meetup meetup = new Meetup();
        List<Meetup> meetupList = List.of(meetup);

        when(meetupRepository.getAllMeetups()).thenReturn(meetupList);
        when(meetupMapper.mapEntityToDto(meetup)).thenReturn(new MeetupDto());

        assertEquals(1, meetupService.findAllMeetups().size());
    }

    @Test
    void findMeetupById_shouldReturnMeetupWithExistingId() {
        Long id = 1L;
        Meetup meetup = new Meetup();
        meetup.setId(id);

        MeetupDto meetupDto = new MeetupDto();
        meetupDto.setId(id);

        when(meetupRepository.getById(id)).thenReturn(Optional.of(meetup));
        when(meetupMapper.mapEntityToDto(meetup)).thenReturn(meetupDto);

        assertEquals(meetup.getId(), meetupService.findMeetupById(id).getId());

    }

    @Test
    void findMeetupById_shouldThrowMeetupNotFoundException_whenMeetupNotFound() {
        Long id = 1L;
        when(meetupRepository.getById(id)).thenReturn(Optional.empty());
        assertThrows(MeetupNotFoundException.class, () -> meetupService.findMeetupById(id));

    }


    @Test
    void findFilteredMeetups_shouldReturnMeetupsMatchedTopicFilter() {
        Meetup meetup0 = new Meetup();
        meetup0.setTopic("Topic0");

        MeetupDto meetupDto0 = new MeetupDto();
        meetupDto0.setTopic("Topic0");

        Meetup meetup1 = new Meetup();
        meetup1.setTopic("Topic1");

        MeetupDto meetupDto1 = new MeetupDto();
        meetupDto1.setTopic("Topic1");

        when(meetupRepository.getFilteredMeetups("Topic0", null, null, null)).thenReturn(List.of(meetup0));
        when(meetupMapper.mapEntityToDto(meetup0)).thenReturn(meetupDto0);

        assertEquals(1, meetupService.findFilteredMeetups("Topic0", null, null, null).size());
    }

    @Test
    void findFilteredMeetups_shouldReturnMeetupsSortedByTopic() {
        Meetup meetup0 = new Meetup();
        meetup0.setTopic("Topic0");

        MeetupDto meetupDto0 = new MeetupDto();
        meetupDto0.setTopic("Topic0");

        Meetup meetup1 = new Meetup();
        meetup1.setTopic("Topic1");

        MeetupDto meetupDto1 = new MeetupDto();
        meetupDto1.setTopic("Topic1");

        when(meetupRepository.getFilteredMeetups(null, null, null, "topic")).thenReturn(List.of(meetup1, meetup0));
        when(meetupMapper.mapEntityToDto(meetup0)).thenReturn(meetupDto0);
        when(meetupMapper.mapEntityToDto(meetup1)).thenReturn(meetupDto1);

        assertEquals(meetupDto1.getTopic(),
                meetupService.findFilteredMeetups(null, null, null, "topic").get(0).getTopic());
    }


}
