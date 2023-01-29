//package pl.gajdek.alekino.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import pl.gajdek.alekino.domain.user.Role;
//
//@Configuration
//@EnableWebSecurity
//
//class CustomSecurityConfig  {
//    private static final String USER_ROLE = "USER";
//    private static final String ADMIN_ROLE = "ADMIN";

//
//    private final JwtAuthenticationFilter jwtAuthFilter;
//
//    private final AuthenticationProvider authenticationProvider;
//        @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/auth/**")
//                .permitAll()
//                .requestMatchers("/movies/add-movie").hasAnyRole(String.valueOf(Role.ADMIN))
//                .requestMatchers(HttpMethod.GET)
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////       http
////               .csrf().disable()
////               .authorizeHttpRequests()
////               .requestMatchers(HttpMethod.POST).hasAnyRole(String.valueOf(Role.ADMIN))
////               .anyRequest().permitAll()
////               .and().httpBasic();
//        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//        http.headers().frameOptions().sameOrigin();
//        return http.build();
//    }
//

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
//}
//