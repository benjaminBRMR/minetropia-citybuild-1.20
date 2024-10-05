package net.citybuild.backend.perk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Material;

import java.util.EnumSet;

@AllArgsConstructor
public enum Perk {

    ROAD_RUNNER(
            "Road Runner",
            Material.LEATHER_BOOTS,
            "§8» §7Vorteil§8: §fSpeed II",
            "  §7Du bist viel zu langsam und kommst nicht hinterher?",
            "  §7Dann hole dir dieses besondere Perk!",
            125000,
            null
    ),

    BUGS_BUNNY(
            "Bugs Bunny",
            Material.RABBIT_FOOT,
            "§8» §7Vorteil§8: §fSprungkraft II",
            "  §7Hüpfe höher als jeder andere",
            "  §7Spieler und sei ein Hase.",
            150000,
            null
    ),

    NO_FIREDAMAGE(
            "Kein Feuerschaden",
            Material.BLAZE_POWDER,
            "§8» §7Vorteil§8: §fFeuerresistenz",
            "  §7Du möchtest bequem im Nether dein Quarz",
            "  §7farmen? Kein Feuerschaden ermöglicht es dir!",
            350000,
            null
    ),

    NIGHTVISION(
            "Nachtsicht",
            Material.ENDER_EYE,
            "§8» §7Vorteil§8: §fNachtsicht",
            "  §7Du siehst kaum was in der Höhle?",
            "  §7Fühle dich frei mit dem Nachtsicht Perk!",
            120000,
            null
    ),

    NO_HUNGER(
            "Kein Hunger",
            Material.APPLE,
            "§8» §7Vorteil§8: §fSättigung",
            "  §7Geht dir das ständige Hungern auf die Nerven?",
            "  §7Fühle dich satt mit diesem Perk!",
            150000,
            null
    ),

    NO_DROWNING(
            "Kein Ertrinken",
            Material.CONDUIT,
            "§8» §7Vorteil§8: §fKein Wasserschaden",
            "  §7Fühle dich mit dem Meer verbunden",
            "  §7und hole dir das ultimative Perk.",
            250000,
            null
    ),

    NO_FALLDAMAGE(
            "Kein Fallschaden",
            Material.FEATHER,
            "§8» §7Vorteil§8: §fKein Fallschaden",
            "  §7Bei wiederholtem Schaden durch Stürze in der",
            "  §7Farmwelt ist dieses Perk die richtige Wahl.",
            350000,
            null
    ),

    ONE_WITH_THE_OCEAN(
            "Eins mit dem Meer",
            Material.FEATHER,
            "§8» §7Vorteil§8: §fDelfin Effekt",
            "  §7Wenn du im Wasser so flink wie ein Delfin sein",
            "  §7möchtest, solltest du dir dieses Perk besorgen.",
            300000,
            null
    ),

    SLOW_FALLING(
            "Sanfter Fall",
            Material.HAY_BLOCK,
            "§8» §7Vorteil§8: §fSanfter Fall Effekt",
            "  §7Du möchtest einen sanften und weniger",
            "  §7weh tuhenden Fall? Dann hol dir das Perk!",
            750000,
            null
    ),

    MINEWORKER(
            "Minenarbeiter",
            Material.BLAST_FURNACE,
            "§8» §7Vorteil§8: §fInstant-Ofen",
            "  §7Mit diesem Perk schmelzen Blöcke",
            "  §7und Erze sofort beim Abbauen.",
            500000,
            null
    ),

    GARDENER(
            "Gärtner",
            Material.STONE_HOE,
            "§8» §7Vorteil§8: §fInstant-Anpflanzer",
            "  §7Dank diesem Perks wird bsw. beim Abbau von Kartoffeln sofort eine neue Kartoffel",
            "  §7angebaut, sofern sich mind. eine Kartoffel in deinem Inventar befindet.",
            -1,
            null
    ),

    VOTEKICK(
            "Votekick",
            Material.COMMAND_BLOCK,
            "§8» §7Vorteil§8: §fAbstimmung eines Spieler-Kicks starten",
            "  §7Mithilfe dieses Perks kannst du bsw. einen Betrüger",
            "  §7durch eine Server-Abstimmung für 5 Minuten sperren.",
            1000000,
            "citybuild.votekick"
    ),

    VOTEMUTE(
            "Votemute",
            Material.BELL,
            "§8» §7Vorteil§8: §fAbstimmung eines Spieler-Mutes starten",
            "  §7Mithilfe dieses Perks kannst du bsw. einen Betrüger",
            "  §7durch eine Server-Abstimmung für 15 Minuten muten.",
            1200000,
            "citybuild.votemute"
    ),

