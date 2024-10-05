package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.sound.SoundUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.stream.IntStream;

public class ListenPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        event.setJoinMessage(null);

        IntStream.range(0, 255).forEach(i -> player.sendMessage("§r"));

        /* DATABASE LOAD */
        CityBuild.getUserManager().load(player.getUniqueId())
                .thenAccept(successfully -> {

                    if (successfully) {
                        Message.sendMessage(player, "§aDeine Daten wurden synchronisiert§8.");

                    } else {
                        Message.sendMessage(player, "§aDeine Daten werden eingetragen§8.");
                    }
                });
        /* DATABASE LOAD */

        if (CityBuild.getLocationManager().existLocation("spawn")) {
            player.teleport(CityBuild.getLocationManager().getLocation("spawn"));
        }

        CityBuild.getScoreboardManager().hook(player);
        CityBuild.getTablistManager().hook(player);
        CityBuild.getAfkManager().hook(player);
        CityBuild.getTablistManager().update(player);

        if (player.hasPermission("citybuild.god")) {
            player.setInvulnerable(true);
            Message.sendMessage(player, "§aDu bist nun im Godmode§8.");
        } else {
            player.setInvulnerable(false);
        }

        CityBuild.getUserManager().getPlayersWithPerkOn(Perk.OBSERVER).forEach(all -> {
            Message.sendMessage(all, "§8(§a+§8) §7" + player.getDisplayName().replaceAll(player.getName(), Utility.toLuni(player.getName())));
        });


        new BukkitRunnable() {
            @Override
            public void run() {
                CityBuild.getINSTANCE().getBegleiterController().hook(player);
                SoundUtility.rareDropJingle(player);
            }
        }.runTaskLater(CityBuild.getINSTANCE(), 10L);





        /*
        final String[] colors = {
          "#F3904F",
          "#4F5AF3",
          "#FFE259",
          "#3B4371"
        };

        Message.sendMessage(player, Color.gradient(player.getName(), colors));

         */
    }
}
