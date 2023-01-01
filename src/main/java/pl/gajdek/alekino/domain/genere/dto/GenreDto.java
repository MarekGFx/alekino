package pl.gajdek.alekino.domain.genere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenreDto {
    private Long id;

    @NotNull(message = "Genre name shouldn't be null")
    @NotBlank(message = "Genre name shouldn't be empty")
    private String name;
    @NotBlank(message = "Description shouldn't be empty")
    private String description;

    public GenreDto(){}

    public GenreDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
