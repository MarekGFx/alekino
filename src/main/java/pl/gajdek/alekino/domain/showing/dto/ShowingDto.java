package pl.gajdek.alekino.domain.showing.dto;

import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatInfoDto;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.dto.MovieToTicketInfoDto;

import java.util.Date;

public class ShowingDto {

    private Date date;
    private double hour;
    private MovieToTicketInfoDto movieInfo;
    private int cinemaRoomNumber;
    private SeatInfoDto seatInfoDto;
}
