package pl.gajdek.alekino.domain.cinemaRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRoomRepository extends JpaRepository<CinemaRoom, Long> {

    Optional<CinemaRoom> findByRoomNumber(int roomNumber);
}
