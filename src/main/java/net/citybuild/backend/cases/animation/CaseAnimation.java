package net.citybuild.backend.cases.animation;

import lombok.Getter;
import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.utility.Serialization;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CaseAnimation {

    final Player player;
    @Getter
    private final HashMap<Player, Integer> spinning = new LinkedHashMap<>();
    @Getter
    private final HashMap<Player, Integer> win = new LinkedHashMap<>();
    private final Random random = new Random();
    private final List<ItemStack> caseItems = new ArrayList<>();
    private int spin = random.nextInt(10000);
    private int ticks;

    public CaseAnimation(final @NonNull Player player) {
        this.player = player;
    }

    public void startAnimation(final @NonNull Case caseA, final boolean testspin) {

        final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §7Öffnet " + caseA.getName());

        player.closeInventory();
        player.openInventory(inventory);

        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack gewinn = new ItemCreator(Material.SHULKER_SHELL).setName("§8» §a§lGewinn")
                .setLore(
                        "§7Das untere Item ist dein Gewinn§8."
                );
        final AtomicInteger slotCounter = new AtomicInteger(9);


        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, glass);
        }

        for (int i = 18; i < 27; i++) {
            inventory.setItem(i, glass);
        }

        ticks = 3;
        spinning.put(player, 0);
        caseA.getCaseItems().forEach((key, value) -> caseItems.add(Serialization.itemStackFromBase64(key)));


        spin = Bukkit.getScheduler().scheduleSyncRepeatingTask(CityBuild.getINSTANCE(), () -> {

            int itemInt = random.nextInt(caseItems.size());
            spinning.put(player, (spinning.get(player) + 1));

            if (spinning.get(player) == 10) ticks = 5;
            if (spinning.get(player) == 20) ticks = 10;
            if (spinning.get(player) == 30) ticks = 15;
            if (spinning.get(player) == 40) ticks = 20;

            if (spinning.get(player) < 40) {
                inventory.setItem(slotCounter.getAndIncrement(), caseItems.get(itemInt));

                if (spinning.get(player) == 39) {
                    win.put(player, itemInt);
                }
                player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1F, 1F);
                if (slotCounter.get() == 18) slotCounter.set(9);
            } else if (spinning.get(player) == 40) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
            } else if (spinning.get(player) == 41) {
                itemInt = win.get(player);


                if (!testspin) {
                    Message.sendMessage(player, "§7Du hast eine §7" + caseA.getDisplayName() + " §7geöffnet und hast " +
                            (caseItems.get(itemInt).getAmount() > 1 ? " §a" + caseItems.get(itemInt).getAmount() + "§8x " : "§aeinen ") +
                            (caseItems.get(itemInt).getItemMeta().hasDisplayName() ? caseItems.get(itemInt).getItemMeta().getDisplayName() : "§a" + caseItems.get(itemInt).getType()) + " §7gezogen§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    Utility.addItem(player, caseItems.get(itemInt));

                    /*
                    if(caseA.getCaseItems().get(itemInt) <= 5) {
                        Bukkit.broadcastMessage("§7Monkey hat baba item gezogen yeah"); // FIXME: 11.08.2023 BROADCAST WENN CHANCE UNTER 5
                    }

                     */

                    caseA.setLastOpened(player.getUniqueId());
                    caseA.setOpened((caseA.getOpened()) + 1);
                } else {
                    Message.sendMessage(player, "§7Du hast eine §7" + caseA.getDisplayName() + " §7geöffnet und hättest " +
                            (caseItems.get(itemInt).getAmount() > 1 ? " §a" + caseItems.get(itemInt).getAmount() + "§8x " : "§aeinen ") +
                            (caseItems.get(itemInt).getItemMeta().hasDisplayName() ? caseItems.get(itemInt).getItemMeta().getDisplayName() : "§a" + caseItems.get(itemInt).getType()) + " §7gezogen§8.");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                }


                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                    }
                }.runTaskLater(CityBuild.getINSTANCE(), 15L);
                Bukkit.getScheduler().cancelTask(spin);
                spinning.remove(player);
                win.remove(player);

            }

        }, 0, ticks);


    }
}
