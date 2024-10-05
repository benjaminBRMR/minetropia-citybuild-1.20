package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandNight extends ExecutableCommand {

    public CommandNight() {
        super("night", null);

        setAliases(new ArrayList<>() {{
            add("nacht");
        }});
    }

    private String[] syntax() {
        return new String[]{
                "§7Befehl §8→ §e/night",
                "§7Beschreibung §8→ §eSetzt in deiner Welt, wo du den Befehl eingibst die Tageszeit zu Nacht.",
                "§7Verwendung §8→ §e/night §8- §7Setzt die Tageszeit in deiner Welt zu Nacht§8.",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.night")) {

            if (args.length == 0) {
                player.getWorld().setTime(13000);
                player.getWorld().setThundering(false);
                Message.sendMessage(player, "§7Du hast erfolgreich die Tageszeit zu §eNacht §7gesetzt§8.");
                return;
            }
            Utility.sendSyntax(player, syntax());

        }
    }
}
