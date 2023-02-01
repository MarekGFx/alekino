package pl.gajdek.alekino.domain.cinemaRoom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {

    Optional<CinemaRoom> findByRoomNumber(int roomNumber);
}
