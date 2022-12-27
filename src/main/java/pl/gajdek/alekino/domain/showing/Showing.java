package pl.gajdek.alekino.domain.showing;


import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.movie.Movie;

import java.util.Date;


public class Showing {

    private long id;
    private Date date;
    private Movie movie;
    private CinemaRoom cinemaRoom;

    public Showing(long id, Date date, Movie movie, CinemaRoom cinemaRoom) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.cinemaRoom = cinemaRoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
