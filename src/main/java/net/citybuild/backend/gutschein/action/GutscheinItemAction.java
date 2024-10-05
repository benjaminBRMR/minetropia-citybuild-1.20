package net.citybuild.backend.gutschein.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface GutscheinItemAction {

    void interact(final Player player, final ItemStack itemStack);
}
