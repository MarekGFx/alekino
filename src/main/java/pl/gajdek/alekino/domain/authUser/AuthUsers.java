package pl.gajdek.alekino.domain.authUser;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.exceptions.UniqueDataConstraintException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AuthUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;

    @ManyToMany
    private List<Movie> favoriteMovies;
    @OneToOne(optional = false)
    @NotNull
    private User user;
    @ElementCollection
    @CollectionTable(name = "user_ratings")
    @MapKeyJoinColumn(name = "movie_id")
  //  @Column(name = "rating")
    private Map<Movie, Integer> movieRatings = new HashMap<>();

    public boolean hasRatedMovie(Movie movie) {
        return movieRatings.containsKey(movie);
    }

    public void rateMovie(Movie movie, int rating) {
        if (hasRatedMovie(movie)) {
            throw new UniqueDataConstraintException("User has already rated this movie");
        }
        movie.addRating(rating);
        movieRatings.put(movie, rating);
    }

}
