package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.entity.Player;

public class CommandSpeed extends ExecutableCommand {
    public CommandSpeed() {
        super("speed", null);
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/speed",
                "§7Beschreibung §8→ §eSetze deine Geschwindigkeit um§8!",
                "§7Verwendung §8→ §e/rename <walk, fly> <float>§8- §7Legt deine Geschwindigkeit fest§8."
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.speed")) {
            if (args.length == 0) {
                Message.sendMessage(player, "§7Aktuelle Gehgeschwindigkeit§8: §e" + player.getWalkSpeed());
                Message.sendMessage(player, "§7Aktuelle Fluggeschwindigkeit§8: §e" + player.getFlySpeed());
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("reset")) {
                player.setWalkSpeed(0.2f);
                player.setFlySpeed(0.1f);
                Message.sendMessage(player, "§aDeine Geh- und Fluggeschwindigkeit wurde zurückgesetzt.");
                return;
            }

            if (args.length == 2) {
                try {
                    float speed = Float.parseFloat(args[1]);
                    if (!(speed >= -1 && speed <= 1)) {
                        Message.sendMessage(player, "§cDie Geschwindigkeit muss zwischen -1 und 1 liegen.");
                        return;
                    }
                    if (args[0].equalsIgnoreCase("walk")) {
                        player.setWalkSpeed(speed);
                        Message.sendMessage(player, "§aDeine Gehgeschwindigkeit wurde auf §e" + speed + " §agesetzt.");
                        return;
                    }
                    if (args[0].equalsIgnoreCase("fly")) {
                        player.setFlySpeed(speed);
                        Message.sendMessage(player, "§aDeine Fluggeschindigkeit wurde auf §e" + speed + " §agesetzt.");
                        return;
                    }
                    Utility.sendSyntax(player, syntax());
                    return;
                } catch (NumberFormatException exception) {
                    Message.sendMessage(player, "§cUngültige Geschwindigkeitsangabe. Verwende eine Zahl!");
                }
            }
            Utility.sendSyntax(player, syntax());
        }
    }
}
