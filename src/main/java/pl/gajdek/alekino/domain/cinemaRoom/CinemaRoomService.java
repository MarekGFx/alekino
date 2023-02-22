package pl.gajdek.alekino.domain.cinemaRoom;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.constantCinemaData.ConstantDataForCinema;
import pl.gajdek.alekino.domain.cinemaRoom.dto.CinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.NewCinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.map.CinemaRoomMapper;
import pl.gajdek.alekino.enums.SeatStatus;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CinemaRoomService {

    private CinemaRoomRepository cinemaRoomRepository;

    public ResponseEntity<List<?>> getCinemaRooms(){
        return  ResponseEntity.ok(cinemaRoomRepository.findAll().stream().map(CinemaRoomMapper::map).toList());
    }

    public ResponseEntity<?> getCinemaRoom(long id){
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(id);
        if (cinemaRoom.isPresent()){
            return ResponseEntity.ok(cinemaRoom.stream().map(CinemaRoomMapper::map));
        } else
            return ResponseEntity.status(404).body("Cinema room with this ID do not exist");
    }

    public ResponseEntity<?> addNewCinemaRoom(NewCinemaRoomDto newCinemaRoomDto){
        if (cinemaRoomRepository.findAll().size() >= ConstantDataForCinema.MAX_CINEMA_SIZE){
            return ResponseEntity.status(400).body("You cant add more rooms, try edit existing ones");
        }
        else if (newCinemaRoomDto != null) {
            CinemaRoom cinemaRoom = new CinemaRoom();
            cinemaRoom.setRoomNumber(newCinemaRoomDto.getRoomNumber());
            if (cinemaRoomRepository.findByRoomNumber(cinemaRoom.getRoomNumber()).isPresent()){
                return ResponseEntity.status(400).body("Cinema room with number " + cinemaRoom.getRoomNumber()
                + " already exist");
            }
            cinemaRoom.setMaxNumberOfSeats(newCinemaRoomDto.getMaxNumberOfSeats());
            cinemaRoomRepository.save(cinemaRoom);
            return ResponseEntity.status(201).body(cinemaRoom);
        }
        else
            return ResponseEntity.status(400).body("Cant add new room");
    }

}
