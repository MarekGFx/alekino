package pl.gajdek.alekino.domain.cinemaRoom.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.enums.SeatStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private long id;
    private int row;
    private int seatNumber;
    private SeatStatus seatStatus;
}
