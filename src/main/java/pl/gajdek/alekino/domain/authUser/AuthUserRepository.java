package pl.gajdek.alekino.domain.authUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gajdek.alekino.domain.user.User;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUsers, Long> {

    Optional<AuthUsers> findByUser(User user);
}
