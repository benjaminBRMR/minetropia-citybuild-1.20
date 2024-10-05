package net.citybuild.frontend.inventory.booster;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class InventoryBuyBooster {

    public Inventory getBoosterBuyInventory(final @NonNull Player player) {

        final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §f§lKaufe hier deine Booster!");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack back = new ItemCreator(Material.SPRUCE_DOOR).setName("§cZurück");

        Utility.fillInventory(inventory, glass);
        inventory.setItem(18, back);

        final ItemStack booster1x = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§a§l1§8x §f§lBooster")
                .setLore(
                        "§8» §7Preis§8: §e200 Bits"
                );

        final ItemStack booster5x = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§a§l5§8x §f§lBooster")
                .setLore(
                        "§8» §7Preis§8: §e1.000 Bits"
                );

        final ItemStack booster10x = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§a§l10§8x §f§lBooster")
                .setLore(
                        "§8» §7Preis§8: §e2.000 Bits"
                );

        final ItemStack booster15x = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§a§l15§8x §f§lBooster")
                .setLore(
                        "§8» §7Preis§8: §e3.000 Bits"
                );

        final ItemStack booster20x = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§a§l20§8x §f§lBooster")
                .setLore(
                        "§8» §7Preis§8: §e4.000 Bits"
                );

        inventory.setItem(11, booster1x);
        inventory.setItem(12, booster5x);
        inventory.setItem(13, booster10x);
        inventory.setItem(14, booster15x);
        inventory.setItem(15, booster20x);


        return inventory;
    }
}
