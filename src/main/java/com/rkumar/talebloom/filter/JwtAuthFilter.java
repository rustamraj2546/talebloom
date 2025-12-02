package com.rkumar.talebloom.filter;

import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.services.JwtService;
import com.rkumar.talebloom.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtService jwtService;

    /*
     * Used for handling JwtException because GlobalExceptionHandler handles exception in servlet dispatcher context.
     * and JwtException occurred in Security Filter Chain Contexts therefore it requires explicit Exception resolver (it comes from servlet).
     * */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestTokenHeader = request.getHeader("Authorization"); //Extract the Authorization Header:

            //this token is actually concatenated with Bearer space("Bearer ")
            //Check for a Bearer Token:
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            //Ensure that the token in the header is correctly formatted as "Bearer <your-jwt-token>".
            String token = requestTokenHeader.split("Bearer ")[1]; // Extract JWT token from the header
            Long userId = jwtService.getUserIdFromToken(token); // Verify the token

            //Check if userId is Present and the Security Context is Empty
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                //Retrieve the User Entity
                UserEntity user = userService.getUserById(userId);

                //Create an Authentication Token
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, null);

                //Set Authentication Details
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                //Set Authentication in Security Context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            //Continue the Filter Chain
            filterChain.doFilter(request, response);
        } catch(Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex); // Resolving Exception JwtException
        }
    }
}
