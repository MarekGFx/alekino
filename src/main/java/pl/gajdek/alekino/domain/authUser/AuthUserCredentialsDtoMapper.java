package pl.gajdek.alekino.domain.authUser;

import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;
import pl.gajdek.alekino.domain.user.User;
import pl.gajdek.alekino.enums.Role;

public class AuthUserCredentialsDtoMapper {

    static AuthUserCredentialsDto map(User user, AuthUsers authUsers){
        String email = user.getEmail();
        String password = authUsers.getPassword();
        Role role = user.getRole();
        return new AuthUserCredentialsDto(email,password,role);
    }


}
