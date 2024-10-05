package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.frontend.inventory.perk.InventoryPerk;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandPerk extends ExecutableCommand {

    public CommandPerk() {
        super("perk", null);
        setAliases(new ArrayList<>() {{
            add("perks");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/perks",
                "§7Beschreibung §8→ §eSichere dir viele tolle Vorteile indem du unserer Perks erwerbst!",
                "§7Verwendung §8→ §e/perks §8- §7Öffnet ein Inventar von unseren Perks"
        };
    }


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (args.length == 0) {
            final InventoryPerk inventoryPerk = new InventoryPerk(player);
            ConstStorage.getPerks().put(player, inventoryPerk);
            inventoryPerk.update(0, player);
            return;
        }

        Utility.sendSyntax(player, syntax(player));
        return;
    }
}
