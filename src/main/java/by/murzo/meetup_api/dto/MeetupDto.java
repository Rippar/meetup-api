package by.murzo.meetup_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetupDto implements Serializable {


    @Range(min = 0, message = "{id.outofrange}")
    private Long id;

    @NotBlank(message = "{topic.notblank}")
    @Size(min = 5, max = 50, message = "{topic.size}")
    private String topic;

    @NotBlank(message = "{description.notblank}")
    @Size(min = 5, max = 100, message = "{description.size}")
    private String description;

    @NotBlank(message = "{organizer.notblank}")
    @Size(min = 5, max = 50, message = "{organizer.size}")
    private String organizer;

    @NotNull(message = "{date.notnull}")
    private LocalDateTime time;

    @NotBlank(message = "{location.notblank}")
    @Size(min = 5, max = 50, message = "{location.size}")
    private String location;
}
