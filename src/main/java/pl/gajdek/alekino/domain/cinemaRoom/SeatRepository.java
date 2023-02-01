package pl.gajdek.alekino.domain.cinemaRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {


    Optional<Seat> findByRowNumberAndSeatNumber(int rowNumber, int seatNumber);
}
