package pl.gajdek.alekino.domain.movie.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


public class MovieDto {

    private Long id;
    @NotNull(message = "Movie title shouldn't be null")
    @NotBlank(message = "Movie title shouldn't be blank")
    private String title;
    @URL(message = "invalid URL")
    private String poster;
    private String genre;

    @NotBlank(message = "PGA shouldn't be blank")
    private String pga;
    @NotBlank(message = "description shouldn't be blank")
    private String description;
    private String shortDescription;
    private Date releaseDate;

//    @Min(value = 1, message = "run time shouldn't be less than 1 min")
//    @Max(value = 400, message = "run time shouldn't be grater than 400 min")
    private int runTimeInMin;
    @Min(value = 0, message = "rating shouldn't be less than 0 score")
    @Max(value = 10, message = "rating shouldn't be grater than 10 score")
    private Double rating;
    private boolean premiere;
    public MovieDto(Long id, String title, String poster, String genre,
                    String pga, String description, String shortDescription,
                    Date releaseDate, int runTimeInMin, Double rating,boolean premiere) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.genre = genre;
        this.pga = pga;
        this.description = description;
        this.shortDescription = shortDescription;
        this.releaseDate = releaseDate;
        this.runTimeInMin = runTimeInMin;
        this.rating = rating;
        this.premiere = premiere;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunTimeInMin() {
        return runTimeInMin;
    }

    public void setRunTime(int runTimeInMin) {
        try {
            if (runTimeInMin <= 0) {
                throw new ArithmeticException("RunTime can not be less than 0");
            }
        }catch (ArithmeticException e){
            System.out.println("czas trwania nie miejszy niż zero");
        }

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
