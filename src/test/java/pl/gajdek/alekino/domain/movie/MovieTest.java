package pl.gajdek.alekino.domain.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.exceptions.NotValidNumberExceptions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieTest {

    Genre genre = new Genre();
    Movie movie = new Movie();

    @BeforeAll
    public void init() {

        genre.setName("DRAMA");
        genre.setDescription("opis");
        genre.setId(1L);

        movie.setId(1L);
        movie.setTitle("Rambo");
        movie.setPoster("http://dummyimage.com/");
        movie.setShortDescription("krótkiOpis");
        movie.setDescription("opis");
        movie.setPga("pga-18");
        movie.setGenre(genre);
        movie.setReleaseDate(LocalDate.parse("2002-04-04"));
        movie.setRunTimeInMin(120);
    }

//    @Mock
//    private List<Integer> ratings;
//
//    @Before
//    public void setup() {
//        when(ratings.isEmpty()).thenReturn(false);
//        when(ratings.size()).thenReturn(3);
//        when(ratings.get(0)).thenReturn(5);
//        when(ratings.get(1)).thenReturn(7);
//        when(ratings.get(2)).thenReturn(9);
//    }

    @Test
    public void testAddRating() {

        movie.addRating(1);
        assertEquals(1, movie.getRatings().size());

        movie.addRating(10);
        assertEquals(2, movie.getRatings().size());
        Assertions.assertEquals(5.5, movie.getAverageRating());
    }

    @Test
    public void testAddRatingThrowsException() {

        assertThrows(NotValidNumberExceptions.class,() -> movie.addRating(0));
        assertThrows(NotValidNumberExceptions.class,() -> movie.addRating(11));
    }

}

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class MovieTest {
//
//    @BeforeAll
//    void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void addRating(){
//        Movie movie = Mockito.mock(Movie.class);
//        movie.addRating(6);
//        assertEquals(1.0,movie.getMovieRating());
//    }
//}