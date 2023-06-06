package online.partyrun.partyrunauthorizationmodule.jwt;

public interface JwtExtractor {
    JwtPayload extract(String accessToken);
}
