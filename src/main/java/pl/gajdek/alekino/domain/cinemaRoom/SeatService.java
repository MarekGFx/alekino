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
import pl.gajdek.alekino.enums.SeatStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SeatService {

    private SeatRepository seatRepository;
    private CinemaRoomRepository cinemaRoomRepository;
    private ShowingRepository showingRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private TicketRepository ticketRepository;

    public ResponseEntity<?> getSeatsByCinemaRoom(long id) {
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(id);
        if (cinemaRoom.isPresent()) {
            List<SeatDto> seatDtoList = cinemaRoom.get()
                    .getSeats()
                    .stream()
                    .map(SeatDtoMapper::mapToSeatDto)
                    .toList();
            return ResponseEntity.ok(seatDtoList);
        } else
            return ResponseEntity.status(404).body("Cinema room with ID " + id + " does not exist");
    }

    public ResponseEntity<?> addSeatToCinemaRoom(long id, AddSeatToCinemaRoomDto seatToAdd) {
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(id);

        if (cinemaRoom.isPresent()) {
            Seat seat = new Seat();
            seat.setRowNumber(seatToAdd.getRowNumber());
            seat.setSeatNumber(seatToAdd.getSeatNumber());
            seat.setCinemaRoom(cinemaRoom.get());

            for (Seat s : cinemaRoom.get().getSeats()) {
                if (s.getRowNumber() == seat.getRowNumber() && s.getSeatNumber() == seat.getSeatNumber()) {
                    return ResponseEntity.status(400).body("Seat in row "
                            + seat.getRowNumber() + " and number " + seat.getSeatNumber() + " already exist");
                }
            }

            if (cinemaRoom.get().getSeats().size() >= cinemaRoom.get().getMaxNumberOfSeats()) {
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
        } else
            return ResponseEntity.status(404).body("Cinema room with ID " + id + " does not exist");
    }

    public ResponseEntity<?> setSeatStatus(long cinemaRoomId, long firstSeatId, long secondSeatId) {
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(cinemaRoomId);
        Optional<Seat> firstSeat = seatRepository.findById(firstSeatId);
        Optional<Seat> secondSeat = seatRepository.findById(secondSeatId);
        List<Showing> showings = showingRepository.findByCinemaRoomId(cinemaRoomId);

        if (!showings.isEmpty()){
            return ResponseEntity.status(400).body("In this cinema hall are already scheduled screenings, " +
                    "you can not now modify the arrangement of chairs in the hall");
        }

        if (!cinemaRoom.isPresent()) {
            return ResponseEntity.status(404).body("Cinema room with id " + cinemaRoomId + " does not exist");
        } else if (!firstSeat.isPresent() || !secondSeat.isPresent()) {
            return ResponseEntity.status(400).body("Can't combine seats because one or both don't exist");
        } else if (firstSeat.get().getRowNumber() != secondSeat.get().getRowNumber()) {
            return ResponseEntity.status(400).body("Can't combine seats in different rows");
        } else if (!cinemaRoom.get().getSeats().contains(firstSeat.get()) ||
                !cinemaRoom.get().getSeats().contains(secondSeat.get())) {
            return ResponseEntity.status(404).body("Seats dose not belong to this cinema room");
        } else if (firstSeat.get().getSeatsStatus().equals(SeatStatus.VIP) ||
                secondSeat.get().getSeatsStatus().equals(SeatStatus.VIP)) {
            return ResponseEntity.status(404).body("One of chosen seats has status Vip already");
        } else if (firstSeat.get().getSeatNumber() == secondSeat.get().getSeatNumber() - 1 ||
                firstSeat.get().getSeatNumber() == secondSeat.get().getSeatNumber() + 1) {
            if (firstSeat.get().getSeatNumber() < secondSeat.get().getSeatNumber()) {
                firstSeat.get().setSeatsStatus(SeatStatus.VIP);
                seatRepository.delete(secondSeat.get());
                resetSeatsNumber(firstSeatId, cinemaRoomId);
                return ResponseEntity.ok("Seats was merged");
            } else {
                secondSeat.get().setSeatsStatus(SeatStatus.VIP);
                seatRepository.delete(firstSeat.get());
                resetSeatsNumber(secondSeatId, cinemaRoomId);
                return ResponseEntity.ok("Seats was merged");
            }
        } else
            return ResponseEntity.badRequest().body("Given seats should be side by side");
    }

    private void resetSeatsNumber(long seatId, long cinemaRoomId) {
        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(cinemaRoomId);
        Optional<Seat> seatToCheckRow = seatRepository.findById(seatId);
        int number = 1;
        if (cinemaRoom.isPresent() && seatToCheckRow.isPresent()) {
            List<Seat> seatList = cinemaRoom.get().getSeats();
            for (Seat seat : seatList) {
                Optional<Seat> seatToResetNum = seatRepository.findById(seat.getId());
                if (seatToResetNum.isPresent()){
                    if (seatToResetNum.get().getRowNumber() == seatToCheckRow.get().getRowNumber()){
                        seatToResetNum.get().setSeatNumber(number);
                        seatRepository.save(seatToResetNum.get());
                        number++;
                    }
                }
            }
        }
    }

}

