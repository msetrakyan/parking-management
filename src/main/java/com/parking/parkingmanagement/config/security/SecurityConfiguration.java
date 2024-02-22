package com.parking.parkingmanagement.config.security;

import com.parking.parkingmanagement.config.security.jwt.JwtAuthenticationFilter;
import com.parking.parkingmanagement.constants.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.parking.parkingmanagement.constants.Role.ROLE_ADMIN;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(Path.LOGIN, Path.REGISTER)
                .permitAll()
                .requestMatchers(Path.USER).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.POST, Path.PARKING).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.PUT, Path.PARKING).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.DELETE, Path.PARKING_DELETE).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.GET, Path.BOOKING).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.DELETE, Path.BOOKING).hasRole(ROLE_ADMIN.getName())
                .requestMatchers(HttpMethod.GET, Path.BOOKING_COMMUNITY).hasRole(ROLE_ADMIN.getName())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}