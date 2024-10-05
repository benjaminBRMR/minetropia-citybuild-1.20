package net.citybuild.backend.storage;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.gutschein.Gutschein;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.sound.SoundUtility;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class VoucherStorage {

    @Getter
    private final @NonNull List<Gutschein> voucher = new ArrayList<>() {{
        add(new Gutschein(new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue("9d9cc58ad25a1ab16d36bb5d6d493c8f5898c2bf302b64e325921c41c35867")
                .setName("§b§lErfahrener Würfel")
                .setLore(
                        "",
                        "§fDer §berfahrene Würfel §fgewährt zufällig zwischen",
                        "§e2 §fund §e10 §fErfahrungslevel beim Rechtsklicken§8."
                ), ((player, itemStack) -> {
            int randomXP = (int) ((Math.random() * 9) + 2);
            player.giveExpLevels(randomXP);
            Message.sendMessage(player, "§aDu hast §e" + randomXP + " §aErfahrungslevel gutgeschrieben bekommen§8.");
            SoundUtility.rareDropJingle(player);
            Utility.removeHand(player);
        })));

        add(new Gutschein(new ItemCreator(Material.PAPER)
                .setName("§e§lPlot Merge Gutschein")
                .setLore(
                        "",
                        "§fVerbinde §e1§8x §fkostenlos deine Plots",
                        "§fund spare dir somit §e10.000€§8."
                ), ((player, itemStack) -> {

            SoundUtility.rareDropJingle(player);
            Utility.removeHand(player);
        })));

        add(new Gutschein(new ItemCreator(Material.NAME_TAG)
                .setName("§d§lZufälliges Perk")
                .glow(true)
                .setLore(
                        "",
                        "§fHole dir ein §dzufälliges §fPerk§8, §fdas",
                        "§fim §eCase-Opening §c§lnicht §fzu finden ist§8."
                ), ((player, itemStack) -> {
            Utility.giveRandomPerkWithoutCaseOpening(player);
            Utility.playSuccess(player);
        })));

        add(new Gutschein(new ItemCreator(Material.NAME_TAG)
                .setName("§d§lZufälliges Perk")
                .glow(true)
                .setLore(
                        "",
                        "§fHole dir ein §dzufälliges §fPerk§8,",
                        "§fdas im §eCase-Opening §fzu finden ist§8."
                ), ((player, itemStack) -> {
            Utility.giveRandomPerkWithCaseOpening(player);
            Utility.playSuccess(player);
        })));

        add(new Gutschein(new ItemCreator(Material.FISHING_ROD)
                .setName(Utility.translateHex("#4A306D&lO#4A306D&lb#4A306D&ls#4A306D&li#4A306D&ld#4A306D&li#4A306D&la#4A306D&ln #4A306D&lA#4A306D&ln#4A306D&lg#4A306D&le#4A306D&ll"))
                .glow(true)
                .setUnbreakable(true)
                .addItemFlag(ItemFlag.HIDE_UNBREAKABLE)
                .setLore(
                        "",
                        "§8» §7Verwendungen§8: §e500§8/§6" + ConstStorage.getMaxUsesMap().getOrDefault("Obsidian Angel", 0),
                        "",
                        "§fVerwende diese einzigartige Angel§8,",
                        "§fum bis zu " + Utility.translateHex("#4A306D&l500") + " §fObsidian zu fangen§8."
                ), null));
    }};


}
