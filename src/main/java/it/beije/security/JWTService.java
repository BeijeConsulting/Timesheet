package it.beije.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.beije.mgmt.exception.TokenVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
	
    private Algorithm algorithm;
    private int defaultExpiration;
    
    
    
    
    public JWTService() {
    	
//            @Value("jwt.secret") String secret,                                       COMMENTATI E SETTATI DIRETTAMENTE IN QUANTO I LORO VALORI SONO NEL FILE APPLICATION PROPERTIES
//            @Value("${jwt.defaultExpirationMillis}") int defaultExpirationMillis) {
    	
        this.algorithm = Algorithm.HMAC256("abcdefghijklmnopqrstuvwxyz123");   // STRING SECRET
        this.defaultExpiration = 30000; //INT DEFAULTEXPIRATIONMILLIS

    }

    public String create(String username) {
        Instant issuedAt = Instant.now();
        return JWT.create()
                .withIssuedAt(Date.from(issuedAt))
                .withExpiresAt(Date.from(issuedAt.plusSeconds(defaultExpiration)))
                .withClaim("username", username)
                .sign(algorithm);
    }

    public Map<String, Object> verify(String token) throws TokenVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().as(Object.class)));
        } catch (Exception e) {
            throw new TokenVerificationException(e);
        }
    }
}

