package net.citybuild.frontend.inventory.voucher;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.storage.VoucherStorage;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryVoucher {

    public final Pagifier<ItemStack> pagifier = new Pagifier<>(45);
    public final AtomicInteger pageItemCounter = new AtomicInteger(0);

    public int page;

    public InventoryVoucher(final @NonNull Player player) {
        page = 0;

        VoucherStorage.getVoucher().forEach((key) -> {
            pagifier.addItem(key.getItem());
        });


        update(0, player);

    }


    public void update(@Nonnegative int page, final @NonNull Player player) {
        this.page = page;

        pageItemCounter.set(0);

        final Inventory inventory = Bukkit.createInventory(null, 54, Color.translateColorCodes("§8» &#ae9d6f&lGutscheine"));
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

        inventory.setItem(50, seiteVor);
        inventory.setItem(48, seiteBack);

        for (ItemStack displayStack : pagifier.getPage(this.page)) {
            final ItemCreator modifiedCreator = new ItemCreator(displayStack);
            modifiedCreator.addToLore(Utility.toLuni("§7Signiert von ") + ConstStorage.getTITLE() + Utility.toLuni(" §7am §e") + Utility.toLuni(new SimpleDateFormat("dd.MM.yyyy").format(new Date())));
            inventory.setItem(pageItemCounter.getAndIncrement(), modifiedCreator);
        }

        player.openInventory(inventory);

    }
}
