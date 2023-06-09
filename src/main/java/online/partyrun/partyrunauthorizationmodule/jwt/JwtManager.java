package online.partyrun.partyrunauthorizationmodule.jwt;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtManager implements JwtGenerator, JwtExtractor {

    TokenManager accessTokenManager;
    TokenManager refreshTokenManager;

    @Override
    public JwtToken generate(String id) {
        return JwtToken.builder()
                .accessToken(accessTokenManager.generate(id))
                .refreshToken(refreshTokenManager.generate(id))
                .build();
    }

    @Override
    public JwtPayload extract(String accessToken) {
        return accessTokenManager.extract(accessToken);
    }

    @Override
    public String generateAccessToken(String refreshToken) {
        final JwtPayload extract = refreshTokenManager.extract(refreshToken);
        return accessTokenManager.generate(extract.id());
    }
}
