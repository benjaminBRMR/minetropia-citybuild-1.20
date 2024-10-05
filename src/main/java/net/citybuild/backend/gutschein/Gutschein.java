package net.citybuild.backend.gutschein;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.citybuild.backend.gutschein.action.GutscheinItemAction;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class Gutschein {

    private final ItemStack item;
    private final GutscheinItemAction gutscheinItemAction;
}
