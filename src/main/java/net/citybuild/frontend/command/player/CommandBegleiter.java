package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.begleiter.Begleiter;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import net.citybuild.frontend.inventory.begleiter.InventoryBegleiter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.EnumSet;

public class CommandBegleiter extends ExecutableCommand {

    public CommandBegleiter() {
        super("begleiter", null);

        setAliases(new ArrayList<>() {{
            add("pets");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/begleiter",
                "§7Beschreibung §8→ §eSei etwas besonderes mit unseren Begleitern!",
                "§7Verwendung §8→ §e/begleiter " + (Permission.hasPermission(player, "citybuild.begleiter.give") ? "<give> <spieler> <begleiter>" : "§8- §7Öffnet das Begleiter Inventar")
        };
    }


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (args.length == 0) {
            final InventoryBegleiter inventoryBegleiter = new InventoryBegleiter(player);
            ConstStorage.getBegleiter().put(player, inventoryBegleiter);
            inventoryBegleiter.update(0, player);
            return;
        }

        if (Permission.hasPermission(player, "citybuild.begleiter.give")) {

            if (args.length >= 2 && args[0].equalsIgnoreCase("give")) {

                try {
                    final Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        Message.sendMessage(player, ConstStorage.getOFFLINE());
                        return;
                    }

                    final StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 2; i < args.length; i++) {
                        stringBuilder.append(args[i]).append(" ");

                    }

                    char specificCharacter = ' ';
                    String displayName = stringBuilder.toString();

                    if (stringBuilder.toString().endsWith(String.valueOf(specificCharacter))) {
                        displayName = displayName.substring(0, displayName.length() - 1);
                    }
                    System.out.println(displayName);
                    final Begleiter begleiter = Begleiter.getBegleiterByDisplayName(displayName);


                    if (begleiter == null) {
                        EnumSet.allOf(Begleiter.class).forEach(all -> {
                            Message.sendMessage(player, "§e" + all.getDisplayName());
                        });
                        Utility.playError(player);
                        return;
                    }

                    if (CityBuild.getUserManager().hasBegleiter(target.getUniqueId(), begleiter)) {
                        Message.sendMessage(player, target.getDisplayName() + " §cbesitzt diesen Begleiter bereits§8!");
                        Utility.playError(player);
                        return;
                    }

                    CityBuild.getUserManager().giveBegleiter(target.getUniqueId(), begleiter);
                    Message.sendMessage(player, target.getDisplayName() + " §abesitzt nun diesen Begleiter§8.");
                    Utility.playSuccess(player);


                } catch (Exception ignored) {
                    EnumSet.allOf(Begleiter.class).forEach(all -> Message.sendMessage(player, "§e" + all.getDisplayName()));
                    Utility.playError(player);
                }
            }
        }
    }
}

