package pl.gajdek.alekino.domain.showing;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.repertoire.Repertoire;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeat;
import pl.gajdek.alekino.exceptions.DateTimeInPastException;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Showing  { //implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime;
    private int showHour;
    private int showMin;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private CinemaRoom cinemaRoom;

    @ManyToOne
    @JoinColumn(name = "repertoire_id")
    private Repertoire repertoire;

    @OneToMany(mappedBy = "showing")
    private List<ShowingSeat> seats;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime)  {
        if (startTime.compareTo(LocalDateTime.now()) <= 0){
            throw new DateTimeInPastException("Showing start time cant be in de past");
        }
        this.startTime = startTime;
    }

    public int getShowHour() {
        return startTime.getHour();
    }

    public void setShowHour() {
        this.showHour = startTime.getHour();
    }

    public int getShowMin() {
        return startTime.getMinute();
    }

    public void setShowMin() {
        this.showMin = startTime.getMinute();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

}
