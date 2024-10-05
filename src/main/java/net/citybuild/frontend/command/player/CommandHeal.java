package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHeal extends ExecutableCommand {
    public CommandHeal() {
        super("heal", null);

        setAliases(new ArrayList<>() {{
            add("feed");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/heal",
                "§7Beschreibung §8→ §eErfrischt deine Herzen und stillt deinen Hunger völlig aufs Neue§8.",
                "§7Verwendung §8→ §e/heal " + (Permission.hasPermission(player, "citybuild.heal.other") ? "<spieler>" : ""),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.heal")) {
            if (args.length == 0) {
                player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 1);
                Message.sendMessage(player, "§aDu wurdest erfolgreich geheilt§8.");
                player.setHealth(20);
                player.setFoodLevel(20);
                return;
            }

            if (args.length == 1) {
                if (Permission.hasPermission(player, "citybuild.heal.other")) {

                    final Player target = Bukkit.getPlayer(args[0]);

                    if (target == null) {
                        Message.sendMessage(player, ConstStorage.getOFFLINE());
                        return;
                    }

                    player.playSound(player.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 1);
                    Message.sendMessage(player, "§aDu hast " + target.getDisplayName() + " §aerfolgreich geheilt§8.");
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    return;
                }
                return;

            }

            Utility.sendSyntax(player, syntax(player));
            return;
        }

    }
}
