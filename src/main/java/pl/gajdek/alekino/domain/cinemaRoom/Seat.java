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
    private boolean isBusy; //TODO czemu obiekt? czy prymityw by nie wystarczyl?  // done
    private int positionRow; //TODO walidacja na setterach
    private int positionCol;
    private SeatStatus seatsStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(int positionRow) {
        if (positionRow < 0)
            throw new IllegalArgumentException("Row should be a positive number");
        this.positionRow = positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public void setPositionCol(int positionCol) {
        if (positionCol < 0)
            throw new IllegalArgumentException("Column should be a positive number");
        this.positionCol = positionCol;
    }

    public SeatStatus getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(SeatStatus seatsStatus) {
        if (!seatsStatus.equals(SeatStatus.DISABLED)
                || !seatsStatus.equals(SeatStatus.VIP)
                || !seatsStatus.equals(SeatStatus.NORMAL)){
            System.out.println("There is no such type of seat");
        }
        this.seatsStatus = seatsStatus;
    }
}
