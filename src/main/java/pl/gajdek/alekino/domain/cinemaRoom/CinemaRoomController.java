package pl.gajdek.alekino.domain.cinemaRoom;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.dto.NewCinemaRoomDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("CinemaRoom")
public class CinemaRoomController {

    private CinemaRoomService cinemaRoomService;

    @GetMapping()
    public ResponseEntity<?> getCinemaRoom(){
        return cinemaRoomService.getCinemaRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCinemaRoom(@PathVariable("id") long id){
        return cinemaRoomService.getCinemaRoom(id);
    }

    @Transactional
    @PatchMapping()
    @Operation(summary = "Add new cinema room")
    public ResponseEntity<?> addCinemaRoom(@RequestBody @Valid NewCinemaRoomDto newCinemaRoomDto){
        return cinemaRoomService.addNewCinemaRoom(newCinemaRoomDto);
    }


}
