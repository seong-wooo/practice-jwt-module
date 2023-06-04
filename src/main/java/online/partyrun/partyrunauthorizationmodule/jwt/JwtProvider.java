package online.partyrun.partyrunauthorizationmodule.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider {
    Key key;

    Long accessTokenValidationSecond;

    Long refreshTokenValidationSecond;

    public JwtProvider(Key key,
                       @Value("${jwt.access-token-validation-second}") Long accessTokenValidationSecond,
                       @Value("${jwt.refresh-token-validation-second}")Long refreshTokenValidationSecond) {
        this.key = key;
        this.accessTokenValidationSecond = accessTokenValidationSecond;
        this.refreshTokenValidationSecond = refreshTokenValidationSecond;
    }

    public JwtToken createJwtTokens(final String uid) {
        final Claims claims = getClaims(uid);

        final String accessToken = generateToken(uid, claims, accessTokenValidationSecond);
        final String refreshToken = generateToken(uid, claims, refreshTokenValidationSecond);

        return JwtToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Claims getClaims(final String uid) {
        Claims claims = Jwts.claims();
        claims.put("id", uid);
        return claims;
    }

    private String generateToken(final String uid, final Claims claims, final Long validationSecond) {
        final long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(uid)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }
}
