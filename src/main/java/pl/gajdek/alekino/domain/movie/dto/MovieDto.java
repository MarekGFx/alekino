package pl.gajdek.alekino.domain.movie.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;


public class MovieDto {

    private Long id;
    @NotNull(message = "Movie title shouldn't be null")
    @NotBlank(message = "Movie title shouldn't be blank")
    private String title;
    private String poster;
  //  @NotFound
    private String genre;
    private String pga;
    private String description;
    private String shortDescription;

 //   private Integer releaseYear;

    private Date releaseDate;

    private int runTimeInMin;
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
   //     this.releaseYear = releaseYear;
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

//    public Integer getReleaseYear() {
//        return releaseYear;
//    }
//
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
    }

    public void setRunTime(int runTimeInMin) {
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
