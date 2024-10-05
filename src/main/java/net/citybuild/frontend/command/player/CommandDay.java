package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandDay extends ExecutableCommand {

    public CommandDay() {
        super("day", null);

        setAliases(new ArrayList<>() {{
            add("tag");
        }});
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/day",
                "§7Beschreibung §8→ §eSetzt in deiner Welt, wo du den Befehl eingibst die Tageszeit zu Tag.",
                "§7Verwendung §8→ §e/day §8- §7Setzt die Tageszeit in deiner Welt zu Tag§8.",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.day")) {

            if (args.length == 0) {
                player.getWorld().setTime(0);
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                Message.sendMessage(player, "§7Du hast erfolgreich die Tageszeit zu §eTag §7gesetzt§8.");
                return;
            }
            Utility.sendSyntax(player, syntax());

        }
    }

}
