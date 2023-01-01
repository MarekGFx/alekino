package pl.gajdek.alekino.domain.authUser.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
public class UserCredentialsDto {

    @Email
    private final String email;
    private final String password;
    private final Set<String> roles;
}
