package pl.gajdek.alekino.domain.cinemaRoom.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfoDto {

    private int positionRow;
    private int positionCol;
    private int seatNumber; //Todo add to base
}
