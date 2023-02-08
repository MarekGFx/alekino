package pl.gajdek.alekino.domain.repertoire.map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.repertoire.Repertoire;
import pl.gajdek.alekino.domain.repertoire.dto.RepertoireToShownDto;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;
import pl.gajdek.alekino.domain.showing.dto.ShowMovieDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingHourToRepertoirePageDto;
import pl.gajdek.alekino.domain.showing.map.ShowingMapperDto;
import pl.gajdek.alekino.interfaceMapper.EntityMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RepertoireMapperDto  {

    public List<RepertoireToShownDto> mapRepertoireListToDto(List<Repertoire> repertoireList) {
        List<RepertoireToShownDto> repertoireToShownDtoList = new ArrayList<>();
        for (Repertoire repertoire : repertoireList) {
            Map<String, List<ShowingHourToRepertoirePageDto>> showingHourMap = new HashMap<>();
            for (Showing showing : repertoire.getShowing()) {
                String movieTitle = showing.getMovie().getTitle();
                ShowingHourToRepertoirePageDto showingHourDto = new ShowingHourToRepertoirePageDto(
                        showing.getId(),
                        showing.getShowHour() + ":" + showing.getShowMin()
                );
                showingHourMap.computeIfAbsent(movieTitle, k -> new ArrayList<>()).add(showingHourDto);
            }

            List<ShowMovieDto> showMovieDtoList = new ArrayList<>();
            for (Map.Entry<String, List<ShowingHourToRepertoirePageDto>> entry : showingHourMap.entrySet()) {
                showMovieDtoList.add(new ShowMovieDto(entry.getKey(), entry.getValue()));
            }

            repertoireToShownDtoList.add(new RepertoireToShownDto(repertoire.getRepertoireDate(), showMovieDtoList));
        }
        return repertoireToShownDtoList;
    }

}
