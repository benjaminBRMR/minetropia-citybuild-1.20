package net.citybuild.frontend.inventory.booster;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;

@UtilityClass
public class InventoryBooster {

    public Inventory getBoosterInventory(final @NonNull Player player) {

        final Inventory inventory = Bukkit.createInventory(null, 54, "§8» §f§lBooster-Menü");
        final ItemStack glass = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).setName("§r");
        Utility.fillInventory(inventory, glass);

        final User user = CityBuild.getUserManager().handle(player.getUniqueId());

        final String[] multiplier = new String[]{
                "1919d1594bf809db7b44b3782bf90a69f449a87ce5d18cb40eb653fdec2722",
                "5506ee0e382a2891eb1a3322a1c886c9d97c3485fbc127b33689fc1283e71c",
                "c3cf2c1523f5e91c3bdab076b395a1b381121ca1c0b42ae6783fb7ce518a",
                "42cd2bff21f6918ea7da41833d99a0bdb019aca2a67e5dc20f1a17a61b3d62a",
                "dacc5994884573dbc6cd7b503cc55b733b18b85a1aea5f401c065326cc3c3f",
                "6415a5b9f471be6e261f6bb5a080d6661acc34d493afa37d7815f6607819402e",
                "496e2a3fe1ed64436e8d60b9f999ff38701ac13b14510dacbf0704060fb2a",
                "86a51b9374e6dbceaa17337d3bd833f7dba611fc36965d98c1dd2b587cacec",
                "76c76f62cb6b557497f322ff95372e4518ca7d9879b76d46da9ee01a3cabe94f"
        };


        final ItemStack flyBooster = new ItemCreator(Material.FEATHER)
                .setName("§f§lBooster§8: §a§lFly")
                .setLore(
                        "§8» §7Effekt§8: §f§lFliegen",
                        "",
                        "  §7Wenn du beim Bauen deines Plots",
                        "  §7fliegen möchtest§8, §7ist der §f§lFly Booster",
                        "  §7die perfekte Lösung für dich§8!",
                        ""
                );

        final ItemStack xpBooster = new ItemCreator(Material.EXPERIENCE_BOTTLE)
                .setName("§f§lBooster§8: §a§lXP")
                .setLore(
                        "§8» §7Effekt§8: §f§lErfahrungspunkte vermehrt",
                        "",
                        "  §7Du möchtest schnell Level bekommen und",
                        "  §7erhältst zu wenige Erfahrungspunkte§8?",
                        "  §7Der §f§lXP Booster §7hilft dir dabei§8!",
                        ""
                );

        final ItemStack breakBooster = new ItemCreator(Material.NETHERITE_PICKAXE)
                .setName("§f§lBooster§8: §a§lBreak")
                .setLore(
                        "§8» §7Effekt§8: §f§lEile VII",
                        "",
                        "  §7Du willst diverse Materialien farmen§8,",
                        "  §7doch deine Schaufel ist nicht effizient",
                        "  §7genug§8? §7Der §f§lBreak Booster §7hilft dir§8!",
                        ""
                )
                .addItemFlag(ItemFlag.HIDE_ATTRIBUTES);


        final ItemStack boosterInfo = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("900d28ff7b543dd088d004b1b1f95b38d444ea0461ff5ae3c68d76c0c16e2527")
                .setName("§f§lBooster Übersicht")
                .setLore(
                        "§8» §7Verfügbare Booster§8: §f§l" + NumberFormat.getInstance().format(user.getBoosters()),
                        "§8» §7Verfügbare §a§lUltra §7Booster§8: §f§l" + NumberFormat.getInstance().format(user.getUltraBoosters())
                );

        final ItemStack booster_kaufen = new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("7e3deb57eaa2f4d403ad57283ce8b41805ee5b6de912ee2b4ea736a9d1f465a7")
                .setName("§f§lKaufe hier deine Booster!")
                .setLore(
                        "§8» §7Klicke§8, §7um das §fBooster kaufen§7 Menü zu öffnen§8."
                );

        inventory.setItem(39, boosterInfo);
        inventory.setItem(41, booster_kaufen);

        inventory.setItem(10, flyBooster);
        inventory.setItem(11, xpBooster);
        inventory.setItem(12, breakBooster);

        inventory.setItem(19, new ItemCreator(Material.PLAYER_HEAD)
                .setName("§c✘")
                .setSkullValue(multiplier[0]));

        inventory.setItem(20, new ItemCreator(Material.PLAYER_HEAD)
                .setName("§c✘")
                .setSkullValue(multiplier[0]));

        inventory.setItem(21, new ItemCreator(Material.PLAYER_HEAD)
                .setName("§c✘")
                .setSkullValue(multiplier[0]));

        if (CityBuild.getBoosterManager().getBoosterMultiplier().get(Booster.FLY) != null) {
            inventory.setItem(19, new ItemCreator(Material.PLAYER_HEAD)
                    .setName("§8» §7Stufe§8: §f" + ((int) CityBuild.getBoosterManager().getMultiplier(Booster.FLY)))
                    .setSkullValue(multiplier[(int) CityBuild.getBoosterManager().getMultiplier(Booster.FLY)]));
        }

        if (CityBuild.getBoosterManager().getBoosterMultiplier().get(Booster.XP) != null) {
            inventory.setItem(20, new ItemCreator(Material.PLAYER_HEAD)
                    .setName("§8» §7Stufe§8: §f" + ((int) CityBuild.getBoosterManager().getMultiplier(Booster.XP)))
                    .setSkullValue(multiplier[(int) CityBuild.getBoosterManager().getMultiplier(Booster.XP)]));
        }

        if (CityBuild.getBoosterManager().getBoosterMultiplier().get(Booster.BREAK) != null) {
            inventory.setItem(21, new ItemCreator(Material.PLAYER_HEAD)
                    .setName("§8» §7Stufe§8: §f" + ((int) CityBuild.getBoosterManager().getMultiplier(Booster.BREAK)))
                    .setSkullValue(multiplier[(int) CityBuild.getBoosterManager().getMultiplier(Booster.BREAK)]));
        }

        return inventory;
    }

}
