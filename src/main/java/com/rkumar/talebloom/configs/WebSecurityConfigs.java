package com.rkumar.talebloom.configs;

import com.rkumar.talebloom.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigs {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception  {
        httpSecurity
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/api/error","/api/auth/**").permitAll()   // Public endpoints
                                .anyRequest().authenticated())  // All other requests require authentication

                .csrf(csrfConfig -> csrfConfig.disable())  // Disable CSRF for simplicity, not recommended for production

                .sessionManagement(sessionConfig ->   // Disable JSESSIONID for simplicity, not recommended for production
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //add the filter just before of the ther usernamepasswordauthenticationfilter

        return httpSecurity.build();   //when you add build this throws an exception
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
