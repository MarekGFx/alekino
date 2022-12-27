package pl.gajdek.alekino.domain.cinemaRoom;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isBusy; //TODO czemu obiekt? czy prymityw by nie wystarczyl?
    private int positionRow; //TODO walidacja na setterach
    private int positionCol;
    private SeatStatus seatsStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    public SeatStatus getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(SeatStatus seatsStatus) {
        this.seatsStatus = seatsStatus;
    }
}
