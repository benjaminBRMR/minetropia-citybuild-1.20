package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ListenPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        CityBuild.getAfkManager().update(player);
        CityBuild.getINSTANCE().getBegleiterController().update(player);


        if (ConstStorage.getAfkTimer().containsKey(player)) {
            long entryTime = ConstStorage.getAfkTimer().getOrDefault(player, 0L);
            long currentTime = System.currentTimeMillis();
            long afkTime = currentTime - entryTime;
            Message.sendMessage(player, "§6Schön, dass du wieder da bist §e❤ §6Du warst §e" + Utility.timeToString(afkTime, true) + " §6Inaktiv§8.");
            ConstStorage.getAfkTimer().remove(player);
            ConstStorage.getPreviousData().put(player, false);

            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 1);

        }
    }
}
