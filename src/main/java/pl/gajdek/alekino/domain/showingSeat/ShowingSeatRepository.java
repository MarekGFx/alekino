package pl.gajdek.alekino.domain.showingSeat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowingSeatRepository extends JpaRepository<ShowingSeat,Long> {


//    Optional<showingSeat> findByRowNumberAndSeatNumber(int rowNumber, int seatNumber);
}
