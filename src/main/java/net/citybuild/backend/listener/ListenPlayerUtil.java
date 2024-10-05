package net.citybuild.backend.listener;

import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

import java.util.Objects;

public class ListenPlayerUtil implements Listener {

    @EventHandler
    public void onFoodLevelChangeEvent(final FoodLevelChangeEvent event) {
        if (event.getEntity().isInvulnerable()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            if (event.getEntity().isInvulnerable()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityInteract(final EntityInteractEvent event) {

        // FIXME: 21.08.2023
        if (event.getEntity() instanceof ArmorStand) {
            System.out.println(event.getEntity().getCustomName());
            if (Objects.requireNonNull(event.getEntity().getCustomName()).contains(Color.translateColorCodes("&#213c15&l"))) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(final VehicleEntityCollisionEvent event) {
        final Entity entity = event.getEntity();
        System.out.println("yess");
        if (entity instanceof Minecart && entity.getWorld().getName().equalsIgnoreCase(ConstStorage.getPlotWelt())) {
            event.setCollisionCancelled(true);
        }
    }

    // FIXME: 21.08.2023 Minecarts komplett in Plotwelt deaktivieren

    @EventHandler
    public void blockBreak(final BlockBreakEvent event) {
        final Block block = event.getBlock();

        if (block.getType() == Material.PLAYER_HEAD) {
            if (block.getState() instanceof Skull skull) {
                if (skull.hasOwner()) {
                    Player player = event.getPlayer();
                    String ownerName = skull.getOwningPlayer().getName();

                    if (!player.getName().equals(ownerName) && !player.isOp()) {
                        event.setCancelled(true);
                        return;
                    }

                    Message.sendMessage(player, "§7Kopf von §e" + ownerName + " §7abgebaut§8.");
                }
            }
        }
    }
}