    SLOWCHAT(
            "Slowchat",
            Material.COBWEB,
            "§8» §7Vorteil§8: §fVerlangsamt für alle den Chat",
            "  §7Das Perk gestattet dir, bei einer Überlastung des Chats einen",
            "  §7Slowchat zu aktivieren, wenn du dies für angemessen hältst.",
            1000000,
            "citybuild.slowchat"
    ),

    CLEARCHAT(
            "Clearchat",
            Material.WRITABLE_BOOK,
            "§8» §7Vorteil§8: §fSäubert für jedem den Chat",
            "  §7Mit diesem Perk hast du die Fähigkeit, den",
            "  §7Chat für jeden einzelnen Spieler zu säubern.",
            1500000,
            "citybuild.cc"
    ),

    KEEP_HOTBAR(
            "Keep Hotbar",
            Material.STRUCTURE_VOID,
            "§8» §7Vorteil§8: §fBeim Tod behältst du deine Hotbar",
            "  §7Mit diesem Perk behältst du",
            "  §7beim Tod deine gesamte Hotbar.",
            750000,
            null
    ),

    KEEP_INVENTORY(
            "Keep Inventory",
            Material.STRUCTURE_VOID,
            "§8» §7Vorteil§8: §fBeim Tod behältst du dein Inventar",
            "  §7Mit diesem Perk behältst du",
            "  §7beim Tod dein gesamtes Inventar.",
            1500000,
            null
    ),

    KEEP_EXP(
            "Keep XP",
            Material.STRUCTURE_VOID,
            "§8» §7Vorteil§8: §fBeim Tod behältst du deine Erfahrungslevel",
            "  §7Mit diesem Perk behältst du",
            "  §7beim Tod deine Erfahrungslevel.",
            700000,
            null
    ),

    OBSERVER(
            "Stalker",
            Material.OBSERVER,
            "§8» §7Vorteil§8: §fSiehe, welche Spieler den Server beitreten/verlassen",
            "  §7Mit diesem Perk hast du die Möglichkeit, Benachrichtigungen",
            "  §7zu erhalten, wenn ein Spieler den Server betritt und/oder verlässt.",
            1250000,
            null
    ),

    PLOT_FLY(
            "Plot fliegen",
            Material.FEATHER,
            "§8» §7Vorteil§8: §fDauerhaft Fly auf deinem Plot",
            "  §7Dieses Perk ermöglicht es dir, auf deinem Grundstück",
            "  §7zu fliegen, selbst ohne einen Fly-Booster",
            -1,
            null
    ),

    PAY_ALL(
            "Pay-All",
            Material.GOLD_INGOT,
            "§8» §7Vorteil§8: §fGeld an alle Spieler senden",
            "  §7Mit diesem Perk erhältst du die Befugnis,",
            "  §7allen Spielern auf dem Server Geld zu geben.",
            -1,
            "citybuild.pay.all"
    ),

    WIZARD(
            "Magier",
            Material.LIGHTNING_ROD,
            "§8» §7Vorteil§8: §fAbgebaute Blöcke gehen direkt in dein Iventar",
            "  §7Mit diesem Perk kannst du abgebaute Blöcke ",
            "  §7direkt in dein Inventar erhalten.",
            -1,
            null
    ),

    TODESKOORDINATEN(
            "Todeskoordinaten",
            Material.SKULL_POTTERY_SHERD,
            "§8» §7Vorteil§8: §fSiehe deine Todeskoordinaten",
            "  §7Siehe deine Koordinaten ein,",
            "  §7an dem Punkt, wo du gestorben bist.",
            150000,
            null
    ),

    GLOWING(
            "Leuchten",
            Material.GLOW_INK_SAC,
            "§8» §7Vorteil§8: §fReiche Spieler leuchten von weitem",
            "  §7Mithilfe dieses Perks kannst du",
            "  §7durch alle Blöcke hindurch leuchten.",
            -1,
            null
    );

    @Getter
    final String displayName;
    @Getter
    final Material material;
    @Getter
    final String advantage;
    @Getter
    final String descriptionOne;
    @Getter
    final String descriptionTwo;
    @Getter
    final long price;
    @Getter
    final String permission;

    public static Perk getPerkByDisplayName(final @NonNull String displayName) {
        for (Perk perks : EnumSet.allOf(Perk.class)) {
            if (perks.getDisplayName().equals(displayName)) return perks;
        }
        return null;
    }
}
