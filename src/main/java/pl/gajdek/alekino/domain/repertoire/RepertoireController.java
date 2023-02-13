package pl.gajdek.alekino.domain.repertoire;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("repertoires")
public class RepertoireController {

    private RepertoireService repertoireService;

    @GetMapping
    @Operation(summary = "Get all repertoires list")
    public ResponseEntity<?> getRepertoires(){
        return repertoireService.getRepertoires();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get repertoire and his showing list")
    public ResponseEntity<?> getRepertoire(@PathVariable("id") long id){
        return repertoireService.getRepertorieForCurrentDay(id);
    }

    @PatchMapping("/{repertoireId}/showings/{showingId}")
    @Operation(summary = "Add showing to repertoire")
    public ResponseEntity<?> addShowingToRepertoire(long repertoireId, long showingId){
        return repertoireService.addShowingToRepertoire(repertoireId,showingId);
    }

    @PatchMapping("/{date}/create")
    @Operation(summary = "Create repertoire with existing show with the same date")
    public ResponseEntity<?> createRepertoire(@RequestParam("date") LocalDate date){
        return repertoireService.createRepertories(date);
    }
}
