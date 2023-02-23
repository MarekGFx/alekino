package pl.gajdek.alekino.domain.showing;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoomRepository;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.MovieRepository;
import pl.gajdek.alekino.domain.showing.dto.CreateShowDto;
import pl.gajdek.alekino.domain.showing.map.ShowingMapperDto;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeatRepository;
import pl.gajdek.alekino.domain.showingSeat.map.ShowingSeatDtoMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowingServicesTest {

    @Mock
    private ShowingRepository showingRepository;

    @Mock
    private CinemaRoomRepository cinemaRoomRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ShowingSeatRepository showingSeatRepository;

    @Mock
    private ShowingMapperDto showingMapperDto;

    @Mock
    private ShowingSeatDtoMapper showingSeatDtoMapper;

    @InjectMocks
    private ShowingServices showingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateShow() {

        CreateShowDto showToAdd = new CreateShowDto();
        showToAdd.setMovieId(1L);
        showToAdd.setCinemaRoomId(1L);
        showToAdd.setStartTime(LocalDateTime.of(2023,3,12,13,0,0));

        CreateShowDto showToAdd1 = new CreateShowDto();
        showToAdd1.setMovieId(1L);
        showToAdd1.setCinemaRoomId(1L);
        showToAdd1.setStartTime(LocalDateTime.of(2023,3,12,16, 0,0));

        CreateShowDto showToAdd2 = new CreateShowDto();
        showToAdd2.setMovieId(1L);
        showToAdd2.setCinemaRoomId(1L);
        showToAdd2.setStartTime(LocalDateTime.of(2023,3,12,10, 0,0));

        CreateShowDto showToAdd3 = new CreateShowDto();
        showToAdd3.setMovieId(1L);
        showToAdd3.setCinemaRoomId(1L);
        showToAdd3.setStartTime(LocalDateTime.of(2023,3,12,17, 0,0));

        CreateShowDto showToAdd4 = new CreateShowDto();
        showToAdd4.setMovieId(1L);
        showToAdd4.setCinemaRoomId(1L);
        showToAdd4.setStartTime(LocalDateTime.of(2023,3,12,23, 0,0));

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setRunTimeInMin(120);

        List<Seat> seats = new ArrayList<>();

        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setId(1L);
        cinemaRoom.setSeats(seats);

        List<Showing> showings = new ArrayList<>();
        Showing showToCheckSameHour = new Showing();
        Showing showToCheckSameHour1 = new Showing();
        Showing showToCheckSameHour2 = new Showing();
        Showing showToCheckSameHour3 = new Showing();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(cinemaRoomRepository.findById(1L)).thenReturn(Optional.of(cinemaRoom));
        when(showingRepository.findByCinemaRoomId(1L)).thenReturn(showings);

        ResponseEntity<?> response = showingServices.createShow(showToAdd);
        showToCheckSameHour.setStartTime(showToAdd.getStartTime());
        showings.add(showToCheckSameHour);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Show successful created", response.getBody());

        // show with the same hour
        ResponseEntity<?> response1 = showingServices.createShow(showToAdd);
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());

        //show with hour +3
        ResponseEntity<?> response2 = showingServices.createShow(showToAdd1);
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals("Show successful created", response2.getBody());
        showToCheckSameHour1.setStartTime(showToAdd1.getStartTime());
        showings.add(showToCheckSameHour1);

        //show with hour -3
        ResponseEntity<?> response3 = showingServices.createShow(showToAdd2);
        assertEquals(HttpStatus.CREATED, response3.getStatusCode());
        assertEquals("Show successful created", response3.getBody());
        showToCheckSameHour2.setStartTime(showToAdd2.getStartTime());
        showings.add(showToCheckSameHour2);

    //    show without break time 17.00
        ResponseEntity<?> response4 = showingServices.createShow(showToAdd3);
        assertEquals(HttpStatus.BAD_REQUEST, response4.getStatusCode());
        assertEquals("There should be 10 min. break between shows in the same cinema room", response4.getBody());
        showToCheckSameHour3.setStartTime(showToAdd3.getStartTime());
        showings.add(showToCheckSameHour3);

        //    show after cinema closing 23.00
        ResponseEntity<?> response5 = showingServices.createShow(showToAdd4);
        assertEquals(HttpStatus.BAD_REQUEST, response5.getStatusCode());
        assertEquals("Not possible to add a screening after 11 p.m!", response5.getBody());
    }
}