package online.partyrun.partyrunauthorizationmodule.jwt;

import lombok.Builder;

@Builder
public record JwtToken(String accessToken, String refreshToken) {
}

