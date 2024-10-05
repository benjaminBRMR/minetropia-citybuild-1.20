package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandClear extends ExecutableCommand {
    public CommandClear() {
        super("clear", null);
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/clear",
                "§7Beschreibung §8→ §eLeere dein aktuelles Inventar§8.",
                "§7Verwendung §8→ §e/clear" + (player.hasPermission("citybuild.clear.other") ? "<spieler>" : ""),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.clear")) {

            if (args.length == 0) {
                if (Utility.hasEmptyInventory(player)) {
                    Message.sendMessage(player, "§cDein Inventar ist bereits leer§8.");
                    return;
                }
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                Message.sendMessage(player, "§aDein Inventar wurde erfolgreich geleert§8.");
                return;
            }

            if (args.length == 1 && Permission.hasPermission(player, "citybuild.clear.other")) {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }
                if (target == player) {
                    Message.sendMessage(player, "§cBitte benutze dafür, den Befehl ohne Argument.");
                    return;
                }
                if (Utility.hasEmptyInventory(target)) {
                    Message.sendMessage(player, "§cDas Inventar von " + target.getDisplayName() + " §cist bereits leer§8.");
                    return;
                }
                target.getInventory().clear();
                target.getInventory().setArmorContents(null);
                Message.sendMessage(player, "§aDu hast das Inventar von " + target.getDisplayName() + " §aerfolreich geleert§8.");
                return;
            }
            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
