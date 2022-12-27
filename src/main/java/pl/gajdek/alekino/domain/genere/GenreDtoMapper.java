package pl.gajdek.alekino.domain.genere;

import pl.gajdek.alekino.domain.genere.dto.GenreDto;

public class GenreDtoMapper {

    static GenreDto map(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}
