package pl.gajdek.alekino.domain.authUser;

import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;
import pl.gajdek.alekino.domain.user.Role;

public class AuthUserCredentialsDtoMapper {

//    static UserCredentialsDto map(User user){
//        String email = user.getEmail();
//        String password = user.getPassword();
//        Set<String> roles = user.getRoles()
//                .stream().map(UserRole::getName)
//                .collect(Collectors.toSet());
//        return new UserCredentialsDto(email,password,roles);
//    }

    static AuthUserCredentialsDto map(AuthUsers authUser){
        String email = authUser.getUser().getEmail();
        String password = authUser.getPassword();
        Role role = authUser.getUser().getRole();
        return new AuthUserCredentialsDto(email,password,role);
    }


}
