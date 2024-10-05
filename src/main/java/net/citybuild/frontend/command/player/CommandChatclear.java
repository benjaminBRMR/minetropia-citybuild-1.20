package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class CommandChatclear extends ExecutableCommand {

    public CommandChatclear() {
        super("chatclear", "citybuild.cc");

        setAliases(new ArrayList<String>() {{
            add("clearchat");
            add("cc");
        }});
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/chatclear",
                "§7Beschreibung §8→ §eSäubere den Chat vor bösen Wörtern etc. Davon sind Teammitglieder ausgenommen.",
                "§7Verwendung §8→ §e/chatclear §8- §7Säubere den Chat von jeden Spieler ausgenommen sind Teammitglieder",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.cc")) {

            if (args.length == 0) {
                Bukkit.getOnlinePlayers().stream()
                        .filter(all -> !all.hasPermission("citybuild.team"))
                        .forEach(onlinePlayers -> IntStream.range(0, 255).forEach(i -> onlinePlayers.sendMessage("")));

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.hasPermission("citybuild.cc")) {
                        Message.sendMessage(onlinePlayer, "§7Der Chat wurde von §e" + player.getDisplayName() + " §7geleert§8.");
                    } else {
                        Message.sendMessage(onlinePlayer, "§7Der Chat wurde geleert§8.");
                    }
                }
                return;
            }
            Utility.sendSyntax(player, syntax());

        }
    }

}
