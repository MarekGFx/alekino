package pl.gajdek.alekino.domain.cinemaRoom.map;

import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.dto.CinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.NewCinemaRoomDto;

public class CinemaRoomMapper {

    public static CinemaRoomDto map(CinemaRoom cinemaRoom) {
        return new CinemaRoomDto(
                cinemaRoom.getId(),
                cinemaRoom.getRoomNumber(),
                cinemaRoom.getMaxNumberOfSeats(),
                cinemaRoom.getSeats().size()
        );
    }


}
