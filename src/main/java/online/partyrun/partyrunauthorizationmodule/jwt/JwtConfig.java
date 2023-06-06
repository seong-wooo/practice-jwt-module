package online.partyrun.partyrunauthorizationmodule.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class JwtConfig {

    @Bean
    public JwtManager jwtManager(@Value("${jwt.access-secret-key}") String accessKey,
                                 @Value("${jwt.access-expire-second}") long accessExpireSecond,
                                 @Value("${jwt.refresh-secret-key}") String refreshKey,
                                 @Value("${jwt.refresh-expire-second}") long refreshExpireSecond) {
        return new JwtManager(tokenManager(accessKey, accessExpireSecond),
                tokenManager(refreshKey, refreshExpireSecond));
    }

    private TokenManager tokenManager(String key, long tokenExpireSecond) {
        return new TokenManager(key, tokenExpireSecond, clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
