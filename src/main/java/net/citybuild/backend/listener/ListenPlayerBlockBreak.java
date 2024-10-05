package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.listener.custom.ListenXPBoosterMultiplier;
import net.citybuild.backend.perk.Perk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ListenPlayerBlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        if(CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), Perk.WIZARD)) {
            ItemStack droppedItem = event.getBlock().getDrops().stream().findFirst().orElse(null);
            if (droppedItem != null) {
                event.getPlayer().getInventory().addItem(droppedItem);
                event.getBlock().setType(Material.AIR);
            }
        }
        if(CityBuild.getBoosterManager().isRunning(Booster.XP)) {
            int originalXP = event.getExpToDrop();
            int getMultiplier = (int) ListenXPBoosterMultiplier.getMultiplier();
            int modifiedXP = (originalXP*getMultiplier);
            event.setExpToDrop(modifiedXP);
        }
    }
}
