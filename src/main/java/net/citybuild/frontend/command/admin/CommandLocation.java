package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.location.Location;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandLocation extends ExecutableCommand {

    public CommandLocation() {
        super("location", "citybuild.location");

        setAliases(new ArrayList<>() {{
            add("loc");
        }});
    }

    private String[] syntax() {
        return new String[]{
                "§8/§elocation set <name>",
                "§8/§elocation delete <name>",
                "§8/§elocation list",
                "§8/§elocation save",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.location")) {

            if (args.length == 2 && args[0].equalsIgnoreCase("set")) {

                final String locationName = args[1];
                CityBuild.getLocationManager().createLocation(locationName, player.getLocation());
                Message.sendMessage(player, "§7Die Location §e" + locationName + " §7wurde abgespeichert§8.");
                Utility.playSuccess(player);

                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {

                final String locationName = args[1];

                if (!CityBuild.getLocationManager().existLocation(locationName)) {
                    Message.sendMessage(player, "§cDie angegebene Location wurde nicht gefunden§8.");
                    Utility.playError(player);
                    return;
                }

                CityBuild.getLocationManager().deleteEntry(locationName);
                Message.sendMessage(player, "§7Die Location §e" + locationName + " §7wurde §cgelöscht§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                if (CityBuild.getLocationManager().getLocations().isEmpty()) {
                    Message.sendMessage(player, "§cAktuell existieren keine Locations§8.");
                    Utility.playError(player);
                    return;
                }

                for (Location location : CityBuild.getLocationManager().getLocations()) {
                    Message.sendMessage(player, location.getName() + " §8(§c§o" + location.getLocationString() + "§8)");
                }
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("save")) {

                CityBuild.getLocationManager().saveAll();
                Message.sendMessage(player, "§7Alle Locations wurde erfolgreich §7gespeichert§8.");
                Utility.playSuccess(player);
                return;
            }


            for (String syntax : syntax()) {
                Message.sendMessage(player, syntax.toLowerCase());
            }


        }

    }
}
