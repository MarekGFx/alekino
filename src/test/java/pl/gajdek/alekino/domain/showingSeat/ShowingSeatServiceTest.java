package pl.gajdek.alekino.domain.showingSeat;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoomRepository;
import pl.gajdek.alekino.domain.cinemaRoom.SeatRepository;
import pl.gajdek.alekino.domain.cinemaRoom.SeatService;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.MovieRepository;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartRepository;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartService;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.TicketRepository;
import pl.gajdek.alekino.domain.ticket.TicketServices;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.domain.user.UserRepository;
import pl.gajdek.alekino.enums.SeatStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ShowingSeatServiceTest {

    @Mock
    private ShowingSeatRepository seatRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CinemaRoomRepository cinemaRoomRepository;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShowingRepository showingRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private HttpSession session;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private TicketServices ticketServices;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private ShowingSeatService seatService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSeatToTicket() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("TestTest");
        user.setEmail("email@wp.pl");

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test movie");

        long cinemaRoomId = 1L;
        CinemaRoom cinemaRoom = new CinemaRoom();
        cinemaRoom.setId(cinemaRoomId);
        cinemaRoom.setMaxNumberOfSeats(100);

        Showing showing = new Showing();
        showing.setId(1L);
        showing.setMovie(movie);
        showing.setStartTime(LocalDateTime.of(2023, 3, 15, 19, 30));
        showing.setShowMin();
        showing.setShowHour();
        showing.setCinemaRoom(cinemaRoom);

        ShowingSeat seat = new ShowingSeat();
        seat.setId(1L);
        seat.setRowNumber(1);
        seat.setSeatNumber(1);
        seat.setBusy(false);
        seat.setShowing(showing);
        seat.setSeatsStatus(SeatStatus.NORMAL);

        Long shoppingCartId = 1L;
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("shopping_cart_id", shoppingCartId);
        when(session.getAttribute("shopping_cart_id")).thenReturn(shoppingCartId);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(userRepository.findByEmail("email@wp.pl")).thenReturn(Optional.of(user));
        when(showingRepository.findById(1L)).thenReturn(Optional.of(showing));
        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat));

        ResponseEntity<?> responseEntity = seatService.reserveShowingSeat(seat.getId());

        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("Ticket added to shopping cart", responseEntity.getBody());

        ResponseEntity<?> responseEntity1 = seatService.reserveShowingSeat(seat.getId());

        assertEquals(400, responseEntity1.getStatusCode().value());
        assertEquals("Seat is busy", responseEntity1.getBody());
    }

}