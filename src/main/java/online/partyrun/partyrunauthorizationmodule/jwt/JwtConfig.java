package online.partyrun.partyrunauthorizationmodule.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;

import java.time.Clock;

@AutoConfiguration
@Slf4j
@Conditional(JwtCondition.class)
@Import(ClockConfiguration.class)
public class JwtConfig {

    @Autowired
    private Clock clock;

    @Bean
    public JwtManager jwtManager(@Value("${jwt.access-secret-key}") String accessKey,
                                 @Value("${jwt.access-expire-second}") long accessExpireSecond,
                                 @Value("${jwt.refresh-secret-key}") String refreshKey,
                                 @Value("${jwt.refresh-expire-second}") long refreshExpireSecond) {
        return new JwtManager(tokenManager(accessKey, accessExpireSecond),
                tokenManager(refreshKey, refreshExpireSecond));
    }

    private TokenManager tokenManager(String key, long tokenExpireSecond) {
        return new TokenManager(key, tokenExpireSecond, clock);
    }
}

//ACCESS_EXPIRE_SECOND=100000;ACCESS_SECRET_KEY=xclkafvjavjasoipruqoweirlkzxckvjasoieurqpowexclkafvjavjasoipruqoweirlkzxckvjasoieurqpowe;REFRESH_EXPIRE_SECOND=1000000;REFRESH_SECRET_KEY=xclkzxcvjhq9w8e419287kjdbfzm1lasdf353SDxclkzxcvjhq9w8e419287kjdbfzm1lasdf353SD
