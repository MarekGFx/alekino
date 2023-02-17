package pl.gajdek.alekino.domain.cinemaRoom.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCinemaRoomDto {

    @NotNull(message = "rom cant be null")
    @Min(value = 1,message = "rom number cant be less than 1")
    @Max(value = 10, message = "rom number cant be greater than 10")
    private int roomNumber;
    @Min(value = 10,message = "Seat number cant be less than 10")
    @Max(value = 200, message = "Seat number cant be greater than 100")
    private int maxNumberOfSeats;
}
