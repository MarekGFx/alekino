package pl.gajdek.alekino.domain.movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.gajdek.alekino.domain.authUser.AuthUserRepository;
import pl.gajdek.alekino.domain.authUser.AuthUsers;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.domain.genere.GenreRepository;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.domain.user.User;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private AuthUserRepository authUserRepository;
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void shouldRateMovieSuccessfully() {
        // given
        long movieId = 1L;
        int rating1 = 5;
        int rating2 = 9;
        long authUserId1 = 2L;
        long userId1 = 2L;
        long authUserId2 = 3L;
        long userId2 = 3L;

        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Test movie");

        User user1 = new User();
        user1.setId(userId1);
        user1.setFirstName("testUser");
        user1.setLastName("userTest");

        AuthUsers authUser1 = new AuthUsers();
        authUser1.setId(authUserId1);
        authUser1.setUser(user1);

        User user2 = new User();
        user2.setId(userId2);
        user2.setFirstName("testUser1");
        user2.setLastName("userTest1");

        AuthUsers authUser2 = new AuthUsers();
        authUser2.setId(authUserId2);
        authUser2.setUser(user2);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(authUserRepository.findById(authUserId1)).thenReturn(Optional.of(authUser1));
        when(authUserRepository.save(any())).thenReturn(authUser1);
        when(authUserRepository.findById(authUserId2)).thenReturn(Optional.of(authUser2));
        when(authUserRepository.save(any())).thenReturn(authUser2);
        when(movieRepository.save(any())).thenReturn(movie);

        // when
        ResponseEntity<?> response1 = movieService.rateMovie(movieId, rating1, authUserId1);

        // then
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(5.0, response1.getBody());
        assertTrue(movie.getRatings().contains(rating1));
        assertEquals(1, movie.getRatings().size());

        ResponseEntity<?> response2 = movieService.rateMovie(movieId, rating2, authUserId2);

        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(7.0, response2.getBody());
        assertEquals(7.0, movie.getAverageRating());
        assertTrue(movie.getRatings().contains(rating2));
        assertEquals(2, movie.getRatings().size());

    }

    @Test
    public void shouldReturnBadRequestWhenRatingIsLessThanOneOrGreaterThanTen() {

        long movieId = 1L;
        int rating1 = 0;
        int rating2 = 11;
        long authUserId1 = 2L;
        long authUserId2 = 3L;

        ResponseEntity<?> response1 = movieService.rateMovie(movieId, rating1, authUserId1);

        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
        assertEquals("Rating should be between 1 and 10", response1.getBody());

        ResponseEntity<?> response2= movieService.rateMovie(movieId, rating2, authUserId2);

        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
        assertEquals("Rating should be between 1 and 10", response2.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenMovieNotFound() {

        long movieId = 1L;
        int rating = 5;
        long authUserId = 2L;

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        ResponseEntity<?> response1 = movieService.rateMovie(movieId, rating, authUserId);

        assertEquals(HttpStatus.NOT_FOUND, response1.getStatusCode());
        assertEquals("Movie with Id " + movieId + " dose not exist", response1.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenAuthUserNotFound() {

        long movieId = 1L;
        int rating = 5;
        long authUserId = 1L;

        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("Test movie");

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(authUserRepository.findById(authUserId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = movieService.rateMovie(movieId, rating, authUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User with Id " + authUserId + " dose not exist", response.getBody());
    }

    @Test
    public void testAddMovie() {

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Test Movie");
        movieDto.setPoster("test_poster.jpg");
        movieDto.setGenre("Drama");
        movieDto.setPga("PG-13");
        movieDto.setDescription("A test movie for testing purposes");
        movieDto.setShortDescription("A short description");
        movieDto.setReleaseDate(LocalDate.of(2022, 1, 1));
        movieDto.setRunTimeInMin(120);
        movieDto.setPremiere(false);

        Genre genre = new Genre();
        genre.setName("Drama");
        Optional<Genre> optionalGenre = Optional.of(genre);

        when(genreRepository.findByNameIgnoreCase("Drama")).thenReturn(optionalGenre);

        ResponseEntity<?> responseEntity = movieService.addMovie(movieDto);

        verify(movieRepository, times(1)).save(any(Movie.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof Movie);
        Movie movie = (Movie) responseEntity.getBody();
        assertEquals("Test Movie", movie.getTitle());
        assertEquals("test_poster.jpg", movie.getPoster());
        assertEquals(genre, movie.getGenre());
        assertEquals("PG-13", movie.getPga());
        assertEquals("A test movie for testing purposes", movie.getDescription());
        assertEquals("A short description", movie.getShortDescription());
        assertEquals(LocalDate.of(2022, 1, 1), movie.getReleaseDate());
        assertEquals(120, movie.getRunTimeInMin());
        assertEquals(false, movie.isPremiere());
    }

}