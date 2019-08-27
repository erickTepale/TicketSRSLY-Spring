package com.example.ticket.ticketSRSLY.security;

import com.example.ticket.ticketSRSLY.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * RESPONSIBILITIES OF THIS CLASS
 *
 * 1. Check for access token in Authorization header.
 *      If Access token is found in the header, delegate authentication to JwtTokenProvider
 *      otherwise throw authentication exception
 * 2. Invokes success or failure strategies based on the outcome of authentication process
 *      performed by JwtTokenProvider
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected  void doFilterInternal(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse,
                                     FilterChain filterChain) throws ServletException,
                                                                        IOException{
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (CustomException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
