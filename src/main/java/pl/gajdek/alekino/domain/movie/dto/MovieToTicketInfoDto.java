package pl.gajdek.alekino.domain.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieToTicketInfoDto {

    @NotNull(message = "Movie title shouldn't be null")
    @NotBlank(message = "Movie title shouldn't be blank")
    private String title;
    @NotNull(message = "Genre shouldn't be null")
    @NotBlank(message = "Genre shouldn't be blank")
    private String genre;
}
