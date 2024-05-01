package br.com.onitama.service;

import br.com.onitama.error.exception.UnauthorizedException;
import br.com.onitama.model.entity.UserEntity;
import br.com.onitama.model.response.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;  // Tempo de expiração do token em milissegundos

    private final UserService userService;

    public TokenService(UserService userService) {
        this.userService = userService;
    }

    public TokenResponse generateToken(String username) {
        UserEntity user = userService.findByUsername(username);
        String token = Jwts.builder()
                .setSubject(username)
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return new TokenResponse(extractClaim(token, Claims::getExpiration), token);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public void isAuthorization(String playerUsername, String tokenUsername) {
        if (!playerUsername.equals(tokenUsername))
            throw new UnauthorizedException("Usuário não autorizado.");
    }
}