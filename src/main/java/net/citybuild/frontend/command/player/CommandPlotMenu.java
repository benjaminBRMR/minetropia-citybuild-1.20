package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.frontend.inventory.plotmenu.InventoryPlotMenu;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandPlotMenu extends ExecutableCommand {
    public CommandPlotMenu() {
        super("gs", null);

        setAliases(new ArrayList<String>() {
            {
                add("m");
                add("plotmenü");
                add("plotmenu");
                add("grundstücksmenü");
            }
        });
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/gs",
                "§7Beschreibung §8→ §eUm dein Spielererlebnis rund um die Plots zu verbessern besteht die Möglichkeit eine vereinfachte Übersicht zu öffnen.",
                "§7Verwendung §8→ §e/gs §8- §7Öffnet ein Inventar von allen Plot-Einstellungen etc.",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, String[] args) {

        if (args.length == 0) {
            final InventoryPlotMenu inventoryPlotMenu = new InventoryPlotMenu(player);
            ConstStorage.getPlotMenu().put(player, inventoryPlotMenu);
            inventoryPlotMenu.update(0, player);
            return;
        }

        Utility.sendSyntax(player, syntax());

    }
}
