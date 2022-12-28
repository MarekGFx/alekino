package pl.gajdek.alekino.domain.movie;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.MovieService;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
//    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/movies/{id}")
    public MovieDto getMovie(@PathVariable long id){
        return movieService.getMovie(id);
    }

    @GetMapping("/movies/premiere")
    public List<MovieDto> getMoviePremiere(){
        return movieService.findAllPremiere();
    }

    @GetMapping("/movies/genre/{name}")
    public List<MovieDto> getMoviesByGenre(String name){
        return movieService.findMovieByGenreName(name);
    }

    @PostMapping("/movies/add-movie")
    public void addMovie(MovieDto movie){
        movieService.addMovie(movie);
    }
}
