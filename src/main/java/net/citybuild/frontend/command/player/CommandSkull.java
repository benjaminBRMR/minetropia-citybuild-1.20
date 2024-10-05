package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CommandSkull extends ExecutableCommand {
    public CommandSkull() {
        super("kopf", null);

        setAliases(new ArrayList<String>() {
            {
                add("skull");
                add("head");
            }
        });
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/kopf",
                "§7Beschreibung §8→ §eErstelle einen Kopf von einem anderen Spieler§8.",
                "§7Verwendung §8→ §e/kopf <" + (player.isOp() ? "minecrafturl/spieler>" : "spieler>"),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.skull")) {

            if (args.length == 1) {
                if (Permission.hasPermission(player, "citybuild.skull.minecrafturl")) {
                    final String input = args[0];

                    if (input.length() > 16) {
                        final ItemStack kopf = new ItemCreator(Material.PLAYER_HEAD).setSkullValue(input);
                        player.getInventory().addItem(kopf);
                        Message.sendMessage(player, "§7Du hast erfolgreich einen Kopf bekommen§8.");

                    } else {

                        final ItemStack kopf = new ItemCreator(Material.PLAYER_HEAD).setSkullPlayer(input);
                        player.getInventory().addItem(kopf);
                        Message.sendMessage(player, "§7Du hast den Kopf von §e" + input + " §7bekommen§8.");
                    }


                } else {


                    if (CityBuild.getUserManager().hasCooldown(player.getUniqueId(), "skull")) {
                        Message.sendMessage(player, "§cBitte habe noch etwas Geduld§8!");
                        Message.sendMessage(player, "§7Bereit in§8: §c" + Utility.timeToString(CityBuild.getUserManager().getTime(player.getUniqueId(), "skull"), true));
                        return;
                    }

                    final ItemStack kopf = new ItemCreator(Material.PLAYER_HEAD).setSkullPlayer(args[0]);
                    player.getInventory().addItem(kopf);
                    Message.sendMessage(player, "§7Du hast den Kopf von §e" + args[0] + " §7bekommen§8.");
                    CityBuild.getUserManager().setCooldown(player.getUniqueId(), "skull", TimeUnit.DAYS.toMillis(14));
                }
                return;
            }

            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
