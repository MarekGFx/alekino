package pl.gajdek.alekino.domain.movie;

import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.domain.movie.dto.MovieToTicketInfoDto;

import java.time.LocalDate;

public class MovieDtoMapper {

    static MovieDto mapToMovieDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getPoster(),
                movie.getGenre().getName(),
                movie.getPga(),
                movie.getDescription(),
                movie.getShortDescription(),
                movie.getReleaseDate(),
                movie.getRunTimeInMin(),
                movie.getRating(),
                movie.isPremiere()
        );
    }

    static MovieToTicketInfoDto mapToMovieTicketInfoDto(Movie movie) {
        return new MovieToTicketInfoDto(
                movie.getTitle(),
                movie.getGenre().getName()
        );
    }
}
