package net.citybuild.frontend.inventory.cases;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

@UtilityClass
public class InventoryCase {

    public Inventory getCaseInventory(final @NonNull Player player) {

        final User user = CityBuild.getUserManager().handle(player.getUniqueId());

        final Inventory inventory = Bukkit.createInventory(null, 54, "§8» §a§lKisten-Menü");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack miniVote_Kiste = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("4cb3acdc11ca747bf710e59f4c8e9b3d949fdd364c6869831ca878f0763d1787")
                .setName(Color.translateColorCodes("&#EF32D9&lM&#E743DC&li&#DE54DF&ln&#D665E2&li &#CD76E5&lV&#C587E8&lo&#BC99EB&lt&#B4AAEE&le &#ABBBF1&lK&#A3CCF4&li&#9ADDF7&ls&#92EEFA&lt&#89FFFD&le"))
                .setLore(
                        "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("minivotekiste")),
                        "",
                        "  §7Rechte Maustaste §8● §2Vorschau",
                        "  §7Linke Maustaste §8● §2Öffnen",
                        "  §7Mittlere Maustaste §8● §2Test-Spin"
                );

        final ItemStack vote_Kiste = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("5e49985c3ce27dcc72b60bc4e0017316c71943715cecc97b3616d42a9d919ac1")
                .setName(Color.translateColorCodes("&#40C9FF&lV&#55B3FF&lo&#6A9EFF&lt&#7F88FF&le &#9473FF&lK&#A95DFF&li&#BE47FF&ls&#D332FF&lt&#E81CFF&le"))
                .setLore(
                        "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("votekiste")),
                        "",
                        "  §7Rechte Maustaste §8● §2Vorschau",
                        "  §7Linke Maustaste §8● §2Öffnen",
                        "  §7Mittlere Maustaste §8● §2Test-Spin"
                );

        final ItemStack antike_Kiste = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName(Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("antikekiste")),
                        "",
                        "  §7Rechte Maustaste §8● §2Vorschau",
                        "  §7Linke Maustaste §8● §2Öffnen",
                        "  §7Mittlere Maustaste §8● §2Test-Spin"
                );

        final ItemStack epische_Kiste = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName(Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("epischekiste")),
                        "",
                        "  §7Rechte Maustaste §8● §2Vorschau",
                        "  §7Linke Maustaste §8● §2Öffnen",
                        "  §7Mittlere Maustaste §8● §2Test-Spin"
                );

        final ItemStack eis_Kiste = new ItemCreator(Material.BLUE_ICE)
                .setName(Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Aktuelle Anzahl§8: §a" + CityBuild.getUserManager().getCase(player.getUniqueId(), CityBuild.getCaseManager().getCase("eiskiste")),
                        "",
                        "  §7Rechte Maustaste §8● §2Vorschau",
                        "  §7Linke Maustaste §8● §2Öffnen",
                        "  §7Mittlere Maustaste §8● §2Test-Spin"
                );

        final ItemStack meine_Kisten = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("279f02402eb523006afbc43b6662878a1bced9a42829fe2283000f2a18c89c89")
                .setName(Color.translateColorCodes("§a§lMeine Kisten"))
                .setLore(
                        "§8» §7Klicke§8, §7um alle deine verfügbaren Kisten zu sehen§8."
                );

        final ItemStack meine_Bits = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("ed0947c40de6789f6cfa2370add2a04c9855e45fde9483d655101e9510288ee8")
                .setName("§a§lAktuelle Bits§8: §2" + NumberFormat.getInstance().format(user.getBits()));

        final ItemStack kisten_kaufen = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("7e3deb57eaa2f4d403ad57283ce8b41805ee5b6de912ee2b4ea736a9d1f465a7")
                .setName("§a§lKaufe hier deine Kisten!")
                .setLore(
                        "§8» §7Klicke§8, §7um das §2Kisten kaufen§7 Menü zu öffnen§8."
                );

        Utility.fillInventory(inventory, glass);

        inventory.setItem(20, miniVote_Kiste);
        inventory.setItem(29, vote_Kiste);
        inventory.setItem(24, epische_Kiste);
        inventory.setItem(33, antike_Kiste);
        inventory.setItem(13, eis_Kiste);
        inventory.setItem(48, meine_Kisten);
        inventory.setItem(49, meine_Bits);
        inventory.setItem(50, kisten_kaufen);

        return inventory;
    }
}
