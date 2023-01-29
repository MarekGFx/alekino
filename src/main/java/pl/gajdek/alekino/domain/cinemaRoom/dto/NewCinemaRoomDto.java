package pl.gajdek.alekino.domain.cinemaRoom.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCinemaRoomDto {

    private int roomNumber;
    private int maxNumberOfSeats;
}
