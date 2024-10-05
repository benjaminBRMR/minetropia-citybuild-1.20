package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandGamemode extends ExecutableCommand {
    public CommandGamemode() {
        super("gm", null);

        setAliases(new ArrayList<>() {{
            add("gamemode");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/gamemode <survial, creative, adventure, spectator>",
                "§7Beschreibung §8→ §eAls Teammitglied hast du Rechte auf bestimmte Gamemode's! Siehe #team-rechte im Discord.",
                "§7Verwendung §8→ §e/gamemode <survial, creative, adventure, spectator> " + (player.hasPermission("citybuild.gamemode.other") ? "<spieler>" : ""),
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, String[] args) {


        if (Permission.hasPermission(player, "citybuild.gamemode")) {

            if (args.length == 1 && args[0].equalsIgnoreCase("creative") && Permission.hasPermission(player, "citybuild.gamemode.creative")) {
                Message.sendMessage(player, "§aDein Spielmodus wurde auf §a§lKreativmodus §agesetzt§8.");
                player.setGameMode(GameMode.CREATIVE);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("survival") && Permission.hasPermission(player, "citybuild.gamemode.survival")) {
                Message.sendMessage(player, "§aDein Spielmodus wurde auf §a§lÜberlebensmodus §agesetzt§8.");
                player.setGameMode(GameMode.SURVIVAL);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("adventure") && Permission.hasPermission(player, "citybuild.gamemode.adventure")) {
                Message.sendMessage(player, "§aDein Spielmodus wurde auf §a§lAbenteuermodus §agesetzt§8.");
                player.setGameMode(GameMode.ADVENTURE);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("spectator") && Permission.hasPermission(player, "citybuild.gamemode.spectator")) {
                Message.sendMessage(player, "§aDein Spielmodus wurde auf §a§lZuschauermodus §agesetzt§8.");
                player.setGameMode(GameMode.SPECTATOR);
                return;
            }

            if (args.length == 2 && Permission.hasPermission(player, "citybuild.gamemode.other")) {
                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                if (target == player) {
                    Message.sendMessage(player, "§cBitte benutze dafür, den Befehl ohne Argument.");
                    return;
                }


                if (args[0].equalsIgnoreCase("creative")) {
                    Message.sendMessage(player, "§aDer Spielmodus von " + target.getDisplayName() + " §7wurde auf §a§lKreativmodus §agesetzt§8.");
                    target.setGameMode(GameMode.CREATIVE);
                    return;
                }

                if (args[0].equalsIgnoreCase("survival")) {
                    Message.sendMessage(player, "§aDer Spielmodus von " + target.getDisplayName() + " §7wurde auf §a§lÜberlebensmodus §agesetzt§8.");
                    target.setGameMode(GameMode.SURVIVAL);
                    return;
                }

                if (args[0].equalsIgnoreCase("adventure")) {
                    Message.sendMessage(player, "§aDer Spielmodus von " + target.getDisplayName() + " §7wurde auf §a§lAbenteuermodus §agesetzt§8.");
                    target.setGameMode(GameMode.ADVENTURE);
                    return;
                }

                if (args[0].equalsIgnoreCase("spectator")) {
                    Message.sendMessage(player, "§aDer Spielmodus von " + target.getDisplayName() + " §7wurde auf §a§lZuschauermodus §agesetzt§8.");
                    target.setGameMode(GameMode.SPECTATOR);
                    return;
                }
            }
            Utility.sendSyntax(player, syntax(player));
            return;
        }
    }
}
