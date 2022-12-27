package pl.gajdek.alekino.domain.repertoire;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.movie.Movie;

import java.time.DayOfWeek;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Repertoire {

    private Movie movie;
    private DayOfWeek dayOfWeek;

}
