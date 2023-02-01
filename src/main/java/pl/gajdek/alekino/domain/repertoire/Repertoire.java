package pl.gajdek.alekino.domain.repertoire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.dto.MovieToRepertoireDto;
import pl.gajdek.alekino.domain.showing.Showing;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Repertoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "repertoire")
    private List<Showing> showing;

    @OneToMany
    private List<Movie> movieInfoToRepertoire;
    private DayOfWeek dayOfWeek;

}
