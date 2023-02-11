package pl.gajdek.alekino.domain.showingSeat;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoomRepository;
import pl.gajdek.alekino.domain.cinemaRoom.dto.AddSeatToCinemaRoomDto;
import pl.gajdek.alekino.domain.cinemaRoom.dto.SeatDto;
import pl.gajdek.alekino.domain.cinemaRoom.map.SeatDtoMapper;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCart;
import pl.gajdek.alekino.domain.shoppingCart.ShoppingCartRepository;
import pl.gajdek.alekino.domain.showing.Showing;
import pl.gajdek.alekino.domain.showing.ShowingRepository;
import pl.gajdek.alekino.domain.showingSeat.dto.ShowingSeatDto;
import pl.gajdek.alekino.domain.showingSeat.map.ShowingSeatDtoMapper;
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.TicketRepository;
import pl.gajdek.alekino.domain.ticket.TicketServices;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShowingSeatService {

    private ShowingSeatRepository showingSeatRepository;
    private CinemaRoomRepository cinemaRoomRepository;
    private ShowingRepository showingRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private TicketRepository ticketRepository;
    private ShowingSeatDtoMapper showingSeatDtoMapper;
    private TicketServices ticketServices;

    @Autowired
    private HttpSession session;

    public ResponseEntity<?> getSeatsByShowing(long id){
        Optional<Showing> showing = showingRepository.findById(id);
        if (showing.isPresent()){
            List<ShowingSeatDto> seatDtoList = showing.get()
                    .getSeats()
                    .stream()
                    .map(showingSeatDtoMapper::mapToShowingSeatDto)
                    .toList();
            return ResponseEntity.ok(seatDtoList);
        }
        else
            return ResponseEntity.status(404).body("Showing with ID " + id + " does not exist");
    }

    public ResponseEntity<?> reserveShowingSeat(long seatId){
        Optional<ShowingSeat> seat = showingSeatRepository.findById(seatId);

        if(!seat.isPresent()){
            return ResponseEntity.status(404).body("Seat with ID " + seatId + " does not exist");
        } else if(seat.get().isBusy()) {
            return ResponseEntity.status(400).body("Seat is busy");
        } else {
            seat.get().setBusy(true);
            Ticket ticket = new Ticket();
            ticket.setShowingSeat(seat.get());
            ticket.setShowing(seat.get().getShowing());
            ticket.setTicketPrice(seat.get().getSeatsStatus());
            showingSeatRepository.save(seat.get());
            ticketServices.addTicket(ticket);
            ticketRepository.save(ticket);
            return ResponseEntity.status(200).body("Ticket added to shopping cart");
        }
    }
}

