package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandBroadcast extends ExecutableCommand {
    public CommandBroadcast() {
        super("bc", null);

        setAliases(new ArrayList<>() {{
            add("broadcast");
        }});
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/broadcast",
                "§7Beschreibung §8→ §eSchreibe eine Nachricht an alle Spieler§8!",
                "§7Verwendung §8→ §e/bc <1-5, varianten> <nachricht>§8- §7Schreibt eine Nachricht an alle Spieler in einem bestimmten Farbverlauft§8."
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if(Permission.hasPermission(player, "citybuild.broadcast")) {

            if(args.length == 1 && args[0].equalsIgnoreCase("varianten")) {
                Message.sendMessage(player, "§7Hier sind alle Varianten§8:");
                Message.sendMessage(player, "§7Varianten 1 §8- §r" + (Utility.withGradient(Utility.toLuni("Das hier ist ein Test."), "#ff1b6b", "#45caff", true)));
                Message.sendMessage(player, "§7Varianten 2 §8- §r" + (Utility.withGradient(Utility.toLuni("Das hier ist ein Test."), "#00FFE0", "#EB00FF", true)));
                Message.sendMessage(player, "§7Varianten 3 §8- §r" + (Utility.withGradient(Utility.toLuni("Das hier ist ein Test."), "#3a405a", "#f9dec9", true)));
                Message.sendMessage(player, "§7Varianten 4 §8- §r" + (Utility.withGradient(Utility.toLuni("Das hier ist ein Test."), "#145277", "#83d0cb", true)));
                Message.sendMessage(player, "§7Varianten 5 §8- §r" + (Utility.withGradient(Utility.toLuni("Das hier ist ein Test."), "#F4C4F3", "#FC67FA", true)));
                return;
            }

            if(args.length >= 2) {
                int variant = Integer.parseInt(args[0]);
                StringBuilder messageBuilder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    messageBuilder.append(args[i]).append(" ");
                }
                String message = messageBuilder.toString().trim();


                if(variant == 1) {
                    for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                        allPlayers.sendMessage("§r");
                        allPlayers.sendMessage(ConstStorage.getPREFIX() + Utility.withGradient(Utility.toLuni(message), "#ff1b6b", "#45caff", true));
                        allPlayers.sendMessage("§r");
                    }
                }

                if(variant == 2) {
                    for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                        allPlayers.sendMessage("§r");
                        allPlayers.sendMessage(ConstStorage.getPREFIX() + Utility.withGradient(Utility.toLuni(message), "#00FFE0", "#EB00FF", true));
                        allPlayers.sendMessage("§r");
                    }
                }

                if(variant == 3) {
                    for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                        allPlayers.sendMessage("§r");
                        allPlayers.sendMessage(ConstStorage.getPREFIX() + Utility.withGradient(Utility.toLuni(message), "#3a405a", "#f9dec9", true));
                        allPlayers.sendMessage("§r");
                    }
                }

                if(variant == 4) {
                    for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                        allPlayers.sendMessage("§r");
                        allPlayers.sendMessage(ConstStorage.getPREFIX() + Utility.withGradient(Utility.toLuni(message), "#145277", "#83d0cb", true));
                        allPlayers.sendMessage("§r");
                    }
                }

                if(variant == 5) {
                    for(Player allPlayers : Bukkit.getOnlinePlayers()) {
                        allPlayers.sendMessage("§r");
                        allPlayers.sendMessage(ConstStorage.getPREFIX() + Utility.withGradient(Utility.toLuni(message), "#F4C4F3", "#FC67FA", true));
                        allPlayers.sendMessage("§r");
                    }
                }

                if(variant <= 0 || variant > 5) {
                    Message.sendMessage(player, "§cBitte gebe eine Variante zwischen 1 und 5 an.");
                    Utility.playError(player);
                    return;
                }
                return;
            }
            Utility.sendSyntax(player, syntax());
        }
    }
}
