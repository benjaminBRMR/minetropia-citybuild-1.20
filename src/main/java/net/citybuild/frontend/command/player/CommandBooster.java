package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.frontend.inventory.booster.InventoryBooster;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.EnumSet;

public class CommandBooster extends ExecutableCommand {

    public CommandBooster() {
        super("booster", null);
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/booster",
                "§7Beschreibung §8→ §eDu möchtest z.B. fliegen§8? §eDann mit unseren Boostern schnell und einfach§8!",
                "§7Verwendung §8→ §e/booster §8- §7Öffnet das Booster Inventar"
        };
    }


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (args.length == 0) {
            player.openInventory(InventoryBooster.getBoosterInventory(player));
            return;
        }

        if (args.length == 1) {

            final User user = CityBuild.getUserManager().handle(player.getUniqueId());

            if (args[0].equalsIgnoreCase("fly")) {
                final Booster booster = Booster.FLY;

                if (user.getBoosters() == 0) {
                    Message.sendMessage(player, "§cDu besitzt keinen §f§l" + booster + " §cBooster§8.");
                    return;
                }

                if (CityBuild.getBoosterManager().getMultiplier(booster) >= booster.getMaxMultiplier()) {
                    Message.sendMessage(player, "§cDieser Booster ist bereits auf der Max. Stufe§8.");
                    return;
                }

                CityBuild.getBoosterManager().activateBooster(booster);
                CityBuild.getBoosterManager().getBooster().put(player.getUniqueId(), booster);
                user.setBoosters((user.getBoosters()) - 1);
                Message.sendMessageAll(String.format(CityBuild.getBoosterManager().getActivated(), booster.name(), player.getDisplayName(), CityBuild.getBoosterManager().getMultiplier(booster)));
                Utility.playSoundAll(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH);
                return;
            }

            if (args[0].equalsIgnoreCase("xp")) {
                final Booster booster = Booster.XP;

                if (user.getBoosters() == 0) {
                    Message.sendMessage(player, "§cDu besitzt keinen §f§l" + booster + " §cBooster§8.");
                    return;
                }

                if (CityBuild.getBoosterManager().getMultiplier(booster) >= booster.getMaxMultiplier()) {
                    Message.sendMessage(player, "§cDieser Booster ist bereits auf der Max. Stufe§8.");
                    return;
                }

                CityBuild.getBoosterManager().activateBooster(booster);
                CityBuild.getBoosterManager().getBooster().put(player.getUniqueId(), booster);
                user.setBoosters((user.getBoosters()) - 1);
                Message.sendMessageAll(String.format(CityBuild.getBoosterManager().getActivated(), booster.name(), player.getDisplayName(), CityBuild.getBoosterManager().getMultiplier(booster)));
                Utility.playSoundAll(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH);
                return;
            }

            if (args[0].equalsIgnoreCase("break")) {
                final Booster booster = Booster.BREAK;

                if (user.getBoosters() == 0) {
                    Message.sendMessage(player, "§cDu besitzt keinen §f§l" + booster + " §cBooster§8.");
                    return;
                }

                if (CityBuild.getBoosterManager().getMultiplier(booster) >= booster.getMaxMultiplier()) {
                    Message.sendMessage(player, "§cDieser Booster ist bereits auf der Max. Stufe§8.");
                    return;
                }

                CityBuild.getBoosterManager().activateBooster(booster);
                CityBuild.getBoosterManager().getBooster().put(player.getUniqueId(), booster);
                user.setBoosters((user.getBoosters()) - 1);
                Message.sendMessageAll(String.format(CityBuild.getBoosterManager().getActivated(), booster.name(), player.getDisplayName(), CityBuild.getBoosterManager().getMultiplier(booster)));
                Utility.playSoundAll(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH);
                return;
            }

            if (args[0].equalsIgnoreCase("ultra")) {

                if (user.getUltraBoosters() == 0) {
                    Message.sendMessage(player, "§cDu besitzt keinen §f§lUltra Booster§8.");
                    return;
                }
                EnumSet.allOf(Booster.class)
                        .forEach(booster -> CityBuild.getBoosterManager().activateBooster(booster, booster.getMaxMultiplier()));

                user.setUltraBoosters((user.getUltraBoosters()) - 1);
                Message.sendMessageAll("§7Der Spieler §e" + player.getDisplayName() + " §7hat einen §f§lUltra Booster §aaktiviert§8.");
                Utility.playSoundAll(Sound.BLOCK_END_PORTAL_SPAWN);
                return;
            }

        }

        if (args.length > 1) {
            Utility.sendSyntax(player, syntax(player));
            return;
        }

        Message.sendMessage(player, "§cBitte gebe einen verfügbaren Booster an§8!");
        EnumSet.allOf(Booster.class)
                .forEach(booster -> {
                    Message.sendMessage(player, "§8/§fbooster §f" + booster.name());
                });

    }
}
