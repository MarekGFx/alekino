package pl.gajdek.alekino.domain.movie;


import jakarta.persistence.*;
import pl.gajdek.alekino.domain.genere.Genre;

import java.sql.Date;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String poster;
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
    private String pga;
    private String description;
    private String shortDescription;

   // private int releaseYear;

    private Date releaseDate;

    private int runTimeInMin;
    private Double rating;
    private boolean premiere;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPga() {
        return pga;
    }

    public void setPga(String pga) {
        this.pga = pga;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

//    public int getReleaseYear() {
//        return releaseYear;
//    } //TODO czemu obiekt? nie wystarczy int? moze jakas data?

//    public void setReleaseYear(Integer releaseYear) {
//        this.releaseYear = releaseYear;
//    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunTimeInMin() {
        return runTimeInMin;
    } //TODO czemu Integer? w minutach? sekundach?

    public void setRunTimeInMin(Integer runTimeInMin) {
        this.runTimeInMin = runTimeInMin;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isPremiere() {
        return premiere;
    }

    public void setPremiere(boolean premiere) {
        this.premiere = premiere;
    }
}
