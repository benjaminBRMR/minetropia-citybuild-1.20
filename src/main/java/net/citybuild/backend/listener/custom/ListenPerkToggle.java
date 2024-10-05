package net.citybuild.backend.listener.custom;

import net.citybuild.CityBuild;
import net.citybuild.backend.events.PerkToggleEvent;
import net.citybuild.backend.perk.Perk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenPerkToggle implements Listener {

    @EventHandler
    public void onPerkToggle(final PerkToggleEvent event) {
        final Player player = event.getPlayer();
        final Perk perk = event.getPerk();
        final boolean state = event.isState();

        if (!CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk)) return;

    }
}
