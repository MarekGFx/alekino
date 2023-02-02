package pl.gajdek.alekino.domain.cinemaRoom;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.cinemaRoom.dto.AddSeatToCinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("Seats")
public class SeatController {

    private SeatService seatService;

    @Transactional
    @PatchMapping("/{seatId}/showing/{showingId}/users/{userId}")
    @Operation(summary = "Reserve showing seat")
    public ResponseEntity<?> reserveSeat(@PathVariable("seatId") long seatId, @PathVariable("showingId") long showingId,
                                         @PathVariable("userId") long userId){
        return seatService.reserveSeat(seatId,showingId,userId);
    }
}
