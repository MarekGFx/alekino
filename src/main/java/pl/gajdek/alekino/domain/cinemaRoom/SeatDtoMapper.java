package pl.gajdek.alekino.domain.cinemaRoom;

import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;

public class SeatDtoMapper {

    static SeatDto map(Seat seat){
        return new SeatDto(
                seat.getId(),
                seat.getBusy(),
                seat.getPositionRow(),
                seat.getPositionCol(),
                seat.getSeatsStatus()
        );
    }
}
