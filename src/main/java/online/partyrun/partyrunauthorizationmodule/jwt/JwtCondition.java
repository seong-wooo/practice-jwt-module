package online.partyrun.partyrunauthorizationmodule.jwt;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JwtCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final Environment environment = context.getEnvironment();
        final boolean result1 = environment.containsProperty("jwt.access-secret-key");
        final boolean result2 = environment.containsProperty("refresh-secret-key");
        final boolean result3 = environment.containsProperty("access-expire-second");
        final boolean result4 = environment.containsProperty("refresh-expire-second");

        return result1 && result2 && result3 && result4;
    }
}
