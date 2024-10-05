package net.citybuild.frontend.inventory.cases;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.utility.Pagifier;
import net.citybuild.backend.utility.Serialization;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnegative;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryShowcase {

    public final Pagifier<ItemStack> pagifier = new Pagifier<>(45);
    public final AtomicInteger pageItemCounter = new AtomicInteger(0);

    public int page;
    public boolean opShowcase;

    public InventoryShowcase(final @NonNull Player player, final @NonNull Case caseA, final boolean opShowcase) {
        page = 0;
        final String caseName = caseA.getName();

        caseA.getCaseItems().forEach((key, value) -> {
            pagifier.addItem(Serialization.itemStackFromBase64(key));
        });

        update(0, player, caseName);
        this.opShowcase = opShowcase;

    }


    public void update(@Nonnegative int page, final @NonNull Player player, final @NonNull String caseName) {
        this.page = page;

        pageItemCounter.set(0);

        final Inventory inventory = Bukkit.createInventory(null, 54, (opShowcase ? "§8» §a§lBearbeitung §7von §f" : "§8» §a§lShowcase §7von §f") + caseName);
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");

        final ItemStack seiteBack = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§cSeite zurück")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("d59be1557201c7ff1a0b3696d19eab4104880d6a9cdb4d5fa21b6daa9db2d1");
        final ItemStack seiteVor = new ItemCreator(Material.PLAYER_HEAD)
                .setName("§8»  §aSeite vor")
                .setLore("§7Seite §a" + (this.page + 1) + "§8/§c" + this.pagifier.getPages().size())
                .setSkullValue("42b0c07fa0e89237d679e13116b5aa75aebb34e9c968c6badb251e127bdd5b1");

        final ItemStack back = new ItemCreator(Material.SPRUCE_DOOR).setName("§cZurück");


        Utility.fillInventory(inventory, glass);

        inventory.setItem(50, seiteVor);
        inventory.setItem(48, seiteBack);
        inventory.setItem(45, back);


        for (ItemStack displayStack : pagifier.getPage(this.page)) {

            final String key = Serialization.itemStackToBase64(displayStack);
            final float chance = CityBuild.getCaseManager().getCase(caseName).getCaseItems().get(key);
            final long opened = CityBuild.getCaseManager().getCase(caseName).getOpened();

            if (opShowcase) {
                final ItemStack finalStack = new ItemCreator((displayStack == null ? new ItemStack(Material.STICK) : displayStack))
                        .setName("§a§lKistenitem§8: §r" + (displayStack.getItemMeta().hasDisplayName() ? displayStack.getItemMeta().getDisplayName() : displayStack.getType()))
                        .setLore(
                                "§8» §7Chance§8: §2" + chance + "%",
                                "§8» §7Ins. gezogen§8: §2" + NumberFormat.getInstance().format(opened),
                                "",
                                " " + ChatColor.of("#95D5B2") + "Mausrad, um die Chance",
                                " " + ChatColor.of("#95D5B2") + "des Kistenitems zu verändern.",
                                "",
                                " " + ChatColor.of("#52B788") + "Q-Taste, um das Kistenitem",
                                " " + ChatColor.of("#52B788") + "in dein Inventar zu erhalten.",
                                "",
                                " " + ChatColor.of("#40916C") + "Wenn die Chance eines Kistenitems unter",
                                " " + ChatColor.of("#40916C") + "5% beträgt, dann wird eine Nachricht an",
                                " " + ChatColor.of("#40916C") + "alle Spieler, welche online sind ausgegeben."

                        );

                if (pageItemCounter.get() == 45) pageItemCounter.set(46);
                inventory.setItem(pageItemCounter.getAndIncrement(), finalStack);

            } else {
                final ItemStack finalStack = new ItemCreator((displayStack == null ? new ItemStack(Material.STICK) : displayStack))
                        .setName("§a§lKistenitem§8: §r" + (displayStack.getItemMeta().hasDisplayName() ? displayStack.getItemMeta().getDisplayName() : displayStack.getType()));
                inventory.setItem(pageItemCounter.getAndIncrement(), finalStack);

            }
        }

        player.openInventory(inventory);

    }
}
