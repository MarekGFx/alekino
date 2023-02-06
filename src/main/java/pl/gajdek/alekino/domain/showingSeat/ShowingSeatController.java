package pl.gajdek.alekino.domain.showingSeat;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("Seats")
public class ShowingSeatController {

    private ShowingSeatRepository showingSeatRepository;

//    @Transactional
//    @PatchMapping("/{seatId}/showing/{showingId}/users/{userId}")
//    @Operation(summary = "Reserve showing seat")
//    public ResponseEntity<?> reserveSeat(@PathVariable("seatId") long seatId, @PathVariable("showingId") long showingId,
//                                         @PathVariable("userId") long userId){
//        return showingSeatRepository.reserveSeat(seatId,showingId,userId);
//    }
}
