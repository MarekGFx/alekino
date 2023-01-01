package pl.gajdek.alekino.domain.movie;

import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.domain.genere.GenreRepository;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;

import java.util.List;
import java.util.NoSuchElementException;

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

    public MovieDto getMovie(long id) throws NoSuchElementException {
        return movieRepository.findById(id).map(MovieDtoMapper::map)
                .orElseThrow(() -> new NoSuchElementException("movie not found with id: " + id));
    }

    public List<MovieDto> findAllPremiere() {
        return movieRepository.findAllByPremiereIsTrue().stream()
                .map(MovieDtoMapper::map)
                .toList();
    }

    public List<MovieDto> findMovieByGenreName(String genre) throws NoSuchElementException {
        List<MovieDto> movieList = movieRepository.findAllByGenre_NameIgnoreCase(genre).stream()
                .map(MovieDtoMapper::map)
                .toList();
        if (movieList.size() != 0)
            return movieList;
        else
            throw new NoSuchElementException("Movies with genre: " + genre + " not found");
    }

    public void addMovie(MovieDto movieToSave){
        Movie movie = new Movie();
        movie.setTitle(movieToSave.getTitle());
        movie.setPoster(movieToSave.getPoster());
        Genre genre = genreRepository.findByNameIgnoreCase(movieToSave.getGenre()).orElseThrow(()-> {
            throw new NoSuchElementException("Given genre: " + movieToSave.getGenre() + " does not exist in the database, enter genre first!");
        });
        movie.setGenre(genre);
        movie.setPga(movieToSave.getPga());
        movie.setDescription(movieToSave.getDescription());
        movie.setShortDescription(movieToSave.getShortDescription());
        movie.setReleaseDate(movieToSave.getReleaseDate());
        movie.setRunTimeInMin(movieToSave.getRunTimeInMin());
        movie.setRating(movieToSave.getRating());
        movie.setPremiere(movieToSave.isPremiere());
        movieRepository.save(movie);
    }


}
