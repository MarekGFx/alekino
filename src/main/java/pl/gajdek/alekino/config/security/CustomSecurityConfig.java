package pl.gajdek.alekino.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
class CustomSecurityConfig  {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeHttpRequests()
               .requestMatchers(HttpMethod.POST).hasAnyRole(ADMIN_ROLE)
               .anyRequest().permitAll()
               .and().httpBasic();
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        try {
//            http.authorizeHttpRequests((authz) -> authz
//                    .requestMatchers(HttpMethod.POST).hasAnyRole(ADMIN_ROLE)
//                            .anyRequest().permitAll()
//                    )
//                    .formLogin(withDefaults());
//
//        } catch (Exception e) {
//            System.out.println("Nie masz dziadu uprawnień");
//        }
//        return http.build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers(
//                "/img/**",
//                "/scripts/**",
//                "/styles/**"
//        );
//    }
}
