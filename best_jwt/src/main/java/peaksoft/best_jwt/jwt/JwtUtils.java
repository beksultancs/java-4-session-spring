package peaksoft.best_jwt.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import peaksoft.best_jwt.config.JwtConfig;
import peaksoft.best_jwt.models.Student;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author Beksultan
 */
@Component
@AllArgsConstructor
public class JwtUtils {
    // jwt secret & jwt token expired date
    private JwtConfig jwtConfig;

    // token generator
    public String generateJWTToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtConfig.getTokenExpirationAfterDays()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .compact();
    }

    // get username from token
    public String getEmailFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    // token validator
    public boolean validateJWTToken(String jwtToken) {
        Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(jwtToken);
        return true;
        /*
        vozmojno chykchu oshibkalar
        SignatureException
        MalformedJwtException
        ExpiredJwtException
        UnsupportedJwtException
        IllegalArgumentException
         */
    }
}
