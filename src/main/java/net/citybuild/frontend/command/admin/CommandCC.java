package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.user.User;
import net.citybuild.backend.user.creatorcode.CreatorCode;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.frontend.inventory.creatorcode.InventoryCreatorCode;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandCC extends ExecutableCommand {

    public CommandCC() {
        super("creatorcode", null);
    }

    private String[] syntax() {
        return new String[]{
                "§8/§ecreatorcode erstellen <code> <creator>",
                "§8/§ecreatorcode löschen <code>",
                "§8/§ecreatorcode list",
                "§8/§ecreatorcode save",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (player.hasPermission("citybuild.creatorcode")) {

            if (args.length == 0) {
                player.openInventory(InventoryCreatorCode.getCreatorCodeInventory(player));
                return;
            }


            if (args.length == 3 && args[0].equalsIgnoreCase("erstellen")) {

                final String creatorCodeCode = args[1];
                final OfflinePlayer creator = Bukkit.getOfflinePlayer(args[2]);

                if (!creator.hasPlayedBefore()) {
                    Message.sendMessage(player, ConstStorage.getUNKNOWN());
                    return;
                }

                if (CityBuild.getCcManager().hasCC(creator.getUniqueId())) {
                    Message.sendMessage(player, "§cDieser Creator hat bereits einen Creator-Code§8!");
                    Utility.playError(player);
                    return;
                }

                if (CityBuild.getCcManager().existCC(creatorCodeCode)) {
                    Message.sendMessage(player, "§cDieser Creator-Code existiert bereits§8!");
                    Utility.playError(player);
                    return;
                }

                final CreatorCode creatorCode = new CreatorCode(creatorCodeCode, creator.getUniqueId(), 0, 0, new ArrayList<>());
                CityBuild.getCcManager().createCC(creatorCode);
                Message.sendMessage(player, "§7Der Creator-Code §e" + creatorCodeCode + " §7wurde erstellt§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("löschen")) {

                final String creatorCodeCode = args[1];

                if (!CityBuild.getCcManager().existCC(creatorCodeCode)) {
                    Message.sendMessage(player, "§cDieser Creator-Code existiert nicht§8!");
                    Utility.playError(player);
                    return;
                }

                for (UUID use : CityBuild.getCcManager().getCreatorCode(creatorCodeCode).getUses()) {
                    final User user = CityBuild.getUserManager().handle(use);
                    user.setCreatorCode(null);
                }

                CityBuild.getCcManager().deleteEntry(creatorCodeCode);
                Message.sendMessage(player, "§cDer Creator-Code §e" + creatorCodeCode + " §cwurde gelöscht§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {

                if (CityBuild.getCcManager().getCreatorCodes().isEmpty()) {
                    Message.sendMessage(player, "§cAktuell existieren keine Creator-Codes§8!");
                    Utility.playError(player);
                    return;
                }

                for (CreatorCode creatorCode : CityBuild.getCcManager().getCreatorCodes()) {
                    Message.sendMessage(player, "§7Code§8: §e" + creatorCode.getCode() + " §8● §7Creator§8: §e" + Bukkit.getOfflinePlayer(creatorCode.getCreator()).getName() + " §8● §7Unterstützer§8: §e" + creatorCode.getUses().size());
                }
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("save")) {
                CityBuild.getCcManager().saveAll();
                Message.sendMessage(player, "§7Alle Creator-Codes wurde erfolgreich §7gespeichert§8.");
                Utility.playSuccess(player);
                return;
            }

            for (String syntax : syntax()) {
                Message.sendMessage(player, syntax.toLowerCase());
            }
        } else {
            player.openInventory(InventoryCreatorCode.getCreatorCodeInventory(player));
        }

    }
}
