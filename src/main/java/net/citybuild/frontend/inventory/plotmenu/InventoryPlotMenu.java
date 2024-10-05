package net.citybuild.frontend.inventory.plotmenu;

import com.plotsquared.core.plot.Plot;
import lombok.NonNull;
import net.citybuild.backend.utility.Pagifier;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;


public class InventoryPlotMenu {

    public final Pagifier<Plot> pagifier = new Pagifier<>(27);
    public final AtomicInteger pageItemCounter = new AtomicInteger(8);
    public final AtomicInteger slotCounter = new AtomicInteger(1);

    public int page;

    public InventoryPlotMenu(final @NonNull Player player) {
        page = 0;
        CompletableFuture.supplyAsync(() -> {
            for (Plot plot : Utility.getPlayerPlots(player)) {
                pagifier.addItem(plot);
            }
            return this;
        });
        update(0, player);
    }

    public void update(@Nonnegative int page, final @NonNull Player player) {
        this.page = page;
        pageItemCounter.set(9);
        slotCounter.set(1);


        final Inventory inventory = Bukkit.createInventory(null, 54, Color.translateColorCodes("§8» &#72A300&lGrundstücksmenü"));
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");

        final ItemStack seiteBack = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§cSeite zurück")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("d59be1557201c7ff1a0b3696d19eab4104880d6a9cdb4d5fa21b6daa9db2d1");

        final ItemStack seiteVor = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§8»  §aSeite vor")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("42b0c07fa0e89237d679e13116b5aa75aebb34e9c968c6badb251e127bdd5b1");


        final ItemStack plotAuto = new ItemCreator(Material.LIME_BED).setName("&#72A300&lPlot Auto").setLore(
                "§8» §7Aktuelle Plots§8: &#72A300&l" + Utility.getPlots(player),
                "§8» §7Verfügbare Plots§8: &#72A300&l" + (Utility.getAllowedPlots(player) == 2147483647 ? "UNENDLICH" : Utility.getAllowedPlots(player)),
                "",
                "  §7Siehe hier eine kleine Übersicht",
                "  §7und erstelle dir durchs Klicken",
                "  §7ein zufälliges Plot."
        );

        final ItemStack plotHilfe = new ItemCreator(Material.KNOWLEDGE_BOOK).setName("&#72A300&lPlot Hilfe").setLore(
                "  &#72A300&l/p claim §8● §7Beanspruche das Plot auf dem du stehst§8.",
                "  &#72A300&l/p auto §8● §7Beanspruche ein zufälliges Plot§8.",
                "  &#72A300&l/p sethome §8● §7Setze deinen Plot-Spawn um§8.",
                "  &#72A300&l/p confirm §8● §7Bestätige eine Aktion§8.",
                "  &#72A300&l/p v name §8● §7Besuche das Plot eines anderen Spielers§8.",
                "  &#72A300&l/p delete §8● §7Lösche vollständig dein Plot§8.",
                "  &#72A300&l/p clear §8● §7Leere vollständig dein Plot§8.",
                "  &#72A300&l/p trust name §8● §7Gebe einem Spieler Rechte auf deinem Plot§8.",
                "  &#72A300&l/p add name §8● §7Gebe einem Spieler temporär Rechte auf deinem Plot§8.",
                "  &#72A300&l/p remove name §8● §7Entferne einen Spieler von deinem Plot§8.",
                "  &#72A300&l/p info §8● §7Siehe Informationen über ein Plot ein§8.",
                "  &#72A300&l/p kick name §8● §7Werf ein Spieler von deinem Plot§8.",
                "  &#72A300&l/p deny name §8● §7Sperre ein Spieler von deinem Plot§8."
        );

        //Utility.fillInventory(inventory, glass);
        inventory.setItem(0, glass);
        inventory.setItem(1, glass);
        inventory.setItem(2, glass);
        inventory.setItem(3, glass);
        inventory.setItem(4, glass);
        inventory.setItem(5, glass);
        inventory.setItem(6, glass);
        inventory.setItem(7, glass);
        inventory.setItem(8, glass);

        inventory.setItem(36, glass);
        inventory.setItem(37, glass);
        inventory.setItem(38, glass);
        inventory.setItem(39, glass);
        inventory.setItem(40, glass);
        inventory.setItem(41, glass);
        inventory.setItem(42, glass);
        inventory.setItem(43, glass);
        inventory.setItem(44, glass);

        inventory.setItem(49, plotAuto);
        inventory.setItem(45, plotHilfe);

        inventory.setItem(41, seiteVor);
        inventory.setItem(39, seiteBack);


        for (Plot plot : pagifier.getPage(this.page)) {
            final ItemStack finalStack = new ItemCreator(Material.RED_BED)
                    .setName("&#72A300&lGrundstück §8● §f" + slotCounter + " §8(§7" + plot.getId() + "§8)")
                    .setLore(
                            "§8» §7Teleportie dich zu deinem &#72A300&l" + slotCounter.getAndIncrement() + "§8. §7Grundstück§8."
                    );

            if (pageItemCounter.get() > 35) break;
            inventory.setItem(pageItemCounter.getAndIncrement(), finalStack);

        }

        player.openInventory(inventory);

    }

}