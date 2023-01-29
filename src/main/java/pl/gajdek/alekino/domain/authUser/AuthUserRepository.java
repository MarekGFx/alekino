package pl.gajdek.alekino.domain.authUser;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthUserRepository extends CrudRepository<AuthUsers, Long> {

//    Optional<AuthUsers> findByEmail(String email);
}
