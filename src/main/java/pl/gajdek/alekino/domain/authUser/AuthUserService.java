package pl.gajdek.alekino.domain.authUser;

import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;

import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public AuthUserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

//    public Optional<AuthUserCredentialsDto> findCredentialsByEmail(String email){
//        return authUserRepository.findByEmail(email)
//                .map(AuthUserCredentialsDtoMapper::map);
//    }
}
