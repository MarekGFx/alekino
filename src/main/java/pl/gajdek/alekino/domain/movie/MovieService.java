package pl.gajdek.alekino.domain.movie;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.domain.genere.GenreRepository;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.exceptions.DataNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public ResponseEntity<?> getMovies() {
        return ResponseEntity.ok(movieRepository.findAll().stream().map(MovieDtoMapper::mapToMovieDto).toList());
    }

    public ResponseEntity<?> getMovie(long id)  {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.status(404).body("Movie not found with id: " + id);
        } else {
            return ResponseEntity.ok(movie);
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
        movie.setRunTimeInMin(movieToSave.getRunTimeInMin());
        movie.setRating(movieToSave.getRating());
        movie.setPremiere(movieToSave.isPremiere());
        movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }
}
