package pl.gajdek.alekino.domain.genere;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.genere.GenreService;
import pl.gajdek.alekino.domain.genere.dto.GenreDto;
import pl.gajdek.alekino.domain.movie.MovieService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    public GenreController(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @GetMapping("/genres")
    public List<GenreDto> getGenres(){
        return genreService.findAllGenres();
    }

    @PostMapping("/genres/add-genre")
    public void addGenre(GenreDto genre){
        genreService.addGenre(genre);
    }
}
