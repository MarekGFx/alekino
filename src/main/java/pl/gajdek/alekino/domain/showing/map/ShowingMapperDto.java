package pl.gajdek.alekino.domain.showing.map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;
import pl.gajdek.alekino.domain.showing.dto.ShowMovieDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingHourToRepertoirePageDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingToListDto;
import pl.gajdek.alekino.domain.showingSeat.map.ShowingSeatDtoMapper;
import pl.gajdek.alekino.interfaceMapper.EntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ShowingMapperDto implements EntityMapper<Showing,ShowingDto> {

    private ShowingRepository showingRepository;
    private ShowingSeatDtoMapper showingSeatDtoMapper;



    public ShowingToListDto mapToShowingListDto(Showing showing){
        return new ShowingToListDto(
                showing.getId(),
                showing.getStartTime(),
                showing.getShowHour() + ":" + showing.getShowMin(),
                showing.getMovie().getTitle(),
                showing.getMovie().getGenre().getName(),
                showing.getCinemaRoom().getRoomNumber()
        );
    }

    @Override
    public Showing toEntity(ShowingDto showingDto) {
        return null;
    }

    @Override
    public  ShowingDto toDto(Showing showing) {
        return new ShowingDto(
                showing.getStartTime(),
                showing.getShowHour() + ":" + showing.getShowMin(),
                showing.getMovie().getTitle(),
                showing.getMovie().getGenre().getName(),
                showing.getCinemaRoom().getRoomNumber(),
                showing.getSeats().stream().map(showingSeatDtoMapper::mapToShowingSeatDto).toList()
        );
    }

    public ShowingHourToRepertoirePageDto mapToShowHoursDto(Showing showing){
        return new ShowingHourToRepertoirePageDto(
                showing.getId(),
                showing.getShowHour() + ":" + showing.getShowMin()
        );
    }

    public ShowMovieDto mapToShowMovieDto(Showing showing){

        return new ShowMovieDto(
                showing.getMovie().getTitle(),
                showingRepository.findByMovieId(showing.getId()).stream().map(this::mapToShowHoursDto).toList()
        );
    }
}
