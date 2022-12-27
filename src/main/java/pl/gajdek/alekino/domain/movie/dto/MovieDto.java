package pl.gajdek.alekino.domain.movie.dto;


public class MovieDto {

    private Long id;
    private String title;
    private String poster;
    private String genre;
    private String pga;
    private String description;
    private String shortDescription;
    private Integer releaseYear;
    private Integer runTime;
    private Double rating;
    private boolean premiere;
    public MovieDto(Long id, String title, String poster, String genre,
                    String pga, String description, String shortDescription,
                    Integer releaseYear, Integer runTime, Double rating,
                    boolean premiere) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.genre = genre;
        this.pga = pga;
        this.description = description;
        this.shortDescription = shortDescription;
        this.releaseYear = releaseYear;
        this.runTime = runTime;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
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
