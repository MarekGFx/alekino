package pl.gajdek.alekino.domain.cinemaRoom;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.cinemaRoom.dto.AddSeatToCinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.NewCinemaRoomDto;

@RestController
@AllArgsConstructor
@RequestMapping("CinemaRoom")
public class CinemaRoomController {

    private CinemaRoomService cinemaRoomService;
    private SeatService seatService;

    @GetMapping()
    public ResponseEntity<?> getCinemaRoom(){
        return cinemaRoomService.getCinemaRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCinemaRoom(@PathVariable("id") long id){
        return cinemaRoomService.getCinemaRoom(id);
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<?> getCinemaRoomSeatsList(@PathVariable("id") long id) {
        return seatService.getSeatsByCinemaRoom(id);
    }

    @Transactional
    @PatchMapping()
    @Operation(summary = "Add new cinema room")
    public ResponseEntity<?> addCinemaRoom(@RequestBody @Valid NewCinemaRoomDto newCinemaRoomDto){
        return cinemaRoomService.addNewCinemaRoom(newCinemaRoomDto);
    }

    @Transactional
    @PatchMapping("/{id}/seats")
    @Operation(summary = "Add seat to cinema room")
    public ResponseEntity<?> addSeatToCinemaRoom(@PathVariable("id") long id,
                                                 @RequestBody @Valid AddSeatToCinemaRoomDto seatDto){
        return seatService.addSeatToCinemaRoom(id,seatDto);
    }

    @Transactional
    @PatchMapping("/{cinemaRoomId}/first-seat/{firstSeatId}/second-seat/{secondSeatId}")
    @Operation(summary = "Merge two seats into VIP couch")
    public ResponseEntity<?> mergeSeatsToVipDoubleCouch(long cinemaRoomId, long firstSeatId, long secondSeatId){
        return seatService.setSeatStatus(cinemaRoomId,firstSeatId,secondSeatId);
    }
}
