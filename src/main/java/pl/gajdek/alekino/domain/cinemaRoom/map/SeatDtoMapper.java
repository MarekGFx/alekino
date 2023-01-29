package pl.gajdek.alekino.domain.cinemaRoom.map;

import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;

public class SeatDtoMapper {

    static SeatDto map(Seat seat){
        return new SeatDto(
                seat.getId(),
                seat.isBusy(),
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getSeatsStatus()
        );
    }
}
