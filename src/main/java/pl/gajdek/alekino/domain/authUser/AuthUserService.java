package pl.gajdek.alekino.domain.authUser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserService {

    @Autowired
    private final AuthUserRepository authUserRepository;

//    public Optional<AuthUserCredentialsDto> findCredentialsByEmail(String email){
//        return authUserRepository.findByEmail(email)
//                .map(AuthUserCredentialsDtoMapper::map);
//    }
}
