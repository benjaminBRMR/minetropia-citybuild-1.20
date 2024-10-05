package net.citybuild.backend.listener;

import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ListenProxyPing implements Listener {

    @EventHandler
    public void onProxyPing(final ServerListPingEvent event) {
        event.setMotd(ConstStorage.getTITLE() + " §8┃ §7Citybuild Server §8(§e1.20§8)\n §6⌛ §eAktuell befinden wir uns im Aufbau§8! §6⌛");
        event.setMaxPlayers(300);
    }

    @EventHandler
    public void onLogin(final PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        if (!player.isWhitelisted()) {

            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "\n\n" + ConstStorage.getTITLE() + "\n\n §cᴅɪᴇ ᴡʜɪᴛᴇʟɪꜱᴛ ɪꜱᴛ ᴀᴋᴛᴜᴇʟʟ ᴀᴋᴛɪᴠɪᴇʀᴛ. \n §cᴜɴꜱᴇʀ ᴅɪꜱᴄᴏʀᴅ: ᴅᴄ.ᴍɪɴᴇᴛʀᴏᴘɪᴀ.ᴅᴇ");
            Message.sendMessageAll("§e" + player.getName() + " §7wollte den Server betreten§8.");
        }
    }
}
