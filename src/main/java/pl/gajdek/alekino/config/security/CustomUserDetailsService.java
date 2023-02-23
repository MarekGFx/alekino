package pl.gajdek.alekino.config.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.domain.authUser.AuthUserService;
import pl.gajdek.alekino.domain.authUser.dto.AuthUserCredentialsDto;


@Service
public class CustomUserDetailsService implements UserDetailsService {

//    private UserService userService;
//private UserService userService;
//
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        return userService.findCredentialsByEmail(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", username)));
//    }
//
//    private UserDetails createUserDetails(UserCredentialsDto userCredentialsDto) {
//                System.out.println(userCredentialsDto.getEmail() + " "
//                + userCredentialsDto.getPassword() + " "
//                + userCredentialsDto.getRole().toString());
//        return User.builder()
//                .username(userCredentialsDto.getEmail())
//                .password(userCredentialsDto.getPassword())
//                .roles(userCredentialsDto.getRole().toString())
//                .build();
//    }

    @Autowired
    private AuthUserService authUserService;
    public CustomUserDetailsService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return authUserService.findCredentialsByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", username)));
    }

    private UserDetails createUserDetails(AuthUserCredentialsDto userCredentialsDto) {
        System.out.println(userCredentialsDto.getEmail() + " "
                + userCredentialsDto.getPassword() + " "
                + userCredentialsDto.getRole().toString());
        return User.builder()
                .username(userCredentialsDto.getEmail())
                .password(userCredentialsDto.getPassword())
                .roles(userCredentialsDto.getRole().toString())
                .build();
    }
}
