package pl.gajdek.alekino.domain.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.gajdek.alekino.domain.showingSeat.dto.ShowingSeatDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowingDto {

    @DateTimeFormat()
    private LocalDateTime startTime;
    private String hour;
    private String movieTitle;
    private String movieGenre;
    private int cinemaRoomNumber;
    private List<ShowingSeatDto> seatDtoList;

}
