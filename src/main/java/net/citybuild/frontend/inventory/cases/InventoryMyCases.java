package net.citybuild.frontend.inventory.cases;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryMyCases {

    public final LinkedHashMap<Integer, String> saver = new LinkedHashMap<>();


    public Inventory getMyCasesInventory(final @NonNull Player player) {

        final User user = CityBuild.getUserManager().handle(player.getUniqueId());
        final AtomicInteger itemCounter = new AtomicInteger(0);


        final Inventory inventory = Bukkit.createInventory(null, 45, "§8» §a§lMeine Kisten");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack back = new ItemCreator(Material.SPRUCE_DOOR).setName("§cZurück");

        Utility.fillInventory(inventory, glass);

        inventory.setItem(36, back);

        if (user.getCases().isEmpty()) {
            final ItemStack kiste = new ItemCreator(Material.BARRIER)
                    .setName("§cDu besitzt aktuell keine Kisten§8!");
            inventory.setItem(22, kiste);
        } else {
            user.getCases().forEach((caseA, amount) -> {
                if (amount > 0) {
                    if (itemCounter.get() == 36) itemCounter.set(37);

                    saver.put(itemCounter.get(), CityBuild.getCaseManager().getCase(caseA).getName());

                    final ItemStack kiste = new ItemCreator(Material.PLAYER_HEAD)
                            .setName(CityBuild.getCaseManager().getCase(caseA).getDisplayName())
                            .setSkullValue("9d2338b389bfcd76cde9197caa65b6b75fcb952f38181128dcc7e7163bc131f")
                            .setLore(
                                    "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase(caseA)),
                                    "",
                                    "  §7Rechte Maustaste §8● §2Vorschau",
                                    "  §7Linke Maustaste §8● §2Öffnen",
                                    "  §7Mittlere Maustaste §8● §2Test-Spin"
                            );
                    inventory.setItem(itemCounter.getAndIncrement(), kiste);
                } else {
                    final ItemStack kiste = new ItemCreator(Material.BARRIER)
                            .setName("§cDu besitzt aktuell keine Kisten§8!");
                    inventory.setItem(22, kiste);
                }
            });
        }


        return inventory;
    }
}
