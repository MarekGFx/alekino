package pl.gajdek.alekino.domain.showing;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.movie.dto.MovieDto;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.dto.CreateShowDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("showings")
public class ShowingController {

    private ShowingServices showingServices;

    @GetMapping()
    @Operation(summary = "Get all showing list")
    public ResponseEntity<?> getShowings(){
        return showingServices.getListAllShowings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one showing by id")
    public ResponseEntity<?> getShowing(@PathVariable("id") long id){
        return showingServices.getShowing(id);
    }

    @GetMapping("/movies/{id}")
    @Operation(summary = "Get showing list by movie")
    public ResponseEntity<?> getShowingsByMovie(@PathVariable("id") long id){
        return showingServices.getListShowingsByMovie(id);
    }

    @Transactional
    @PatchMapping("/create-show")
    @Operation(summary = "Create new cinema show")
    public ResponseEntity<?> addMovie(@RequestBody @Valid CreateShowDto show){
        return showingServices.createShow(show);
    }
}
