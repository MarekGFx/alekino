package pl.gajdek.alekino.domain.repertoire;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajdek.alekino.domain.repertoire.Repertoire;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("repertoires")
public class RepertoireController {

    private RepertoireService repertoireService;

    @GetMapping
    @Operation(summary = "Get all repertoires list")
    public ResponseEntity<?> getRepertoire(){
        return repertoireService.getRepertoires();
    }
}
