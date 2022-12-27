package pl.gajdek.alekino.domain.cinemaRoom.dto;

import pl.gajdek.alekino.domain.cinemaRoom.SeatStatus;

public class SeatDto {
    private Long id;
    private Boolean isBusy;
    private int positionRow;
    private int positionCol;
    private SeatStatus seatsStatus;

    public SeatDto(Long id, Boolean isBusy, int positionRow, int positionCol, SeatStatus seatsStatus) {
        this.id = id;
        this.isBusy = isBusy;
        this.positionRow = positionRow;
        this.positionCol = positionCol;
        this.seatsStatus = seatsStatus;
    }

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
