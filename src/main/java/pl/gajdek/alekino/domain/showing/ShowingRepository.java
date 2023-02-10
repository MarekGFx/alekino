package pl.gajdek.alekino.domain.showing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

    List<Showing> findByMovieId(long id);

    List<Showing> findByCinemaRoomId(long id);
    @Override
    Optional<Showing> findById(Long aLong);

    List<Showing> findByStartTime(LocalDateTime localDateTime);

}
