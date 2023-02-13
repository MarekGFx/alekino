package pl.gajdek.alekino.domain.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.genere.Genre;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String title;
    private String poster;
    @ManyToOne
    private Genre genre;
    private String pga;
    @NotBlank
    @Size(max = 10000)
    private String description;
    @NotBlank
    @Size(max = 300)
    private String shortDescription;
    private LocalDate releaseDate;
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

    public void setTitle(String title)  {
            if (title == null || title.equals("")) {
                throw new NullPointerException("Can't be null");
            }
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        System.out.println(releaseDate);
        this.releaseDate = releaseDate;
    }

    public int getRunTimeInMin() {
        return runTimeInMin;
    }

    public void setRunTimeInMin(int runTimeInMin) {
        try {
            if (runTimeInMin <= 1) {
                throw new ArithmeticException("RunTime can not be less than 1 min");
            }
            if (runTimeInMin > 400){
                throw new ArithmeticException("RunTime can not be grater than 400 min");
            }
            this.runTimeInMin = runTimeInMin;
            System.out.println(runTimeInMin);
        }catch (ArithmeticException e){
            System.out.println("Błędny czas trwania filmu");
        }
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
