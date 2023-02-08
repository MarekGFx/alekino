package pl.gajdek.alekino.domain.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowMovieDto {

    private String movieTitle;
    private List<ShowingHourToRepertoirePageDto> showingHours;

}
