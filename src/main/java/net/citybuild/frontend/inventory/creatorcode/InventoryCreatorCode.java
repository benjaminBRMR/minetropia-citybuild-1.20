package net.citybuild.frontend.inventory.creatorcode;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.user.User;
import net.citybuild.backend.user.creatorcode.CreatorCode;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@UtilityClass
public class InventoryCreatorCode {

    public Inventory getCreatorCodeInventory(final @NonNull Player player) {

        final User user = CityBuild.getUserManager().handle(player.getUniqueId());
        final AtomicInteger itemCounter = new AtomicInteger(12);


        final Inventory inventory = Bukkit.createInventory(null, 45, "§8» §d§lCreator-Code");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");

        final ItemStack info = new ItemCreator(Material.AMETHYST_CLUSTER)
                .setName("§d§lCreator-Code Information")
                .setLore(
                        "§7  Möchtest du deine Creator*innen unterstützen?",
                        "§7  Dann wähle den Creator-Code von ihnen",
                        "§7  aus und erhalte tolle Belohnungen!"
                );

        final ItemStack remove = new ItemCreator(Material.BARRIER)
                .setName("§d§lEntferne deinen ausgewählten Creator-Code!")
                .setLore(
                        "§8» §7Abklingzeit§8: §514 Tage"
                );


        Utility.fillInventory(inventory, glass);

        inventory.setItem(10, info);

        if (CityBuild.getCcManager().hasCC(player.getUniqueId())) {
            final ItemStack creatoroutputbits = new ItemCreator(Material.PLAYER_HEAD)
                    .setSkullValue("55dfa284aa15324e5178561f803f5976228d95115583ab031266ae24ee1a99d1")
                    .setName("§d§lCreator-Code Statistiken")
                    .setLore(
                            "§8» §7Anzahl der Spieler§8, §7die dich unterstützen§8: §5" + NumberFormat.getInstance().format(CityBuild.getCcManager().getCreatorCodeUses(CityBuild.getCcManager().getCreatorCodeByUUID(player.getUniqueId()))),
                            "§8» §7Verdiente Bits §8(§7Alltime§8)§8: §5" + NumberFormat.getInstance().format(CityBuild.getCcManager().getCreatorCodeByUUID(player.getUniqueId()).getIncomingBitsAlltime()),
                            "§8» §7Bits abholbereit§8: §5" + NumberFormat.getInstance().format(CityBuild.getCcManager().getCreatorCodeByUUID(player.getUniqueId()).getIncomingBits()),
                            "",
                            "  §7Linksklicke§8,§7 um deine Bits einzusammeln§8."
                    );
            inventory.setItem(28, creatoroutputbits);
        }

        if (user.getCreatorCode() != null) inventory.setItem(44, remove);


        IntStream.range(12, 17).forEach(i -> inventory.setItem(i, new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("2f4812e91da610c0d17a19de8d6c7deefb104d4f439e6ac178db4e4fc23fb7cb")
                .setName("§d§lDu?")
                .setLore(
                        "§8» §7Erstelle ein Discord-Admin-Ticket, um",
                        "§8» §7dich als Creator*innen zu bewerben§8."
                )));
        IntStream.range(21, 26).forEach(i -> inventory.setItem(i, new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("2f4812e91da610c0d17a19de8d6c7deefb104d4f439e6ac178db4e4fc23fb7cb")
                .setName("§d§lDu?")
                .setLore(
                        "§8» §7Erstelle ein Discord-Admin-Ticket, um",
                        "§8» §7dich als Creator*innen zu bewerben§8."
                )));

        IntStream.range(30, 35).forEach(i -> inventory.setItem(i, new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("2f4812e91da610c0d17a19de8d6c7deefb104d4f439e6ac178db4e4fc23fb7cb")
                .setName("§d§lDu?")
                .setLore(
                        "§8» §7Erstelle ein Discord-Admin-Ticket, um",
                        "§8» §7dich als Creator*innen zu bewerben§8."
                )));

        for (CreatorCode creatorCode : CityBuild.getCcManager().getCreatorCodes()) {

            final ItemStack creatorItem = new ItemCreator(Material.PLAYER_HEAD)
                    .setName("§7Creator-Code§8: §5" + creatorCode.getCode())
                    .setSkullPlayer(Objects.requireNonNull(Bukkit.getOfflinePlayer(creatorCode.getCreator()).getName()))
                    .setLore(
                            "§8» §7Creator§8: §5§l" + Bukkit.getOfflinePlayer(creatorCode.getCreator()).getName(),
                            "   §7" + (CityBuild.getCcManager().hasCC(player.getUniqueId(), creatorCode) ? "§aDieser Creator-Code gehört dir§8!" : (Objects.equals(user.getCreatorCode(), creatorCode.getCode()) ? "§aDu unterstützt diesen Creator§8!" : "§cDu unterstützt diesen Creator nicht§8!"))
                    );

            if (itemCounter.get() > 16) itemCounter.set(21);
            if (itemCounter.get() > 25) itemCounter.set(30);
            if (itemCounter.get() > 34) break;

            inventory.setItem(itemCounter.getAndIncrement(), creatorItem);
        }


        return inventory;


    }
}
