package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.gutschein.Gutschein;
import net.citybuild.backend.user.User;
import net.citybuild.frontend.inventory.cases.InventoryCase;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class ListenPlayerInteract implements Listener {


    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final User user = CityBuild.getUserManager().handle(player.getUniqueId());

        try {
            Gutschein gutschein = CityBuild.getVoucherManager().getGutscheinByDisplayName(Objects.requireNonNull(player.getItemInHand().getItemMeta()).getDisplayName());
            if(gutschein.getGutscheinItemAction() != null) {
                gutschein.getGutscheinItemAction().interact(player, player.getItemInHand());
                event.setCancelled(true);
            }
        } catch (NullPointerException ignored) {
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (event.getClickedBlock() != null) {
                final Block block = event.getClickedBlock();

                if (CityBuild.getLocationManager().existLocation("case")) {
                    if (block.getLocation().getX() == CityBuild.getLocationManager().getLocation("case").getX()
                            && block.getLocation().getY() == CityBuild.getLocationManager().getLocation("case").getY()
                            && block.getLocation().getZ() == CityBuild.getLocationManager().getLocation("case").getZ()) {
                        player.openInventory(InventoryCase.getCaseInventory(player));
                        event.setCancelled(true);
                        return;
                    }
                    return;
                }

                /*
                if (CityBuild.getLocationManager().existLocation("immortalanvil"))
                    if (block.getLocation().getX() == CityBuild.getLocationManager().getLocation("immortalanvil").getX()
                            && block.getLocation().getY() == CityBuild.getLocationManager().getLocation("immortalanvil").getY()
                            && block.getLocation().getZ() == CityBuild.getLocationManager().getLocation("immortalanvil").getZ()) {
                        System.out.println("yes");
                        if (ConstStorage.getImmortalAnvil().get() >= 3) {
                            Message.sendMessage(player, "§cDieser Amboss wurde heute bereits 3§8x §7benutzt§8!");
                            event.setCancelled(true);
                            return;
                        }

                        ConstStorage.getImmortalAnvilMap().put(player, block);
                    }

                 */


            }
        }
    }


}
