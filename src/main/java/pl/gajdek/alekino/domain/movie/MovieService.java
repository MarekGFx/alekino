package pl.gajdek.alekino.domain.movie;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.domain.genere.GenreRepository;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.exceptions.MovieListByGenreEmptyExceptions;
import pl.gajdek.alekino.exceptions.MovieNotFoundException;

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

    public List<MovieDto> getMovies() {
        return movieRepository.findAll().stream().map(MovieDtoMapper::map).toList();
    }

    public MovieDto getMovie(long id) throws MovieNotFoundException {
        return movieRepository.findById(id).map(MovieDtoMapper::map)
                .orElseThrow(() -> new MovieNotFoundException("movie not found with id: " + id));
    }

    public List<MovieDto> findAllPremiere() {
        return movieRepository.findAllByPremiereIsTrue().stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public List<MovieDto> findMovieByGenreName(String genre) throws MovieListByGenreEmptyExceptions {
        List<MovieDto> movieList = movieRepository.findAllByGenre_NameIgnoreCase(genre).stream()
                .map(MovieDtoMapper::map)
                .toList();
        if (movieList.size() != 0)
            return movieList;
        else
            throw new MovieListByGenreEmptyExceptions("Movies with genre: " + genre + " not found");
    }

    public void addMovie(MovieDto movieToSave){
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setPoster(movieToSave.getPoster());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow();
        movie.setGenre(genre);
        movie.setPga(movieToSave.getPga());
        movie.setDescription(movieToSave.getDescription());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setReleaseYear(movieToSave.getReleaseYear());
        movie.setRunTime(movieToSave.getRunTime());
        movie.setRating(movieToSave.getRating());
        movie.setPremiere(movieToSave.isPremiere());
        movieRepository.save(movie);
    }


}
