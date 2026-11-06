package backend.gamelibrary.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                // OPEN FOR EVERYONE
                .requestMatchers("/frontpage", "/library", "/login", "/newuser", "/saveuser", "/h2-console/**")
                .permitAll()

                // ADMIN ACCESS
                .requestMatchers("/edit/{id}", "/deletegame/{id}", "/savegame").hasAuthority("admin")
                .anyRequest().authenticated())

                .formLogin(formlogin -> formlogin
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/userprofile", true))

                .logout(logout -> logout
                        .logoutSuccessUrl("/frontpage"))

                // H2-CONSOLE STUFF
                .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/h2-console/**"))

                .headers(headers -> headers
                    .frameOptions(frame -> frame.disable()));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}