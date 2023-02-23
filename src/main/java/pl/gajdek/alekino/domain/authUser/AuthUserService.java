package pl.gajdek.alekino.domain.authUser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.domain.user.UserRepository;


import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthUserRepository authUserRepository;

    public Optional<AuthUserCredentialsDto> findCredentialsByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Optional<AuthUsers> authUsers = authUserRepository.findByUser(user.get());
            if (authUsers.isPresent()) {
                return Optional.of(AuthUserCredentialsDtoMapper.map(user.get(), authUsers.get()));

            } else
                throw new UsernameNotFoundException("User does not exist");
        } else
            throw new UsernameNotFoundException("User with email " + email + " does not exist");
    }
}
