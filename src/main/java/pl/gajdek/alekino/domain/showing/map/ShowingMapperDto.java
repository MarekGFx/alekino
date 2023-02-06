package pl.gajdek.alekino.domain.showing.map;

import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingToListDto;
import pl.gajdek.alekino.domain.showingSeat.map.ShowingSeatDtoMapper;


public class ShowingMapperDto {

    public static ShowingDto mapToShowingDto(Showing showing){
        return new ShowingDto(
                showing.getStartTime(),
            //    showing.getShowHour(),
                showing.getShowHour() + ":" + showing.getShowMin(),
                showing.getMovie().getTitle(),
                showing.getMovie().getGenre().getName(),
                showing.getCinemaRoom().getRoomNumber(),
                showing.getSeats().stream().map(ShowingSeatDtoMapper::mapToShowingSeatDto).toList()
        );
    }

    public static ShowingToListDto mapToShowingListDto(Showing showing){
        return new ShowingToListDto(
                showing.getId(),
                showing.getStartTime(),
          //      showing.getShowHour(),
                showing.getShowHour() + ":" + showing.getShowMin(),
                showing.getMovie().getTitle(),
                showing.getMovie().getGenre().getName(),
                showing.getCinemaRoom().getRoomNumber()
        );
    }
}
