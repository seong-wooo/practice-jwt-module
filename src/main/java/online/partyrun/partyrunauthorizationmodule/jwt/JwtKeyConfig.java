package online.partyrun.partyrunauthorizationmodule.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtKeyConfig {

    String secretKey;

    public JwtKeyConfig(@Value("${jwt.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    @Bean
    public Key jwtKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
