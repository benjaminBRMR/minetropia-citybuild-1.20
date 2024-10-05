package net.citybuild.backend.cases.animation;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.utility.Pair;
import net.citybuild.backend.utility.Serialization;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CaseAnimationLine {

    final Player player;
    final Random random;
    final Inventory inventory;
    final LinkedHashMap<Player, Case> animation = new LinkedHashMap<>();
    final LinkedHashMap<Player, Pair<ItemStack, Integer>> animationSlot = new LinkedHashMap<>();

    public CaseAnimationLine(final @NonNull Player player) {
        this.player = player;
        this.random = new Random();
        this.inventory = Bukkit.createInventory(null, 27, "§cAnimation");
    }

    public void startAnimation(final @NonNull Case caseA) {
        animation.put(player, caseA);

        final LinkedList<ItemStack> itemStacks = new LinkedList<>();
        caseA.getCaseItems().forEach((stack, chance) -> {
            itemStacks.add(Serialization.itemStackFromBase64(stack));
        });

        final AtomicInteger tickCounter = new AtomicInteger(0);
        final AtomicInteger slotCount = new AtomicInteger(16);

        final long task = Bukkit.getScheduler().scheduleSyncRepeatingTask(CityBuild.getINSTANCE(), new Runnable() {
            @Override
            public void run() {
                if (slotCount.getAndIncrement() >= 16) slotCount.set(16);

                if (tickCounter.getAndIncrement() < 20) {
                    inventory.setItem(animationSlot.get(player).value(), animationSlot.get(player).key());
                }

                animationSlot.put(player, new Pair<>(itemStacks.get(random.nextInt(itemStacks.size())), slotCount.get()));


            }
        }, 0L, 1L);
    }
}
