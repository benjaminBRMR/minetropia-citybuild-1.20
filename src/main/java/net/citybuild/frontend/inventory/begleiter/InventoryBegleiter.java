package net.citybuild.frontend.inventory.begleiter;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.begleiter.Begleiter;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Pagifier;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryBegleiter {

    public final Pagifier<Begleiter> pagifier = new Pagifier<>(14);
    public final AtomicInteger pageItemCounter = new AtomicInteger(10);

    public int page;

    public InventoryBegleiter(final @NonNull Player player) {
        page = 0;

        EnumSet.allOf(Begleiter.class).forEach(pagifier::addItem);
        update(0, player);

    }


    public void update(@Nonnegative int page, final @NonNull Player player) {
        this.page = page;
        pageItemCounter.set(10);


        final User user = CityBuild.getUserManager().handle(player.getUniqueId());
        final Inventory inventory = Bukkit.createInventory(null, 36, "§8» " + Color.translateColorCodes("&#213c15&lBegleiter"));
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");

        final ItemStack seiteBack = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§cSeite zurück")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("d59be1557201c7ff1a0b3696d19eab4104880d6a9cdb4d5fa21b6daa9db2d1");
        final ItemStack seiteVor = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§8»  §aSeite vor")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("42b0c07fa0e89237d679e13116b5aa75aebb34e9c968c6badb251e127bdd5b1");


        Utility.fillInventory(inventory, glass);

        inventory.setItem(32, seiteVor);
        inventory.setItem(30, seiteBack);


        for (Begleiter begleiter : pagifier.getPage(this.page)) {

            final ItemStack finalStack = new ItemCreator(Material.PLAYER_HEAD)
                    .setSkullValue(begleiter.getMinecraftURL())
                    .setName("&#213c15&lBegleiter§8: §7" + begleiter.getDisplayName())
                    .setLore(
                            (CityBuild.getUserManager().hasBegleiter(player.getUniqueId(), begleiter)
                                    ? "§7Linke Maustaste §8● §7" + (CityBuild.getUserManager().hasBegleiterToggled(player.getUniqueId(), begleiter)
                                    ? "§cdeaktivieren" : "§aaktivieren") : "§cDu besitzt diesen Begleiter nicht§8.")
                    );


            if (pageItemCounter.get() == 17) pageItemCounter.set(19);
            if (pageItemCounter.get() > 25) break;
            inventory.setItem(pageItemCounter.getAndIncrement(), finalStack);
        }

        player.openInventory(inventory);

    }
}
