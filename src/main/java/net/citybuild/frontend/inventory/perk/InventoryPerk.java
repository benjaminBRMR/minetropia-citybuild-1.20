package net.citybuild.frontend.inventory.perk;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Pagifier;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryPerk {

    public final Pagifier<Perk> pagifier = new Pagifier<>(14);
    public final AtomicInteger pageItemCounter = new AtomicInteger(10);

    public int page;

    public InventoryPerk(final @NonNull Player player) {
        page = 0;

        EnumSet.allOf(Perk.class).forEach(pagifier::addItem);
        update(0, player);

    }


    public void update(@Nonnegative int page, final @NonNull Player player) {
        this.page = page;
        pageItemCounter.set(10);


        final User user = CityBuild.getUserManager().handle(player.getUniqueId());
        final Inventory inventory = Bukkit.createInventory(null, 36, "§8» " + Color.translateColorCodes("&#FF00B0&lPerks"));
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


        for (Perk perk : pagifier.getPage(this.page)) {
            String message;

            if (perk.getPrice() < 0) {
                if(!CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk)) {
                    message = "§cDieses Perk ist nur im Case-Opening erhältlich§8.";
                } else {
                    message = "§7Linke Maustaste §8● §7" + (CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk)
                            ? "§cdeaktivieren" : "§aaktivieren");
                }
            } else {
                if (CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk)) {
                    if (perk.getPermission() == null) {
                        message = "§7Linke Maustaste §8● §7" + (CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk)
                                ? "§cdeaktivieren" : "§aaktivieren");
                    } else {
                        if (player.hasPermission(perk.getPermission())) {
                            message = "§cDie Befugnis hast du bereits in deinem Besitz§8.";
                        } else {
                            message = "§cDie Befugnis hast du bereits in deinem Besitz§8.";
                        }
                    }
                } else {
                    if (perk.getPermission() == null) {
                        message = (CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk) ? "§7Linke Maustaste §8● §7" + (CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk)
                                ? "§cdeaktivieren" : "§aaktivieren") : "§7Linke Maustaste §8● §aPerk erwerben§8.");
                    } else {
                        message = (!player.hasPermission(perk.getPermission()) ? "§7Linke Maustaste §8● §aPerk erwerben§8." : "§cDie Befugnis hast du bereits in deinem Besitz§8.");
                    }
                }
            }


            final ItemStack finalStack = new ItemCreator(perk.getMaterial())
                    .setName("&#FF00B0&lPerk§8: §7" + perk.getDisplayName())
                    .setLore(
                            perk.getAdvantage(),
                            "§8» §7Preis§8: §e" + (CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk) ? "§aBereits erworben§8." : (perk.getPrice() < 0 ? "§c✘" : NumberFormat.getInstance().format(perk.getPrice()) + "§6€")),
                            "",
                            perk.getDescriptionOne(),
                            perk.getDescriptionTwo(),
                            "",
                            message
                    )
                    .addItemFlag(ItemFlag.HIDE_ATTRIBUTES)
                    .glow(CityBuild.getUserManager().hasPerkToggled(player.getUniqueId(), perk));


            if (pageItemCounter.get() == 17) pageItemCounter.set(19);
            if (pageItemCounter.get() > 25) break;
            inventory.setItem(pageItemCounter.getAndIncrement(), finalStack);
        }

        player.openInventory(inventory);

    }
}
