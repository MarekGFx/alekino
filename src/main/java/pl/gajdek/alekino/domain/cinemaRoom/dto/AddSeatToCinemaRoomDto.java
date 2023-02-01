package pl.gajdek.alekino.domain.cinemaRoom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.cinemaRoom.SeatStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSeatToCinemaRoomDto {

    private int rowNumber;
    private int seatNumber;
    private SeatStatus seatsStatus;
}
