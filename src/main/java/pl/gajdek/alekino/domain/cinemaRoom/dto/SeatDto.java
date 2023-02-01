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
public class SeatDto {

    private Boolean isBusy;
    private int row;
    private int seatNumber;
    private SeatStatus seatsStatus;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public Boolean getBusy() {
//        return isBusy;
//    }
//
//    public void setBusy(Boolean busy) {
//        isBusy = busy;
//    }
//
//    public SeatStatus getSeatsStatus() {
//        return seatsStatus;
//    }
//
//    public void setSeatsStatus(SeatStatus seatsStatus) {
//        this.seatsStatus = seatsStatus;
//    }
}
