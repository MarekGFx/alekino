package pl.gajdek.alekino.domain.authUser;

import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.authUser.dto.UserCredentialsDto;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email){
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }
}
