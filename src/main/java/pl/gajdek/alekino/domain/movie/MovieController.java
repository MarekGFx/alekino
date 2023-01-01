package pl.gajdek.alekino.domain.movie;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.exceptions.MovieListByGenreEmptyExceptions;
import pl.gajdek.alekino.exceptions.MovieNotFoundException;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<MovieDto> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/movies/{id}")
    public MovieDto getMovie(@PathVariable long id) throws MovieNotFoundException {
        return movieService.getMovie(id);
    }

    @GetMapping("/movies/premiere")
    public List<MovieDto> getMoviePremiere(){
        return movieService.findAllPremiere();
    }

    @GetMapping("/movies/genre/{name}")
    public List<MovieDto> getMoviesByGenre(String name) throws MovieListByGenreEmptyExceptions {
        return movieService.findMovieByGenreName(name);
    }

    @PostMapping("/movies/add-movie")
    public void addMovie(@RequestBody @Valid MovieDto movie){
        movieService.addMovie(movie);
    }
}
