package pl.gajdek.alekino.domain.cinemaRoom.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.enums.SeatStatus;
import pl.gajdek.alekino.enums.validation.ValidateEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSeatToCinemaRoomDto {

    @NotNull(message = "row cant be null")
    @Min(value = 1,message = "row number cant be less than 1")
    @Max(value = 20, message = "row number cant be greater than 20")
    private int rowNumber;
    @NotNull(message = "seat number  cant be null")
    @Min(value = 1,message = "seat number cant be less than 1")
    @Max(value = 20, message = "seat number cant be greater than 20")
    private int seatNumber;

    @ValidateEnum(enumClass = SeatStatus.class, message = "Invalid seat status: It should be either VIP, DISABLED or NORMAL")
    @JsonProperty("seatsStatus")
    private String seatsStatus;

    public void setSeatsStatus(String seatsStatus) {
        this.seatsStatus = seatsStatus.toUpperCase();
    }

    public void setRowNumber(int rowNumber) {
        if (rowNumber == Integer.parseInt(null)){
            throw new NullPointerException("row number cant be null");
        }
        this.rowNumber = rowNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
