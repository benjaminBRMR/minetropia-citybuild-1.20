package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandPing extends ExecutableCommand {

    public CommandPing() {
        super("ping", null);
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/ping",
                "§7Beschreibung §8→ §eSehe ein wie deine Latenz zu unserem Server ist.",
                "§7Verwendung §8→ §e/ping - Siehe deine Latenz"
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if(args.length == 0) {
            Message.sendMessage(player, "§7Deine Latenz zu unserem Server beträgt§8: " + Utility.getPing(player));
            return;
        }

        if(args.length == 1) {
            if(Permission.hasPermission(player, "citybuild.ping.other")) {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }
                Message.sendMessage(player, "§7Die Latenz von " + target.getDisplayName() + " §7beträgt§8: " + Utility.getPing(target));
                return;
            }
        }
        Utility.sendSyntax(player, syntax());

    }
}
