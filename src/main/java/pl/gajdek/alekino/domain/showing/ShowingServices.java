package pl.gajdek.alekino.domain.showing;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;
import pl.gajdek.alekino.domain.showing.map.ShowingMapperDto;

import java.util.List;

@Service
@AllArgsConstructor
public class ShowingServices {

    private ShowingRepository showingRepository;

    public ResponseEntity<?> getListAllShowings(){
        List<ShowingDto> showingDtoList = showingRepository.findAll()
                .stream()
                .map(ShowingMapperDto::mapToShowingDto)
                .toList();
        return ResponseEntity.ok(showingDtoList);
    }

    public ResponseEntity<?> getListShowingsByMovie(long id){
        List<ShowingDto> showingDtoList = showingRepository.findByMovieId(id)
                .stream()
                .map(ShowingMapperDto::mapToShowingDto)
                .toList();
        return ResponseEntity.ok(showingDtoList);
    }

}
