package pl.gajdek.alekino.domain.cinemaRoom.map;

import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;

public class SeatDtoMapper {

   public static SeatDto mapToSeatDto(Seat seat){
        return new SeatDto(
                seat.getId(),
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getSeatsStatus()
        );
   }

//   public static SeatInfoDto mapToSeatInfoDto(Seat seat){
//       return new SeatInfoDto(
//               seat.getRowNumber(),
//               seat.getSeatNumber(),
//               seat.getSeatNumber(),
//               seat.getCinemaRoom().getRoomNumber()
//       );
//   }
}
