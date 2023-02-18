package pl.gajdek.alekino.domain.genere.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenreDto {

    @NotNull(message = "Genre name shouldn't be null")
    @NotBlank(message = "Genre name shouldn't be empty")
    private String name;
    @NotBlank(message = "Description shouldn't be empty")
    private String description;

    public GenreDto(){}

    public GenreDto(Long id, String name, String description) {
        this.name = name;
        this.description = description;
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
