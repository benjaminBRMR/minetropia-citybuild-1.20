package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Pair;
import net.citybuild.backend.utility.Serialization;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import net.citybuild.frontend.inventory.cases.InventoryShowcase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CommandCase extends ExecutableCommand {

    public CommandCase() {
        super("case", "citybuild.case");
    }

    private String[] syntax() {
        return new String[]{
                "§8/§ecase erstellen <name>",
                "§8/§ecase löschen <case>",
                "§8/§ecase additem <case> <1-100>",
                "§8/§ecase addinv <case>",
                "§8/§ecase removeitem <case>",
                "§8/§ecase toggle <case>",
                "§8/§ecase setdisplayname <case> <name>",
                "§8/§ecase give <spieler> <case> <anzahl>",
                "§8/§ecase remove <spieler> <Case>",
                "§8/§ecase removeall <case>",
                "§8/§ecase giveall <case>",
                "§8/§ecase showcase <name>",
                "§8/§ecase setblock",
                "§8/§ecase list",
                "§8/§ecase save"
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, String[] args) {


        if (Permission.hasPermission(player, "citybuild.case")) {

            if (args.length == 0) {
                Utility.sendSyntax(player, syntax());
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("setblock")) {
                final Block block = player.getTargetBlock(null, 5);
                CityBuild.getLocationManager().createLocation("case", block.getLocation());
                Message.sendMessage(player, "§7Dieser Block wurde zum §a§lCase-Opening §7gesetzt§8.");

                return;
            }


            if (args.length == 2 && args[0].equalsIgnoreCase("toggle")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                Utility.playError(player);
                CityBuild.getCaseManager().getCase(caseName).setEnabled(!CityBuild.getCaseManager().getCase(caseName).isEnabled());
                Message.sendMessage(player, "§7Du hast die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7vorerst " + (CityBuild.getCaseManager().getCase(caseName).isEnabled() ? "§aaktiviert" : "§cdeaktiviert"));
                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("erstellen")) {

                final String caseName = args[1].toLowerCase();

                if (CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert bereits§8.");
                    Utility.playError(player);
                    return;
                }

                final Case caseA = new Case(
                        caseName,
                        "§e" + caseName,
                        0,
                        true,
                        null,
                        new LinkedHashMap<>()

                );
                CityBuild.getCaseManager().createCase(caseA);
                Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7wurde §aerstellt§8.");
                Message.sendMessage(player, "§7Setze den Displaynamen mit §8/§ecase setdisplayname§8.");
                Message.sendMessage(player, "§2ℹ §aBitte das Wort Kiste mit angeben§8.");
                Utility.playSuccess(player);

                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("löschen")) {

                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }


                try {
                    CityBuild.getCaseManager().deleteEntry(caseName);
                    Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7wurde §cgelöscht§8.");
                    Utility.playSuccess(player);
                } catch (Exception ignored) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7konnte nicht §cgelöscht §7werden§8.");
                }


                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("addinv")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                final LinkedHashMap<ItemStack, Float> tempList = new LinkedHashMap<>();
                for (ItemStack content : player.getInventory().getContents()) {
                    if (content == null || content.getType() == Material.AIR) continue;
                    tempList.put(content, 100F);
                }


                for (Map.Entry<ItemStack, Float> itemStackFloatEntry : tempList.entrySet()) {
                    CityBuild.getCaseManager().getCase(caseName).getCaseItems().put(Serialization.itemStackToBase64(itemStackFloatEntry.getKey()), itemStackFloatEntry.getValue());
                }
                Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7wurde bearbeitet§8.");
                Message.sendMessage(player, "§7Alle Items aus deinem Inventar wurden hinzugefügt§8.");
                Utility.playChange(player);
                return;
            }

            if (args.length >= 3 && args[0].equalsIgnoreCase("setdisplayname")) {

                final String caseName = args[1].toLowerCase();
                final StringBuilder stringBuilder = new StringBuilder();


                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                for (int i = 2; i < args.length; i++) {
                    stringBuilder.append(Color.translateColorCodes(args[i])).append(" ");
                }

                char specificCharacter = ' ';
                String displayName = stringBuilder.toString();

                if (stringBuilder.toString().endsWith(String.valueOf(specificCharacter))) {
                    displayName = displayName.substring(0, displayName.length() - 1);
                }

                CityBuild.getCaseManager().getCase(caseName).setDisplayName(displayName);
                Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7wurde bearbeitet§8.");
                Message.sendMessage(player, "§7Neuer Displayname: §e" + displayName);
                Utility.playChange(player);
                return;
            }


            if (args.length == 2 && args[0].equalsIgnoreCase("showcase")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                if (CityBuild.getCaseManager().getCase(caseName).getCaseItems().isEmpty()) {
                    Message.sendMessage(player, "§cDiese Case enthält aktuell keine Items!");
                    Utility.playError(player);
                    return;
                }

                for (Map.Entry<Pair<Player, String>, Pair<ItemStack, Float>> pairPairEntry : CityBuild.getCaseManager().getChanceEdit().entrySet()) {
                    if (pairPairEntry.getKey().key() == player) {
                        Message.sendMessage(player, "§cDu setzt bereits die Chance eines Kistenitems§8!");
                        Utility.playError(player);
                        return;
                    }
                }


                final InventoryShowcase inventoryShowcase = new InventoryShowcase(player, CityBuild.getCaseManager().getCase(caseName), true);
                ConstStorage.getCaseShowcase().put(player, inventoryShowcase);
                inventoryShowcase.update(0, player, caseName);
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("removeall")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    CityBuild.getUserManager().removeCase(onlinePlayer.getUniqueId(), CityBuild.getCaseManager().getCase(caseName));
                }

                Message.sendMessage(player, "§7Du hast jedem Spieler §e1§8x §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7gestohlen§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("giveall")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    CityBuild.getUserManager().addCase(onlinePlayer.getUniqueId(), CityBuild.getCaseManager().getCase(caseName), 1);
                }

                Message.sendMessageAll("§7Jeder Spieler hat §e1§8x §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7erhalten§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 4 && args[0].equalsIgnoreCase("give")) {
                try {
                    final String caseName = args[2].toLowerCase();
                    final long amount = Long.parseLong(args[3]);

                    if (!CityBuild.getCaseManager().exist(caseName)) {
                        Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                        Utility.playError(player);
                        return;
                    }

                    if (!Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore()) {
                        player.sendMessage(ConstStorage.getUNKNOWN());
                        Utility.playError(player);
                        return;
                    }

                    final Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        Message.sendMessage(player, ConstStorage.getOFFLINE());
                        return;
                    }

                    CityBuild.getUserManager().addCase(target.getUniqueId(), CityBuild.getCaseManager().getCase(caseName), amount);
                    Message.sendMessage(player, "§a§l" + target.getDisplayName() + " §7hat §e" + amount + "§8x §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7erhalten§8.");
                    Message.sendMessage(Objects.requireNonNull(Bukkit.getPlayer(target.getUniqueId())), "§7Du hast §e" + amount + "§8x §e" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7erhalten§8.");
                    Utility.playSuccess(player);
                    return;
                } catch (NumberFormatException ignored) {

                }
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("remove")) {
                final String caseName = args[2].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                if (!Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore()) {
                    player.sendMessage(ConstStorage.getUNKNOWN());
                    Utility.playError(player);
                    return;
                }

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }


                if (CityBuild.getUserManager().hasCase(target.getUniqueId(), CityBuild.getCaseManager().getCase(caseName))) {
                    CityBuild.getUserManager().removeCase(target.getUniqueId(), CityBuild.getCaseManager().getCase(caseName));
                    Message.sendMessage(player, "§7Du hast §a§l" + target.getDisplayName() + " §e1§8x §7" + CityBuild.getCaseManager().getCase(caseName).getDisplayName() + " §7gestohlen§8.");
                    Utility.playSuccess(player);
                } else {
                    Utility.playError(player);
                    Message.sendMessage(player, "§a§l" + target.getDisplayName() + " §cbesitzt diese Case nicht§8.");
                }

                return;
            }


            if (args.length == 3 && args[0].equalsIgnoreCase("additem")) {
                final String caseName = args[1].toLowerCase();


                if (player.getItemInHand().getType() == Material.AIR) {
                    Message.sendMessage(player, "§cBitte halte das gewünschte Item in der Hand§8!");
                    Utility.playError(player);
                    return;
                }

                try {
                    final float chance = Float.parseFloat(args[2]);

                    if (!CityBuild.getCaseManager().exist(caseName)) {
                        Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                        Utility.playError(player);
                        return;
                    }

                    if (chance < 0 || chance > 100) {
                        Message.sendMessage(player, "§cBitte gebe eine Chance von 1-100 an§8.");
                        Utility.playError(player);
                        return;
                    }

                    CityBuild.getCaseManager().getCase(caseName).getCaseItems().put(Serialization.itemStackToBase64(player.getItemInHand()), chance);
                    Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getName() + " §7wurde bearbeitet§8.");
                    Message.sendMessage(player, "§7Neues Item: §a" +
                            (player.getItemInHand().getItemMeta().hasDisplayName() ? player.getItemInHand().getItemMeta().getDisplayName() : player.getItemInHand().getType()));
                    Message.sendMessage(player, "§7Chance: §a" + chance + "%");
                    Utility.playChange(player);
                } catch (NumberFormatException ignored) {
                    Message.sendMessage(player, "§cBitte gebe eine gültige Chance an§8!");
                    Utility.playError(player);

                }
                return;
            }


            if (args.length == 2 && args[0].equalsIgnoreCase("removeitem")) {
                final String caseName = args[1].toLowerCase();

                if (!CityBuild.getCaseManager().exist(caseName)) {
                    Message.sendMessage(player, "§cDiese Case existiert nicht§8.");
                    Utility.playError(player);
                    return;
                }

                if (player.getItemInHand().getType() == Material.AIR) {
                    Message.sendMessage(player, "§cBitte halte das gewünschte Item in der Hand§8!");
                    Utility.playError(player);
                    return;
                }

                if (!CityBuild.getCaseManager().getCase(caseName).getCaseItems().containsKey(Serialization.itemStackToBase64(player.getItemInHand()))) {
                    Message.sendMessage(player, "§cDiese Case enthält das gewünschte Item nicht§8!");
                    Utility.playError(player);
                    return;
                }

                CityBuild.getCaseManager().getCase(caseName).getCaseItems().remove(Serialization.itemStackToBase64(player.getItemInHand()));
                Message.sendMessage(player, "§7Die Case §e" + CityBuild.getCaseManager().getCase(caseName).getName() + " §7wurde bearbeitet§8.");
                Message.sendMessage(player, "§7Entferntes Item: §a" +
                        (player.getItemInHand().getItemMeta().hasDisplayName() ? player.getItemInHand().getItemMeta().getDisplayName() : player.getItemInHand().getType()));
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("save")) {

                CityBuild.getCaseManager().saveAll();
                Message.sendMessage(player, "§7Alle Cases wurde erfolgreich §7gespeichert§8.");
                Utility.playSuccess(player);
                return;
            }

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {


                if (CityBuild.getCaseManager().getCases().isEmpty()) {
                    Message.sendMessage(player, "§cAktuell existieren keine Cases§8!");
                    return;
                }

                for (Case aCase : CityBuild.getCaseManager().getCases()) {
                    Message.sendMessage(player, "§7Case: §a" + aCase.getName() + " §8(§e" + aCase.getDisplayName() + "§8) §8» §2" + aCase.getCaseItems().size() + " §7Items");
                }
                Utility.playSuccess(player);
                return;
            }
            Utility.sendSyntax(player, syntax());


        }
    }


}
