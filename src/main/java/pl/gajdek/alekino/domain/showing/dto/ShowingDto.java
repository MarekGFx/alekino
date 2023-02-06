package pl.gajdek.alekino.domain.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatInfoDto;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.dto.MovieToTicketInfoDto;
import pl.gajdek.alekino.domain.showingSeat.dto.ShowingSeatDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowingDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;
 //   private double hour;
    private String hour;
    private String movieTitle;
    private String movieGenre;
    private int cinemaRoomNumber;
    private List<ShowingSeatDto> seatDtoList;

}
