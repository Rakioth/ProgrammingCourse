package com.raks.swiftly.infrastructure.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.raks.swiftly.domain.model.dto.TokenDto;
import com.raks.swiftly.domain.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final Algorithm _algorithm;
    private final long      _accessExpiration;
    private final long      _refreshExpiration;

    private final CustomUserDetailsService _userDetailsService;

    @Autowired
    public JwtService(@Value("${jwt.access-expiration}") String secretKey, @Value("${jwt.access-expiration}") long accessExpiration, @Value("${jwt.refresh-expiration}") long refreshExpiration, CustomUserDetailsService userDetailsService) {
        _algorithm          = Algorithm.HMAC256(secretKey.getBytes());
        _accessExpiration   = accessExpiration;
        _refreshExpiration  = refreshExpiration;
        _userDetailsService = userDetailsService;
    }

    public TokenDto createJwt(String username) {
        UserDetails userDetails = _userDetailsService.loadUserByUsername(username);
        Date        now         = new Date();

        String accessToken = JWT.create()
                                .withSubject(userDetails.getUsername())
                                .withExpiresAt(new Date(now.getTime() + _accessExpiration))
                                .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                                .sign(_algorithm);

        String refreshToken = JWT.create()
                                 .withSubject(userDetails.getUsername())
                                 .withExpiresAt(new Date(now.getTime() + _refreshExpiration))
                                 .sign(_algorithm);

        return TokenDto.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
    }

    public String refreshAccess(String refreshToken) {
        JWTVerifier verifier    = JWT.require(_algorithm).build();
        DecodedJWT  decodedJWT  = verifier.verify(refreshToken);
        String      username    = decodedJWT.getSubject();
        UserDetails userDetails = _userDetailsService.loadUserByUsername(username);

        return JWT.create()
                  .withSubject(userDetails.getUsername())
                  .withExpiresAt(new Date(new Date().getTime() + _accessExpiration))
                  .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                  .sign(_algorithm);
    }

    public String getUsername(String accessToken) {
        JWTVerifier verifier   = JWT.require(_algorithm).build();
        DecodedJWT  decodedJWT = verifier.verify(accessToken);
        return decodedJWT.getSubject();
    }

}