package pl.gajdek.alekino.domain.blik;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gajdek.alekino.domain.authUser.AuthUsers;

import java.util.Optional;

public interface BlikRepository extends JpaRepository<Blik, Long> {

    Optional<Blik> findByBlikNumber(int blikNumber);
}
