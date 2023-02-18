package pl.gajdek.alekino.domain.genere;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.genere.GenreService;
import pl.gajdek.alekino.domain.genere.dto.GenreDto;
import pl.gajdek.alekino.domain.movie.MovieService;
import pl.gajdek.alekino.exceptions.UniqueDataConstraintException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    public GenreController(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @GetMapping("/genres")
    @Operation(summary = "Get genre list")
    public ResponseEntity<?> getGenres(){
        return genreService.findAllGenres();
    }

    @PostMapping("/add-genre")
    @Operation(summary = "Add genre")
    public ResponseEntity<?> addGenre(@RequestBody @Valid GenreDto genre) {
       return genreService.addGenre(genre);
    }
}
