package pl.gajdek.alekino.domain.cinemaRoom;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.enums.SeatStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatsStatus;

    @ManyToOne
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoom cinemaRoom;

    public SeatStatus getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(SeatStatus seatsStatus){
        this.seatsStatus = seatsStatus;
    }
}
