package pl.gajdek.alekino.domain.movie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.gajdek.alekino.domain.genere.Genre;
import pl.gajdek.alekino.exceptions.NotValidNumberExceptions;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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