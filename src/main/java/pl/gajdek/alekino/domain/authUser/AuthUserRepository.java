package pl.gajdek.alekino.domain.authUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUsers, Long> {

//    Optional<AuthUsers> findByEmail(String email);
}
