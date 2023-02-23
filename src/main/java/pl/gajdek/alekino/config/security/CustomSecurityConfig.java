package pl.gajdek.alekino.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.gajdek.alekino.enums.Role;

@Configuration
@EnableWebSecurity
class CustomSecurityConfig  {
    private static final String AUTH_USER_ROLE = "AUTH_USER";
    private static final String ADMIN_ROLE = "ADMIN";

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.PATCH).hasAnyRole(ADMIN_ROLE)
//                .anyRequest().permitAll()
//                .and().httpBasic();
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(  "/swagger-ui/**").permitAll()
                .requestMatchers( "/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers("/genres/add-genre","/showings/create-show"
                        , "/repertoires/{repertoireId}/showings/{showingId}", "/repertoires/{date}/create"
                        , "/movies/add-movie", "/CinemaRoom", "/CinemaRoom/{id}/seats"
                        , "/CinemaRoom/{cinemaRoomId}/first-seat/{firstSeatId}/second-seat/{secondSeatId}")
                .hasRole(ADMIN_ROLE)
                .requestMatchers("/movies/rate-movie/{movieId}/auth-user/{authUserId}").hasAnyRole(AUTH_USER_ROLE)
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic()
                .and()
                .csrf().disable();
        //     http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

}
