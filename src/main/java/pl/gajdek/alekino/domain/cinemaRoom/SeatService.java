package pl.gajdek.alekino.domain.cinemaRoom;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
public class SeatService {

    private SeatRepository seatRepository;
    private CinemaRoomRepository cinemaRoomRepository;
    private ShowingRepository showingRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private TicketRepository ticketRepository;

    public ResponseEntity<?> getSeatsByCinemaRoom(long id){
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(id);
        if (cinemaRoom.isPresent()){
            List<SeatDto> seatDtoList = cinemaRoom.get()
                    .getSeats()
                    .stream()
                    .map(SeatDtoMapper::mapToSeatDto)
                    .toList();
            return ResponseEntity.ok(seatDtoList);
        }
        else
            return ResponseEntity.status(404).body("Cinema room with ID " + id + " does not exist");
    }

    public ResponseEntity<?> addSeatToCinemaRoom(long id, AddSeatToCinemaRoomDto seatToAdd){
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(id);

        if (cinemaRoom.isPresent()){
            Seat seat = new Seat();
            seat.setBusy(false);
            seat.setRowNumber(seatToAdd.getRowNumber());
            seat.setSeatNumber(seatToAdd.getSeatNumber());
            seat.setSeatsStatus(seatToAdd.getSeatsStatus());
            seat.setCinemaRoom(cinemaRoom.get());

            for (Seat s : cinemaRoom.get().getSeats()) {
                if (s.getRowNumber() == seat.getRowNumber() && s.getSeatNumber() == seat.getSeatNumber()){
                    return ResponseEntity.status(400).body("Seat in row "
                            + seat.getRowNumber() + " and number " + seat.getSeatNumber() + " already exist");
                }
            }

            if (cinemaRoom.get().getSeats().size() >= cinemaRoom.get().getMaxNumberOfSeats()){
                System.out.println(cinemaRoom.get().getSeats().size());
                System.out.println(cinemaRoom.get().getMaxNumberOfSeats());
                return ResponseEntity.status(400).body("Maximum number of seats has been reached");
            }

            if (cinemaRoom.get().getSeats().isEmpty()) {
                List<Seat> seatsList = new ArrayList<>();
                seatsList.add(seat);
                cinemaRoom.get().setSeats(seatsList);
            } else {
                cinemaRoom.get().getSeats().add(seat);
            }

            seatRepository.save(seat);
            cinemaRoomRepository.save(cinemaRoom.get());
            return ResponseEntity.status(201).body("Seat was correctly added");
        }
        else
            return ResponseEntity.status(404).body("Cinema room with ID " + id + " does not exist");
    }

    public ResponseEntity<?> reserveSeat(long seatId, long showingId, long userID){
        Optional<Seat> seat = seatRepository.findById(seatId);
        Optional<Showing> showing = showingRepository.findById(showingId);
        Optional<User> user = userRepository.findById(userID);
        if (seat.isPresent()) {
            if (seat.get().isBusy()) {
                return ResponseEntity.status(400).body("Seat is busy");
            } else {

                Ticket ticket = new Ticket();
                Optional<ShoppingCart> shoppingCart = null;
                if (user.get().getShoppingCart() != null){
                    shoppingCart = shoppingCartRepository.findById(user.get().getShoppingCart().getId());
                }

                ticket.setSeat(seat.get());
                ticket.setShowing(showing.get());
                ticket.setTicketPrice(seat.get().getSeatsStatus());
                ticketRepository.save(ticket);
                seat.get().setBusy(true);

                if (shoppingCart.isPresent()) {
                    shoppingCart.get().getTicket().add(ticket);
                    ticket.setShoppingCart(shoppingCart.get());
                    shoppingCartRepository.save(shoppingCart.get());
                } else {
                    ShoppingCart newShoppingCart = new ShoppingCart();
                    List<Ticket> ticketList = new ArrayList<>();
                    ticketList.add(ticket);
                    newShoppingCart.setTicket(ticketList);
                    user.get().setShoppingCart(newShoppingCart);
                    ticket.setShoppingCart(newShoppingCart);
                    shoppingCartRepository.save(newShoppingCart);

                }
                return ResponseEntity.status(200).body("Added ticket to shoppingCart");
            }
        }
        else
            return ResponseEntity.status(404).body("Seat whit this id" + seatId + " does not exist");
    }
}

