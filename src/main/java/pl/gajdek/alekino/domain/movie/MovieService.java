package pl.gajdek.alekino.domain.movie;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.authUser.AuthUserRepository;
import pl.gajdek.alekino.domain.authUser.AuthUsers;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.domain.genere.GenreRepository;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final AuthUserRepository authUserRepository;


    public ResponseEntity<?> getMovies() {
        return ResponseEntity.ok(movieRepository.findAll().stream().map(MovieDtoMapper::mapToMovieDto).toList());
    }

    public ResponseEntity<?> getMovie(long id)  {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.status(404).body("Movie not found with id: " + id);
        } else {
            return ResponseEntity.ok(movie.map(MovieDtoMapper::mapToMovieDto));
        }
    }

    public ResponseEntity<?> findAllPremiere() {
        return ResponseEntity.ok(movieRepository.findAllByPremiereIsTrue().stream()
                .map(MovieDtoMapper::mapToMovieDto)
                .toList());
    }

    public ResponseEntity<?> findMovieByGenreName(String genre)  {
        List<MovieDto> movieList = movieRepository.findAllByGenre_NameIgnoreCase(genre).stream()
                .map(MovieDtoMapper::mapToMovieDto)
                .toList();
        if (movieList.size() != 0)
            return ResponseEntity.ok(movieList);
        else
            return ResponseEntity.status(404).body("Movies with genre: " + genre + " not found");
    }

    public ResponseEntity<?> addMovie(MovieDto movieToSave){
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setPoster(movieToSave.getPoster());
        Optional<Genre> genre =  genreRepository.findByNameIgnoreCase(movieToSave.getGenre());
        if (genre.isEmpty()) {
            return ResponseEntity.status(404).body("genre: "+ movieToSave.getGenre() + " not found in data base");
        }
        movie.setGenre(genre.get());
        movie.setPga(movieToSave.getPga());
        movie.setDescription(movieToSave.getDescription());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setReleaseDate(movieToSave.getReleaseDate());
        movie.setPremiere(movieToSave.isPremiere());
        movie.setRunTimeInMin(movieToSave.getRunTimeInMin());
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }

    public ResponseEntity<?> rateMovie(long id, int rating, long authUserId){
        if (rating <= 10 && rating >= 1) {
            Optional<Movie> movie = movieRepository.findById(id);
            Optional<AuthUsers> authUsers = authUserRepository.findById(authUserId);
            if (movie.isEmpty()) {
                return ResponseEntity.status(404).body("Movie with Id " + id + " dose not exist");
            } else if (authUsers.isEmpty()) {
                return ResponseEntity.status(404).body("User with Id " + id + " dose not exist");
            } else {
                authUsers.get().rateMovie(movie.get(), rating);
                authUserRepository.save(authUsers.get());
                movieRepository.save(movie.get());
                return ResponseEntity.ok(movie.get().getAverageRating());
            }
        } else {
            return ResponseEntity.status(400).body("Rating should be between 1 and 10");
        }
    }
}
