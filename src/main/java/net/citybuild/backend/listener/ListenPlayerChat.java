package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.utility.Pair;
import net.citybuild.backend.utility.Serialization;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ListenPlayerChat implements Listener {

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage().replaceAll("->", "➟");

        CityBuild.getUserManager().addMessage(player.getUniqueId(), message);
        event.setFormat(player.getDisplayName() + " §8» §7" + (player.isOp() ? Utility.withGradient(message, "#53AD59", "#3C502B", true) : message));

        for (Map.Entry<Pair<Player, String>, Pair<ItemStack, Float>> pairPairEntry : CityBuild.getCaseManager().getChanceEdit().entrySet()) {

            if (pairPairEntry.getKey().key() == player && player.isOp()) {
                event.setCancelled(true);

                final ItemStack itemStack = pairPairEntry.getValue().key();
                final String caseName = pairPairEntry.getKey().value();

                if (event.getMessage().equalsIgnoreCase("cancel")) {
                    Message.sendMessage(player, "§cDer Vorgang wurde erfolgreich abgebrochen§8.");
                    CityBuild.getCaseManager().getChanceEdit().clear();
                    Utility.playSuccess(player);
                    return;
                }

                try {
                    final float newChance = Float.parseFloat(event.getMessage());

                    if (newChance >= 0.1f && newChance <= 100f) {
                        CityBuild.getCaseManager().getCase(caseName).getCaseItems().put(Serialization.itemStackToBase64(itemStack), newChance);
                        CityBuild.getCaseManager().getChanceEdit().clear();
                        Message.sendMessage(player, "§7Die Chance des Kistenitems wurde auf §b" + newChance + "% §7gesetzt§8.");
                        Utility.playSuccess(player);

                    } else {
                        Message.sendMessage(player, "§cBitte gebe eine gültige Chance zwischen 0.1 und 100 an§8!");
                    }

                } catch (NumberFormatException ignored) {
                    Message.sendMessage(player, "§cBitte gebe eine gültige Chance an§8!");
                }
            }
        }


    }
}
