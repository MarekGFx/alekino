package pl.gajdek.alekino.domain.repertoire;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.repertoire.dto.RepertoireToMainPageDto;
import pl.gajdek.alekino.domain.repertoire.dto.RepertoireToShownDto;
import pl.gajdek.alekino.domain.repertoire.map.RepertoireMapperDto;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepertoireService {

    private RepertoireRepository repertoireRepository;
    private ShowingRepository showingRepository;
    private RepertoireMapperDto repertoireMapperDto;

    public ResponseEntity<?> getRepertoires(){
        List<RepertoireToMainPageDto> repertoires = repertoireRepository.findAll()
                .stream()
                .map(repertoireMapperDto::toDto)
                .toList();
        return ResponseEntity.ok(repertoires);
    }

    public ResponseEntity<?> getRepertorieForCurrentDay(long id){
        Optional<RepertoireToShownDto> repertoire = repertoireRepository.findById(id).map(repertoireMapperDto::mapRepertoireToDto);
        if (repertoire.isPresent()){
            return ResponseEntity.ok(repertoire);
        } else
            return ResponseEntity.status(404).body("Repertoire with Id " + id + " does not exist");
    }

    @Transactional
    public ResponseEntity<?> addShowingToRepertoire(long repertoireId, long showingId){
        Optional<Showing> showing = showingRepository.findById(showingId);
        Optional<Repertoire> repertoire = repertoireRepository.findById(repertoireId);

        if (!repertoire.isPresent()){
            return ResponseEntity.status(404).body("Repertoire with Id " + repertoireId + " does not exist");
        } else if (!showing.isPresent()){
            return ResponseEntity.status(404).body("Showing with Id " + showingId + " does not exist");
        } else if (!repertoire.get().getRepertoireDate().equals(showing.get().getStartTime().toLocalDate())) {
            return ResponseEntity.status(400).body("screening is scheduled with a different date than the existing repertoire");
        } else if (repertoire.get().getShowing().contains(showing.get())){
            return ResponseEntity.status(400).body("screening is already added to this repertoire");
        } else {
            if (repertoire.get().getShowing() != null) {
                repertoire.get().getShowing().add(showing.get());
                showing.get().setRepertoire(repertoire.get());
            } else {
                List<Showing> showings = new ArrayList<>();
                showing.get().setRepertoire(repertoire.get());
                showings.add(showing.get());
                repertoire.get().setShowing(showings);
            }
            showingRepository.save(showing.get());
            repertoireRepository.save(repertoire.get());
            return ResponseEntity.ok("screening added successfully");
        }
    }

    @Transactional
    public ResponseEntity<?> createRepertories(LocalDate date){
        Repertoire repertoire = new Repertoire();
        List<Showing> showingList = showingRepository.findAll();
        List<Showing> showingListToRepertoire = new ArrayList<>();
        if (date.compareTo(LocalDate.now()) <= 0){
            return ResponseEntity.status(400).body("Repertoire date cant be in the past");
        } else if (date == null){
            return ResponseEntity.status(400).body("You have enter a date");
        } else {
            repertoire.setRepertoireDate(date);
            repertoire.setDayOfWeek();
            if (showingList != null && !showingList.isEmpty()) {
                for (Showing showing : showingList) {
                    if (repertoire.getRepertoireDate().equals(showing.getStartTime().toLocalDate())) {
                        showing.setRepertoire(repertoire);
                        showingRepository.save(showing);
                        showingListToRepertoire.add(showing);
                    }
                }
                repertoire.setShowing(showingListToRepertoire);
            }
            repertoireRepository.save(repertoire);
            return ResponseEntity.status(201).body("Repertoire successfully created");
        }
    }


}


