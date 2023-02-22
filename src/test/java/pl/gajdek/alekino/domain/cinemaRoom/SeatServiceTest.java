package pl.gajdek.alekino.domain.cinemaRoom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoomRepository;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.cinemaRoom.SeatRepository;
import pl.gajdek.alekino.domain.cinemaRoom.SeatService;
import pl.gajdek.alekino.domain.cinemaRoom.dto.AddSeatToCinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;
import pl.gajdek.alekino.domain.cinemaRoom.map.SeatDtoMapper;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartRepository;
import pl.gajdek.alekino.domain.ticket.TicketRepository;
import pl.gajdek.alekino.domain.user.UserRepository;
import pl.gajdek.alekino.enums.SeatStatus;

class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;
    @Mock
    private CinemaRoomRepository cinemaRoomRepository;
    @Mock
    private ShowingRepository showingRepository;

    @InjectMocks
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seatService = new SeatService(seatRepository, cinemaRoomRepository, showingRepository);
    }

    @Test
    void getSeatsByCinemaRoom_existingCinemaRoom() {

        long cinemaRoomId = 1L;
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setId(cinemaRoomId);
        List<Seat> seats = Arrays.asList(
                new Seat(1L, 1, 1, SeatStatus.NORMAL, cinemaRoom),
                new Seat(2L, 1, 2, SeatStatus.NORMAL, cinemaRoom)
        );
        cinemaRoom.setSeats(seats);
        Mockito.when(cinemaRoomRepository.findById(cinemaRoomId)).thenReturn(Optional.of(cinemaRoom));

        ResponseEntity<?> responseEntity = seatService.getSeatsByCinemaRoom(cinemaRoomId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<SeatDto> expectedSeats = cinemaRoom.getSeats().stream().map(SeatDtoMapper::mapToSeatDto).toList();
        List<SeatDto> responseSeat = (List<SeatDto>) responseEntity.getBody();
        for (int i = 0; i < expectedSeats.size(); i++) {
            assertEquals(expectedSeats.get(i).getId(), responseSeat.get(i).getId());
            assertEquals(expectedSeats.get(i).getRow(), responseSeat.get(i).getRow());
            assertEquals(expectedSeats.get(i).getSeatNumber(), responseSeat.get(i).getSeatNumber());
            assertEquals(expectedSeats.get(i).getSeatStatus(), responseSeat.get(i).getSeatStatus());
        }

    }

    @Test
    void addSeatToCinemaRoom_existingCinemaRoomAndUniqueSeat() {
        // given
        long cinemaRoomId = 1L;
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setId(cinemaRoomId);
        cinemaRoom.setMaxNumberOfSeats(100);
        Mockito.when(cinemaRoomRepository.findById(cinemaRoomId)).thenReturn(Optional.of(cinemaRoom));
        Mockito.when(seatRepository.save(Mockito.any())).thenReturn(new Seat());

        // when
        AddSeatToCinemaRoomDto addSeatToCinemaRoomDto = new AddSeatToCinemaRoomDto(1, 1, "NORMAL");
        ResponseEntity<?> responseEntity = seatService.addSeatToCinemaRoom(cinemaRoomId, addSeatToCinemaRoomDto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Seat was correctly added", responseEntity.getBody());
        Mockito.verify(seatRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(cinemaRoomRepository, Mockito.times(1)).save(cinemaRoom);

        AddSeatToCinemaRoomDto addSeatToCinemaRoomDto1 = new AddSeatToCinemaRoomDto(1, 1, "NORMAL");
        ResponseEntity<?> responseEntity1 = seatService.addSeatToCinemaRoom(cinemaRoomId, addSeatToCinemaRoomDto1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity1.getStatusCode());
    }

}