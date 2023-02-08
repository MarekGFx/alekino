package pl.gajdek.alekino.domain.repertoire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.showing.dto.ShowMovieDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepertoireToShownDto {

//    private long id;
    private LocalDate repertoireDate;
    private List<ShowMovieDto> movieShows;
}
