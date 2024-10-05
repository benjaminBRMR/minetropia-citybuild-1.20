package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandGodMode extends ExecutableCommand {
    public CommandGodMode() {
        super("god", null);


        setAliases(new ArrayList<String>() {
            {
                add("godmode");
            }
        });
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/godmode",
                "§7Beschreibung §8→ §eAls Teammitglied hast du die Rechte auf /godmode, welche dir ermöglichen nicht zu sterben§8.",
                "§7Verwendung §8→ §e/godmode " + (player.hasPermission("citybuild.godmode.other") ? "<spieler>" : ""),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, String[] args) {


        if (Permission.hasPermission(player, "citybuild.godmode")) {

            if (args.length == 0) {
                if (!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    Message.sendMessage(player, "§aDu bist nun im Godmode§8.");
                    return;
                }
                Message.sendMessage(player, "§cDu bist nun nicht mehr im Godmode§8.");
                player.setInvulnerable(false);
                return;
            }


            if (Permission.hasPermission(player, "citybuild.godmode.other")) {
                if (args.length == 1) {
                    final Player target = Bukkit.getPlayer(args[0]);

                    if (target == player) {
                        Message.sendMessage(player, "§cBitte benutze dafür, den Befehl ohne Argument.");
                        return;
                    }

                    if (target == null) {
                        Message.sendMessage(player, ConstStorage.getOFFLINE());
                        return;
                    }
                    if (!target.isInvulnerable()) {
                        target.setInvulnerable(true);
                        Message.sendMessage(player, "§a" + target.getDisplayName() + " ist nun im Godmode§8.");
                        return;
                    }
                    Message.sendMessage(player, "§c" + target.getDisplayName() + " ist nun nicht mehr im Godmode§8.");
                    target.setInvulnerable(false);
                    return;
                }
            } else {
                Message.sendMessage(player, ConstStorage.getNOPERM());
            }

            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
