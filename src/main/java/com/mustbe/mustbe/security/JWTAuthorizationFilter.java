package com.mustbe.mustbe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.services.PlayerService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static com.mustbe.mustbe.security.SecurityConstants.HEADER_STRING;
import static com.mustbe.mustbe.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    private PlayerService playerService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, PlayerService playerService) {
        super(authenticationManager);
        this.playerService = playerService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            try {
                String phone = JWT.require(Algorithm.HMAC512(JWTProvider.getInstance().getSecret().getBytes()))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""))
                        .getSubject();

                if (phone != null) {
                    Player stored = playerService.findByPhone(phone);
                    if(stored == null) return null;
                    return new UsernamePasswordAuthenticationToken(stored, null, new ArrayList<>());
                }
            }
            catch(Exception ex){
                return null;
            }
            return null;
        }
        return null;
    }

}