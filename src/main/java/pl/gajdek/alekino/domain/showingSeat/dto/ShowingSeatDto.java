package pl.gajdek.alekino.domain.showingSeat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.enums.SeatStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowingSeatDto {

    private long id;
    private boolean isBusy;
    private int row;
    private int seatNumber;
    private SeatStatus seatStatus;

}