package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CommandHat extends ExecutableCommand {
    public CommandHat() {
        super("hat", null);

        setAliases(new ArrayList<>() {{
            add("head");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/hat",
                "§7Beschreibung §8→ §eSetze das Item in deiner Hand auf deinem Kopf§8!",
                "§7Verwendung §8→ §e/hat " + (Permission.hasPermission(player, "citybuild.hat.other") ? "<remove> <spieler>" : "§8- §7Setze das Item auf deinem Kopf§8."),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.hat")) {
            if (args.length == 0) {
                final ItemStack itemStack = player.getItemInHand();
                if (itemStack.getType() == Material.AIR) {
                    Message.sendMessage(player, "§cBitte halte ein gültiges Item in deiner Hand§8.");
                    return;
                }
                if (player.getInventory().getHelmet() != null)
                    player.getInventory().addItem(player.getInventory().getHelmet());
                final ItemStack newItem = new ItemStack(itemStack);
                newItem.setAmount(1);
                player.getInventory().setHelmet(newItem);
                Utility.removeHand(player);
                Message.sendMessage(player, "§aDu trägst nun dein Item auf deinem Kopf§8.");
                return;
            }

            if (Permission.hasPermission(player, "citybuild.hat.other")) {
                if (args.length == 2) {
                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Message.sendMessage(player, ConstStorage.getOFFLINE());
                        return;
                    }

                    target.getInventory().setHelmet(null);
                    Message.sendMessage(player, "§aDu hast den Kopf von " + target.getDisplayName() + " §aentfernt§8.");
                }
                return;
            }
            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
