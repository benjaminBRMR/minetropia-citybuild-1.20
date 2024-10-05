package net.citybuild.backend.booster;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nonnegative;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public enum Booster {


    FLY(TimeUnit.MINUTES.toMillis(10), 8),
    BREAK(TimeUnit.MINUTES.toMillis(10), 8),
    XP(TimeUnit.MINUTES.toMillis(5), 5);

    @Getter
    final @Nonnegative long time;
    @Getter
    final @Nonnegative long maxMultiplier;

}
