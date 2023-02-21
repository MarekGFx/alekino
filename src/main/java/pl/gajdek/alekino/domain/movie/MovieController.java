package pl.gajdek.alekino.domain.movie;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    @Operation(summary = "Get movie list")
    public ResponseEntity<?> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by Id")
    public  ResponseEntity<?> getMovie(@PathVariable long id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/premiere")
    @Operation(summary = "Get movie list upon premiere")
    public ResponseEntity<?> getMoviePremiere(){
        return movieService.findAllPremiere();
    }

    @GetMapping("/genre/")
    @Operation(summary = "Get movie list upon genre")
    public ResponseEntity<?> getMoviesByGenre(@RequestParam String name) {
        return movieService.findMovieByGenreName(name);
    }

    @PatchMapping("/add-movie")
    @Operation(summary = "Add new movie")
    public ResponseEntity<?> addMovie(@RequestBody @Valid MovieDto movie) {
       return movieService.addMovie(movie);
    }

    @PatchMapping("/rate-movie/{movieId}/auth-user/{authUserId}")
    @Operation(summary = "Rate movie")
    public ResponseEntity<?> rateMovie(@PathVariable("movieId") long movieId,
                                       @RequestParam int rating,
                                       @PathVariable("authUserId") long authUserId) {
        return  movieService.rateMovie(movieId,rating,authUserId);
    }
}
