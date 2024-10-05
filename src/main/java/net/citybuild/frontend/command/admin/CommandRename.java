package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandRename extends ExecutableCommand {

    public CommandRename() {
        super("rename", "citybuild.rename");
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/rename",
                "§7Beschreibung §8→ §eGebe einem Item etwas Aesthetik und benenne es um§8!",
                "§7Verwendung §8→ §e/rename <name> §8- §7Benennt das handgehaltene Item um§8."
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.rename")) {


            if (args.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder();
                final ItemStack itemStack = player.getItemInHand();

                for (String arg : args) {
                    stringBuilder.append(Color.translateColorCodes(arg)).append(" ");
                }

                if (itemStack.getType() == Material.AIR) {
                    Message.sendMessage(player, "§cBitte halte das gewünschte Item in der Hand§8!");
                    Utility.playError(player);
                    return;
                }

                final ItemCreator itemCreator = new ItemCreator(itemStack)
                        .setName(stringBuilder.toString());

                player.setItemInHand(itemCreator);
                Message.sendMessage(player, "§7Du hast das §eItem §7erfolgreich umbenannt§8.");
                Utility.playSuccess(player);
                return;
            }
            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
