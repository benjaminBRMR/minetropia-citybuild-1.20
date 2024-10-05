package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenPlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        event.setQuitMessage(null);

        CityBuild.getUserManager().save(player.getUniqueId());
        CityBuild.getCaseManager().getChanceEdit().remove(player);
        CityBuild.getScoreboardManager().delete(player);
        CityBuild.getAfkManager().delete(player);
        ConstStorage.getAfkTimer().remove(player);
        CityBuild.getINSTANCE().getBegleiterController().delete(player);


        CityBuild.getUserManager().getPlayersWithPerkOn(Perk.OBSERVER).forEach(all -> {
            Message.sendMessage(all, "§8(§c-§8) §7" + player.getDisplayName().replaceAll(player.getName(), Utility.toLuni(player.getName())));
        });

    }
}

