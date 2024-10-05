package net.citybuild.backend.manager;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsesManager {

    public int getUses(final ItemStack rod) {
        if (rod.hasItemMeta() && rod.getItemMeta().hasLore()) {
            ItemMeta itemMeta = rod.getItemMeta();
            List<String> lore = itemMeta.getLore();
            String rodName = ChatColor.stripColor(itemMeta.getDisplayName());
            if (lore != null) {
                for (String loreLine : lore) {
                    if (loreLine.contains("Verwendungen")) {
                        Pattern pattern = Pattern.compile("§8» §7Verwendungen§8: §e(\\d+)§8/§6(\\d+)");
                        Matcher matcher = pattern.matcher(loreLine);

                        if (matcher.find()) {
                            try {
                                int currentUses = Integer.parseInt(matcher.group(1));
                                return currentUses;
                            } catch (NumberFormatException exception) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public void updateUsesLore(final @NonNull Player player, ItemStack rod, Map<String, Integer> maxUsesMap) {
        if (rod.hasItemMeta() && rod.getItemMeta().hasLore()) {
            ItemMeta itemMeta = rod.getItemMeta();
            String rodName = ChatColor.stripColor(itemMeta.getDisplayName());
            if (maxUsesMap.containsKey(rodName)) {
                int maxUses = maxUsesMap.get(rodName);
                int currentUses = getUses(rod);
                Pattern pattern = Pattern.compile("§8» §7Verwendungen§8: §e(\\d+)§8/§6(\\d+)");
                List<String> lore = itemMeta.getLore();
                currentUses--;
                if (lore.size() > 1 && lore.get(1).contains("Verwendungen")) {
                    for (int i = 0; i < lore.size(); i++) {
                        String loreLine = lore.get(i);
                        Matcher matcher = pattern.matcher(loreLine);

                        if (matcher.find()) {
                            loreLine = matcher.replaceAll("§8» §7Verwendungen§8: §e" + currentUses + "§8/§6" + maxUses);
                            lore.set(i, loreLine);
                            break;
                        }
                    }

                    itemMeta.setLore(lore);
                    rod.setItemMeta(itemMeta);

                    if(currentUses == 50) {
                        Message.sendMessage(player, "§cDein/e " + itemMeta.getDisplayName() +  " §ckann nur");
                        Message.sendMessage(player, "§cnoch §e§l50 §cweitere Male verwendet");
                        Message.sendMessage(player, "§cwerden, bevor es aufgebraucht wird.");
                    }

                    if(currentUses == 10) {
                        Message.sendMessage(player, "§cDein/e " + itemMeta.getDisplayName() +  " §ckann nur");
                        Message.sendMessage(player, "§cnoch §e§l10 §cweitere Male verwendet");
                        Message.sendMessage(player, "§cwerden, bevor es aufgebraucht wird.");
                    }

                    if (currentUses <= 0) {
                        Message.sendMessage(player, "§cDein/e " + itemMeta.getDisplayName() + " §cist nun aufgebraucht.");
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 100, 14);
                        player.getInventory().remove(rod);
                        player.updateInventory();

                    }
                }
            }
        }
    }


    public void giveFishingReward(Player player, String itemNameWithoutColor) {
        int randomNumber = Utility.random.nextInt(100);
        ItemStack caughtMaterial;

        if (itemNameWithoutColor.equalsIgnoreCase("Obsidian Angel")) {
            if (randomNumber < 80) {
                caughtMaterial = new ItemCreator(Material.OBSIDIAN).setName("§eObsidian");
            } else {
                caughtMaterial = new ItemCreator(Material.CRYING_OBSIDIAN).setName("§eWeinender Obsidian");
            }
        } else {
            caughtMaterial = new ItemCreator(Material.TROPICAL_FISH);
        }

        player.getInventory().addItem(caughtMaterial);
    }
}
