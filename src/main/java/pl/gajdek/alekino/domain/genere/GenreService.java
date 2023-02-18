package pl.gajdek.alekino.domain.genere;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.genere.dto.GenreDto;
import pl.gajdek.alekino.exceptions.UniqueDataConstraintException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<GenreDto> findGenreByName(String name){
        return genreRepository.findByNameIgnoreCase(name)
                .map(GenreDtoMapper::map);
    }

    public ResponseEntity<?> findAllGenres(){
        return ResponseEntity.ok(StreamSupport.stream(genreRepository.findAll().spliterator(), false)
                .map(GenreDtoMapper::map)
                .toList());
    }

    public ResponseEntity<?> addGenre(GenreDto genre) {
        Genre genreToSave = new Genre();
        genreToSave.setName(genre.getName());
        if (findGenreByName(genre.getName()).isPresent()){
            return ResponseEntity.status(400).body("genre: " + genre.getName() + " already exist");
        }
        genreToSave.setDescription(genre.getDescription());
        genreRepository.save(genreToSave);
        return ResponseEntity.ok(genreToSave);
    }
}
