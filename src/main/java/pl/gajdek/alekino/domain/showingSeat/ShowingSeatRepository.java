package pl.gajdek.alekino.domain.showingSeat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowingSeatRepository extends JpaRepository<ShowingSeat,Long> {
}
