package net.citybuild.backend.perk.controller;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.perk.Perk;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PerkController {

    public void tickPerk(final @NonNull Player onlinePlayer) {
        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.SLOW_FALLING)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.ROAD_RUNNER)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30, 2, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.BUGS_BUNNY)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30, 2, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.NO_FIREDAMAGE)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.NIGHTVISION)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 240, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.NO_HUNGER)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 30, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.NO_DROWNING)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 30, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.ONE_WITH_THE_OCEAN)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 30, 1, true, true));
        }

        if (CityBuild.getUserManager().hasPerkToggled(onlinePlayer.getUniqueId(), Perk.GLOWING)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 30, 1, true, true));
        }

        if (CityBuild.getBoosterManager().isRunning(Booster.BREAK)) {
            onlinePlayer.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, 8, true, false));
        }
    }
}
