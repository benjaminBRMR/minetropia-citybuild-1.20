package net.citybuild.frontend.inventory.vote;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

@UtilityClass
public class InventoryVote {

    public Inventory getVoteInventory(final @NonNull Player player) {

        final User user = CityBuild.getUserManager().handle(player.getUniqueId());

        final Inventory inventory = Bukkit.createInventory(null, 45, "§8» §6§lVote-Menü");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack grayglass = new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName("§r");

        final ItemStack infoHead = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("2f78a1d399c1296da2e3a632182efad04f1934fc70ec3f195c95909e40547cd")
                .setName("§6§lVote Information")
                .setLore(
                        "§8» §6Votestreak§8: §e" + NumberFormat.getInstance().format(user.getVotestreak()),
                        "   §7Läuft ab am §e12.03.2023, 9:30 Uhr",
                        "§8» §6Insgesamte Votes§8: §e" + NumberFormat.getInstance().format(user.getVotes()),
                        "",
                        "",
                        "§7 Der Link zum Voten wurde dir bereits",
                        "§7 in dein Minecraft-Chat gesendet§8.",
                        "",
                        "§7 Um deine Vote-Belohnung zu bekommen",
                        "§7 musst du §e/geschenk §7eingeben§8.",
                        ""
                );

        final ItemStack infoPresentperVote = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("2c24ab92ba9d9235afec046e3b1ade19097b788f764e974386fbc32f94c6db4f")
                .setName("§6§lVote-Belohnung")
                .setLore(
                        "§8",
                        "",
                        "§c ",
                        "§7 für die Community belohnt§8.",
                        "",
                        "",
                        "",
                        "",
                        ""
                );

        Utility.fillInventory(inventory, glass);

        inventory.setItem(19, infoHead);
        inventory.setItem(10, grayglass);
        inventory.setItem(28, grayglass);
        inventory.setItem(12, infoPresentperVote);

        return inventory;
    }
}
