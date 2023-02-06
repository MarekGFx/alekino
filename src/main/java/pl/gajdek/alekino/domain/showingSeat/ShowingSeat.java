package pl.gajdek.alekino.domain.showingSeat;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.enums.SeatStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShowingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isBusy;
    private int rowNumber;
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatsStatus;
    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
