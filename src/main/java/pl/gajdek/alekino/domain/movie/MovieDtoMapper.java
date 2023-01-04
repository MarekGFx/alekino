package pl.gajdek.alekino.domain.movie;

import pl.gajdek.alekino.domain.movie.dto.MovieDto;

public class MovieDtoMapper {

    static MovieDto map(Movie movie) {
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
}
