package online.partyrun.partyrunauthorizationmodule.jwt;

import java.time.LocalDateTime;

public record JwtPayload(String id, LocalDateTime expireAt) {
}
