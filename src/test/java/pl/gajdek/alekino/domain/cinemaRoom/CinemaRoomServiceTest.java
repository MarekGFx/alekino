package pl.gajdek.alekino.domain.cinemaRoom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.gajdek.alekino.domain.cinemaRoom.dto.NewCinemaRoomDto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CinemaRoomServiceTest {

    @Mock
    private CinemaRoomRepository cinemaRoomRepository;

    @InjectMocks
    private CinemaRoomService cinemaRoomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewCinemaRoomTest() {
        NewCinemaRoomDto newCinemaRoomDto = new NewCinemaRoomDto(1, 100);

        ResponseEntity<?> responseEntity = cinemaRoomService.addNewCinemaRoom(newCinemaRoomDto);

        verify(cinemaRoomRepository, times(1)).save(any(CinemaRoom.class));
        assertEquals(201, responseEntity.getStatusCode().value());
        assertTrue( responseEntity.getBody() instanceof CinemaRoom);
        CinemaRoom cinemaRoom = (CinemaRoom) responseEntity.getBody();
        assertEquals(1,cinemaRoom.getRoomNumber());
        assertEquals(100,cinemaRoom.getMaxNumberOfSeats());

    }

    @Test
    void addNewCinemaRoom_shouldReturnBadRequest_whenRoomNumberAlreadyExists() {

        NewCinemaRoomDto newCinemaRoomDto = new NewCinemaRoomDto(1, 100);

        when(cinemaRoomRepository.findAll()).thenReturn(List.of());
        when(cinemaRoomRepository.findByRoomNumber(1)).thenReturn(Optional.of(new CinemaRoom()));

        ResponseEntity<?> responseEntity = cinemaRoomService.addNewCinemaRoom(newCinemaRoomDto);

        assertEquals(400, responseEntity.getStatusCode().value());
        assertEquals("Cinema room with number " + newCinemaRoomDto.getRoomNumber() + " already exist", responseEntity.getBody());

        verify(cinemaRoomRepository, never()).save(any());
    }
}