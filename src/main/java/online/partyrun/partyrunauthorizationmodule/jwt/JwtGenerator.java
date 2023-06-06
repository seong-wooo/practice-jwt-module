package online.partyrun.partyrunauthorizationmodule.jwt;

public interface JwtGenerator {
    JwtToken generate(String id);

    String generateAccessToken(String refreshToken);
}
