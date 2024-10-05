package net.citybuild.backend.listener.custom;

import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ListenXPBoosterMultiplier implements Listener {

    public static double getMultiplier() {
        if(CityBuild.getBoosterManager().isRunning(Booster.XP)) {
            if(CityBuild.getBoosterManager().getMultiplier(Booster.XP) == 1) {
                return 2;
            }
            if(CityBuild.getBoosterManager().getMultiplier(Booster.XP) == 2) {
                return 3;
            }
            if(CityBuild.getBoosterManager().getMultiplier(Booster.XP) == 3) {
                return 4;
            }
            if(CityBuild.getBoosterManager().getMultiplier(Booster.XP) == 4) {
                return 5;
            }
            if(CityBuild.getBoosterManager().getMultiplier(Booster.XP) == 5) {
                return 6;
            }
            return 0;
        }
        return 0;
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent event) {
        if(event.getEntity().getKiller() != null) {
            int originalXP = event.getDroppedExp();
            int getMultiplier = (int) getMultiplier();
            int modifiedXP = (originalXP*getMultiplier);
            event.setDroppedExp(modifiedXP);
        }
    }
}
