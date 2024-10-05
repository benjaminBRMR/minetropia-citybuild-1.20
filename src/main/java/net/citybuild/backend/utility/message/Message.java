package net.citybuild.backend.utility.message;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.storage.ConstStorage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@UtilityClass
public class Message {

    public void sendMessage(final @NonNull Player player, final @NonNull String message) {
        if (message.isBlank()) {
            player.sendMessage("");
            return;
        }
        player.sendMessage(ConstStorage.getPREFIX() + message);
    }

    public void sendActionbar(final @NonNull Player player, final @NonNull String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void sendMessageAll(final @NonNull String message) {
        Bukkit.getOnlinePlayers().forEach(all -> {
            sendMessage(all, message);
        });
    }


}
