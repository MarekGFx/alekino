package pl.gajdek.alekino.domain.showingSeat;

import lombok.AllArgsConstructor;
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
import pl.gajdek.alekino.domain.ticket.Ticket;
import pl.gajdek.alekino.domain.ticket.TicketRepository;
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

//    public ResponseEntity<?> getSeatsByShowing(long id){
//        Optional<Showing> showing = showingRepository.findById(id);
//        if (showing.isPresent()){
//            List<SeatDto> seatDtoList = showing
//                    .getSeats()
//                    .stream()
//                    .map(SeatDtoMapper::mapToSeatDto)
//                    .toList();
//            return ResponseEntity.ok(seatDtoList);
//        }
//        else
//            return ResponseEntity.status(404).body("Cinema room with ID " + id + " does not exist");
//    }
//
//    public ResponseEntity<?> reserveSeat(long seatId, long showingId, long userID){
//        Optional<Seat> seat = seatRepository.findById(seatId);
//        Optional<Showing> showing = showingRepository.findById(showingId);
//        Optional<User> user = userRepository.findById(userID);
//        if (seat.isPresent()) {
//            if (seat.get().isBusy()) {
//                return ResponseEntity.status(400).body("Seat is busy");
//            } else {
//
//                Ticket ticket = new Ticket();
//                Optional<ShoppingCart> shoppingCart = Optional.empty(); //TODO check this is null in that way
//                if (user.get().getShoppingCart() != null){
//                    shoppingCart = shoppingCartRepository.findById(user.get().getShoppingCart().getId());
//                }
//
//                ticket.setSeat(seat.get());
//                ticket.setShowing(showing.get());
//                ticket.setTicketPrice(seat.get().getSeatsStatus());
//                ticketRepository.save(ticket);
//                seat.get().setBusy(true);
//
//                if (shoppingCart.isPresent()) {  //TODO check this is null in that way
//                    shoppingCart.get().getTicket().add(ticket);
//                    ticket.setShoppingCart(shoppingCart.get());
//                    shoppingCartRepository.save(shoppingCart.get());
//                } else {
//                    ShoppingCart newShoppingCart = new ShoppingCart();
//                    List<Ticket> ticketList = new ArrayList<>();
//                    ticketList.add(ticket);
//                    newShoppingCart.setTicket(ticketList);
//                    user.get().setShoppingCart(newShoppingCart);
//                    ticket.setShoppingCart(newShoppingCart);
//                    shoppingCartRepository.save(newShoppingCart);
//
//                }
//                return ResponseEntity.status(200).body("Added ticket to shoppingCart");
//            }
//        }
//        else
//            return ResponseEntity.status(404).body("Seat whit this id" + seatId + " does not exist");
//    }
}

