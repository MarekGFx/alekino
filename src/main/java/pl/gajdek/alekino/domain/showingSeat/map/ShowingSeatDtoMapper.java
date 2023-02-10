package pl.gajdek.alekino.domain.showingSeat.map;

import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.dto.CreateShowDto;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeat;
import pl.gajdek.alekino.domain.showingSeat.dto.ShowingSeatDto;

@Component
public class ShowingSeatDtoMapper {

    public ShowingSeatDto mapToShowingSeatDto(ShowingSeat seat) {
        return new ShowingSeatDto(
                seat.getId(),
                seat.isBusy(),
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getSeatsStatus()
        );
    }

    public ShowingSeatDto mapToShowingSeatDto(Seat seat) {
        return new ShowingSeatDto(
                seat.getId(),
                false,
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getSeatsStatus()
        );
    }

    public ShowingSeat mapFromShowingSeatDto(Seat seat, Showing showing){
        return new ShowingSeat(
                seat.getId(),
                false,
                seat.getRowNumber(),
                seat.getSeatNumber(),
                seat.getSeatsStatus(),
                showing
        );
    }

}