package net.citybuild.backend.manager;

import lombok.Getter;
import lombok.NonNull;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Sound;

import javax.annotation.Nonnegative;
import java.util.LinkedHashMap;
import java.util.UUID;

public class BoosterManager {


    @Getter
    private final LinkedHashMap<Booster, Long> runningBooster = new LinkedHashMap<>();
    @Getter
    private final LinkedHashMap<Booster, Long> boosterMultiplier = new LinkedHashMap<>();
    @Getter
    private final LinkedHashMap<UUID, Booster> booster = new LinkedHashMap<>();


    @Getter
    private final String activated = "§7Der §f%s §7Booster wurde von §e%s §aaktiviert §8(§8x§f§l%d§8)";
    @Getter
    private final String deactivated = "§7Der §f%s §7Booster ist §cabgelaufen§8.";
    @Getter
    private final String multiplier = "§7Der §f%s §7Booster ist §cabgelaufen §8(§8x§f§l%d§8)";


    public void activateBooster(final @NonNull Booster booster) {
        if (runningBooster.containsKey(booster)) {
            long currentEndTime = runningBooster.get(booster);
            runningBooster.put(booster, currentEndTime + booster.getTime());

            long currentMultiplier = boosterMultiplier.get(booster);
            boosterMultiplier.put(booster, currentMultiplier + 1);
        } else {
            runningBooster.put(booster, System.currentTimeMillis() + booster.getTime());
            boosterMultiplier.put(booster, 1L);
        }
    }


    public void activateBooster(final @NonNull Booster booster, final @Nonnegative long multiplier) {
        long currentTime = System.currentTimeMillis();

        long endTime = currentTime + (booster.getTime() * multiplier);
        long newMultiplier = Math.min(multiplier, booster.getMaxMultiplier());

        runningBooster.put(booster, endTime);
        boosterMultiplier.put(booster, newMultiplier);
    }


    public void deactivateBooster(final @NonNull Booster booster) {
        runningBooster.remove(booster);
        boosterMultiplier.remove(booster);
    }

    public long getMultiplier(final @NonNull Booster booster) {
        return boosterMultiplier.getOrDefault(booster, 1L);
    }

    public boolean isRunning(final @NonNull Booster booster) {
        if (!runningBooster.containsKey(booster)) {
            return false;
        }

        final long endTime = getRemainingBooster(booster);
        final long currentTime = System.currentTimeMillis();
        final long originalTime = booster.getTime();
        final long currentMultiplier = boosterMultiplier.get(booster);

        if (endTime <= 0L) {
            Message.sendMessageAll(String.format(deactivated, booster.name()));
            Utility.playSoundAll(Sound.BLOCK_NOTE_BLOCK_XYLOPHONE);

            long newMultiplier = Math.max(currentMultiplier - 1, 0);
            boosterMultiplier.put(booster, newMultiplier);

            deactivateBooster(booster);
            return false;
        } else {
            long elapsedTime = originalTime * currentMultiplier - endTime;

            if (elapsedTime >= originalTime) {
                long intervalsPassed = elapsedTime / originalTime;
                long newMultiplier = Math.max(currentMultiplier - intervalsPassed, 1);

                if (newMultiplier != currentMultiplier) {
                    Message.sendMessageAll(String.format(multiplier, booster.name(), newMultiplier));
                    Utility.playSoundAll(Sound.BLOCK_NOTE_BLOCK_XYLOPHONE);


                    long adjustedEndTime = currentTime + (originalTime * newMultiplier);
                    runningBooster.put(booster, adjustedEndTime);
                    boosterMultiplier.put(booster, newMultiplier);
                }
            }

            return true;
        }
    }


    public long getRemainingBooster(final @NonNull Booster booster) {
        return (runningBooster.getOrDefault(booster, 0L) - System.currentTimeMillis());
    }

}
