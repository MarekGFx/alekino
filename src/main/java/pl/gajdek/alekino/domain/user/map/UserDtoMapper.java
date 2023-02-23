package pl.gajdek.alekino.domain.user.map;

import org.springframework.stereotype.Component;
import pl.gajdek.alekino.domain.authUser.AuthUsers;
import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;
import pl.gajdek.alekino.domain.user.User;

import pl.gajdek.alekino.domain.user.dto.UserDto;
import pl.gajdek.alekino.enums.Role;

@Component
public class UserDtoMapper {

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
