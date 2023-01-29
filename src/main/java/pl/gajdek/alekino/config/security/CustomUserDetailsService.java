//package pl.gajdek.alekino.config.security;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private UserService userService;
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
//        return User.builder()
//                .username(userCredentialsDto.getEmail())
//                .password(userCredentialsDto.getPassword())
//                .roles(String.valueOf(userCredentialsDto.getRole()))
//                .build();
//    }
//}
