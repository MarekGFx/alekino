package pl.gajdek.alekino.domain.authUser;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.user.User;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
