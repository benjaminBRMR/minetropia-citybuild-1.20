package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandFly extends ExecutableCommand {
    public CommandFly() {
        super("fly", null);
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/fly",
                "§7Beschreibung §8→ §eAls Teammitglied/Creator hast du die Rechte auf /fly, welche dir ermöglichen dauerhaft zu fliegen§8.",
                "§7Verwendung §8→ §e/fly " + (player.hasPermission("citybuild.fly.other") ? "<spieler>" : ""),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {


        if (Permission.hasPermission(player, "citybuild.fly")) {

            if (args.length == 0) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                    Message.sendMessage(player, "§aDu kannst nun fliegen§8.");
                    return;
                }
                Message.sendMessage(player, "§cDu kannst nun nicht mehr fliegen§8.");
                player.setAllowFlight(false);
                return;
            }


            if (Permission.hasPermission(player, "citybuild.fly.other")) {
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
                    if (!target.getAllowFlight()) {
                        target.setAllowFlight(true);
                        Message.sendMessage(player, "§a" + target.getDisplayName() + " kann nun fliegen§8.");
                        return;
                    }
                    Message.sendMessage(player, "§c" + target.getDisplayName() + " kann nun nicht mehr fliegen§8.");
                    target.setAllowFlight(false);
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
