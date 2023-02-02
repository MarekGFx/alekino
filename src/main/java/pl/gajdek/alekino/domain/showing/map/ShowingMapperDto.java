package pl.gajdek.alekino.domain.showing.map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.MovieDtoMapper;
import pl.gajdek.alekino.domain.movie.dto.MovieToTicketInfoDto;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;


public class ShowingMapperDto {

    public static ShowingDto mapToShowingDto(Showing showing){
        return new ShowingDto(
                showing.getDate(),
                showing.getShowHour(),
                showing.getMovie().getTitle(),
                showing.getMovie().getGenre().getName(),
                showing.getCinemaRoom().getRoomNumber()
        );
    }
}
