package net.citybuild.frontend.inventory.invite;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.manager.UserManager;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class InventoryAffiliateShop {

    public Inventory getAffiliateShopInventory(final @NonNull Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 45, "§8» §b§lAffiliate Shop");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        Utility.fillInventory(inventory, glass);

        final ItemStack grayglass = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("§8Ideen?");

        final User user = new UserManager().handle(player.getUniqueId());

        final ItemStack punkteÜbersicht = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("7595260db2b28dea72c2b4252a186d41d694b0d7c859b2aa7b891c31f5989db8")
                .setName("§b§lPunkte Übersicht")
                .setLore(
                        "§8» §7Aktuelle Punkte§8: §b§l" + (CityBuild.getInviteManager().hasInvite(player.getUniqueId()) ? NumberFormat.getInstance().format(CityBuild.getInviteManager().getInvite(player.getUniqueId()).getPoints()) : 0),
                        "",
                        "  §7Lade Freunde auf den Server ein und",
                        "  §7wenn diese dich bei §b§l/invite " + player.getName() + " §7angeben",
                        "  §7erhältst du Punkte für den Affiliate Shop§8!"
                );


        final ItemStack boosterBuy = new ItemCreator(Material.DRAGON_BREATH)
                .setName("§b§l1§8x §f§lBooster").setLore("§8» §7Preis§8: §e3 Punkte");

        final ItemStack miniVoteKisteBuy = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("4cb3acdc11ca747bf710e59f4c8e9b3d949fdd364c6869831ca878f0763d1787")
                .setName("§b§l1§8x " + Color.translateColorCodes("&#EF32D9&lM&#E743DC&li&#DE54DF&ln&#D665E2&li &#CD76E5&lV&#C587E8&lo&#BC99EB&lt&#B4AAEE&le &#ABBBF1&lK&#A3CCF4&li&#9ADDF7&ls&#92EEFA&lt&#89FFFD&le"))
                .setLore("§8» §7Preis§8: §e5 Punkte");

        final ItemStack ultraBoosterBuy = new ItemCreator(Material.ENDER_DRAGON_SPAWN_EGG)
                .setName("§b§l1§8x §a§lUltra §f§lBooster")
                .setLore("§8» §7Preis§8: §e10 Punkte");

        final ItemStack adminItemBuy = new ItemCreator(Material.HEART_POTTERY_SHERD)
                .setName("§b§l1§8x §f§lScherbe der Dankbarkeit")
                .addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2)
                .setLore(
                        "",
                        "§fWir danken dir für deine Unterstützung.",
                        Utility.toLuni("§7Signiert von ") + ConstStorage.getTITLE() + Utility.toLuni(" §7am §e") + Utility.toLuni(new SimpleDateFormat("dd.MM.yyyy").format(new Date())),
                        "",
                        "§8» §7Preis§8: §e25 Punkte"
                );

        inventory.setItem(12, boosterBuy);
        inventory.setItem(13, ultraBoosterBuy);
        inventory.setItem(14, miniVoteKisteBuy);
        inventory.setItem(15, adminItemBuy);
        inventory.setItem(19, punkteÜbersicht);

        inventory.setItem(16, grayglass);
        inventory.setItem(21, grayglass);
        inventory.setItem(22, grayglass);
        inventory.setItem(23, grayglass);
        inventory.setItem(24, grayglass);
        inventory.setItem(25, grayglass);
        inventory.setItem(30, grayglass);
        inventory.setItem(31, grayglass);
        inventory.setItem(32, grayglass);
        inventory.setItem(33, grayglass);
        inventory.setItem(34, grayglass);
        return inventory;
    }
}
