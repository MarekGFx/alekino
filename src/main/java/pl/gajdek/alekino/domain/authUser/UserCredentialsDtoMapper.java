package pl.gajdek.alekino.domain.authUser;

import pl.gajdek.alekino.domain.authUser.dto.UserCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {

    static UserCredentialsDto map(User user){
        String email = user.getEmail();
        String password = user.getPassword();
        Role role = user.getRole();
        return new UserCredentialsDto(email,password,role);
    }
}
