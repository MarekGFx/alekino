package pl.gajdek.alekino.domain.cinemaRoom.dto;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoomDto {

    private long id;
    private int roomNumber;
    private int maxNumberOfSeats;
    private int numberOfSeats;
    private List<SeatDto> seatList;
}
