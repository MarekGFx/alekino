package pl.gajdek.alekino.domain.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatInfoDto;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.dto.MovieToTicketInfoDto;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowingDto {

    private Date date;
    private double hour;
    private String movieTitle;
    private String movieGenre;
    private int cinemaRoomNumber;
}
