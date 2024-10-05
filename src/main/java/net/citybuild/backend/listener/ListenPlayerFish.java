package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.manager.UsesManager;
import net.citybuild.backend.storage.ConstStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ListenPlayerFish implements Listener {

    @EventHandler
    public void onPlayerFish(final PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            ItemStack rod = event.getPlayer().getItemInHand();
            if (rod.getType() == Material.FISHING_ROD && Objects.requireNonNull(rod.getItemMeta()).hasDisplayName()) {
                final String itemNameWithoutColor = ChatColor.stripColor(rod.getItemMeta().getDisplayName());
                if (ConstStorage.getMaxUsesMap().containsKey(itemNameWithoutColor)) {
                    event.getCaught().remove();
                    CityBuild.getUsesManager().giveFishingReward(event.getPlayer(), itemNameWithoutColor);
                    CityBuild.getUsesManager().updateUsesLore(event.getPlayer(), rod, ConstStorage.getMaxUsesMap());
                }
            }
        }
    }
}
