package pl.gajdek.alekino.domain.showing;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.showing.Showing;

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

    @PatchMapping("/movies/{id}")
    @Operation(summary = "Get showing list by movie")
    public ResponseEntity<?> getShowingsByMovie(@PathVariable("id") long id){
        return showingServices.getListShowingsByMovie(id);
    }
}
