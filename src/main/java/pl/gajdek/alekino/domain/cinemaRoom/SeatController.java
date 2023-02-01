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
    @PatchMapping("/{id}/cinema-room")
    @Operation(summary = "Add seat to cinema room")
    public ResponseEntity<?> addSeatToCinemaRoom(@PathVariable("id") long id,
                                                 @RequestBody @Valid AddSeatToCinemaRoomDto seatDto){
        return seatService.addSeatToCinemaRoom(id,seatDto);
    }

    @Transactional
    @PatchMapping("/{seatId}/showing/{showingId}/users/{userId}")
    @Operation(summary = "Add seat to cinema room")
    public ResponseEntity<?> reserveSeat(@PathVariable("seatId") long seatId, @PathVariable("showingId") long showingId,
                                         @PathVariable("userId") long userId){
        return seatService.reserveSeat(seatId,showingId,userId);
    }
}
