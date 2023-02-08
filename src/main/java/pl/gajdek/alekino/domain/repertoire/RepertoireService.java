package pl.gajdek.alekino.domain.repertoire;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.repertoire.map.RepertoireMapperDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RepertoireService {

    private RepertoireRepository repertoireRepository;
    private RepertoireMapperDto repertoireMapperDto;

    public ResponseEntity<?> getRepertoires(){
        List<Repertoire> repertoires = repertoireRepository.findAll();
        return ResponseEntity.ok(repertoireMapperDto.mapRepertoireListToDto(repertoires));
    }
}


