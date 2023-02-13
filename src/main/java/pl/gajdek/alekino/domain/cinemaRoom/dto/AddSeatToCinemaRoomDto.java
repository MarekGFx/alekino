package pl.gajdek.alekino.domain.cinemaRoom.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.enums.SeatStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSeatToCinemaRoomDto {

    private int rowNumber;
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatsStatus;
}
