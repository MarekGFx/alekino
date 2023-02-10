package pl.gajdek.alekino.domain.showingSeat;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("seats")
public class ShowingSeatController {

    private ShowingSeatService showingSeatService;

//    @Transactional
//    @PatchMapping("/{seatId}/showing/{showingId}/users/{userId}")
//    @Operation(summary = "Reserve showing seat")
//    public ResponseEntity<?> reserveSeat(@PathVariable("seatId") long seatId, @PathVariable("showingId") long showingId,
//                                         @PathVariable("userId") long userId){
//        return showingSeatRepository.reserveSeat(seatId,showingId,userId);
//    }
    @GetMapping("/showings/{id}")
    @Operation(summary = "Get showing seats list")
    public ResponseEntity<?> getSeatListByShowing(@PathVariable("id") long id) {
        return showingSeatService.getSeatsByShowing(id);
    }
}
