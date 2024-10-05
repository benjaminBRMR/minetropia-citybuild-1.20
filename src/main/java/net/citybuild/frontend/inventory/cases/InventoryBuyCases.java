package net.citybuild.frontend.inventory.cases;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@UtilityClass
public class InventoryBuyCases {


    public Inventory getCaseInventory(final @NonNull Player player) {

        final Inventory inventory = Bukkit.createInventory(null, 45, "§8» §a§lKaufe hier deine Kisten!");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        final ItemStack back = new ItemCreator(Material.SPRUCE_DOOR).setName("§cZurück");


        final ItemStack antike_Kiste1x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName("§a§l1§8x " + Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Preis§8: §e600 Bits"
                );

        final ItemStack antike_Kiste5x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName("§a§l5§8x " + Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Preis§8: §e2.400 Bits",
                        "§8» §7Rabatt§8: §e§l20%"

                );

        final ItemStack antike_Kiste10x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName("§a§l10§8x " + Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Preis§8: §e4.800 Bits",
                        "§8» §7Rabatt§8: §e§l20%"


                );

        final ItemStack antike_Kiste15x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName("§a§l15§8x " + Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Preis§8: §e7.200 Bits",
                        "§8» §7Rabatt§8: §e§l20%"
                );

        final ItemStack antike_Kiste20x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("8a75619deabcf4fe8603909ac79d2b6d6f31cdb84603f8bae07ef0e24891def3")
                .setName("§a§l20§8x " + Color.translateColorCodes("&#7C65A9&lA&#7F70AC&ln&#817BB0&lt&#8486B3&li&#8691B6&lk&#899DBA&le &#8CA8BD&lK&#8EB3C0&li&#91BEC3&ls&#93C9C7&lt&#96D4CA&le"))
                .setLore(
                        "§8» §7Preis§8: §e9.600 Bits",
                        "§8» §7Rabatt§8: §e§l20%"
                );

        final ItemStack epische_kiste1x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName("§a§l1§8x " + Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Preis§8: §e300 Bits"
                );

        final ItemStack epische_kiste5x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName("§a§l5§8x " + Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Preis§8: §e1.200 Bits",
                        "§8» §7Rabatt§8: §e§l20%"

                );

        final ItemStack epische_kiste10x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName("§a§l10§8x " + Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Preis§8: §e2.400 Bits",
                        "§8» §7Rabatt§8: §e§l20%"

                );

        final ItemStack epische_kiste15x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName("§a§l15§8x " + Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Preis§8: §e3.400 Bits",
                        "§8» §7Rabatt§8: §e§l25%"
                );

        final ItemStack epische_kiste20x = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("6e639e41be368830a2c7ba3fbd69e2ebaf8c391d3f725c2844e8a52b456af421")
                .setName("§a§l20§8x " + Color.translateColorCodes("&#5FA5E2&lE&#5B9BCE&lp&#5791BA&li&#5387A6&ls&#4F7D92&lc&#4B737E&lh&#486969&le &#445F55&lK&#405541&li&#3C4B2D&ls&#384119&lt&#343705&le"))
                .setLore(
                        "§8» §7Preis§8: §e4.500 Bits",
                        "§8» §7Rabatt§8: §e§l25%"
                );

        //

        final ItemStack eis_kiste1x = new ItemCreator(Material.BLUE_ICE)
                .setName("§a§l1§8x " + Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Preis§8: §e1.000 Bits"
                );

        final ItemStack eis_kiste5x = new ItemCreator(Material.BLUE_ICE)
                .setName("§a§l5§8x " + Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Preis§8: §e4.500 Bits",
                        "§8» §7Rabatt§8: §e§l10%"

                );

        final ItemStack eis_kiste10x = new ItemCreator(Material.BLUE_ICE)
                .setName("§a§l10§8x " + Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Preis§8: §e9.000 Bits",
                        "§8» §7Rabatt§8: §e§l10%"

                );

        final ItemStack eis_kiste15x = new ItemCreator(Material.BLUE_ICE)
                .setName("§a§l15§8x " + Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Preis§8: §e13.500 Bits",
                        "§8» §7Rabatt§8: §e§l10%"
                );

        final ItemStack eis_kiste20x = new ItemCreator(Material.BLUE_ICE)
                .setName("§a§l20§8x " + Color.translateColorCodes("&#ADFFF0&lE&#9DEDF2&li&#8DDBF4&ls &#7DC9F6&lK&#6CB6F9&li&#5CA4FB&ls&#4C92FD&lt&#3C80FF&le"))
                .setLore(
                        "§8» §7Preis§8: §e18.000 Bits",
                        "§8» §7Rabatt§8: §e§l10%"
                );


        Utility.fillInventory(inventory, glass);


        inventory.setItem(11, epische_kiste1x);
        inventory.setItem(12, epische_kiste5x);
        inventory.setItem(13, epische_kiste10x);
        inventory.setItem(14, epische_kiste15x);
        inventory.setItem(15, epische_kiste20x);


        inventory.setItem(20, antike_Kiste1x);
        inventory.setItem(21, antike_Kiste5x);
        inventory.setItem(22, antike_Kiste10x);
        inventory.setItem(23, antike_Kiste15x);
        inventory.setItem(24, antike_Kiste20x);


        inventory.setItem(29, eis_kiste1x);
        inventory.setItem(30, eis_kiste5x);
        inventory.setItem(31, eis_kiste10x);
        inventory.setItem(32, eis_kiste15x);
        inventory.setItem(33, eis_kiste20x);

        inventory.setItem(36, back);


        return inventory;
    }

}
