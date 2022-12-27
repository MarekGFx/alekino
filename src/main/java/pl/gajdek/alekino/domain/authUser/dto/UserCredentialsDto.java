package pl.gajdek.alekino.domain.authUser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class UserCredentialsDto {

    private final String email;
    private final String password;
    private final Set<String> roles;
}
