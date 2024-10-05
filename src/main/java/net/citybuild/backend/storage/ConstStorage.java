package net.citybuild.backend.storage;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.fastboard.FastBoard;
import net.citybuild.frontend.inventory.begleiter.InventoryBegleiter;
import net.citybuild.frontend.inventory.cases.InventoryMyCases;
import net.citybuild.frontend.inventory.cases.InventoryShowcase;
import net.citybuild.frontend.inventory.perk.InventoryPerk;
import net.citybuild.frontend.inventory.plotmenu.InventoryPlotMenu;
import net.citybuild.frontend.inventory.voucher.InventoryVoucher;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class ConstStorage {

    @Getter
    private final String plotWelt = "Plotssquared-Plots";



    @Getter
    private final String TITLE = Color.translateColorCodes("&#72A300&lᴍ&#829F00&lɪ&#919A00&lɴ&#A19600&lᴇ&#B19200&lᴛ&#C08D00&lʀ&#D08900&lᴏ&#E08500&lᴘ&#EF8000&lɪ&#FF7C00&lᴀ");
    @Getter
    private final String PREFIX = Color.translateColorCodes("&#72A300&lM&#829F00&li&#919A00&ln&#A19600&le&#B19200&lT&#C08D00&lr&#D08900&lo&#E08500&lp&#EF8000&li&#FF7C00&la") + " §8» §7";
    @Getter
    private final String OFFLINE = "§cDieser Spieler ist aktuell nicht online!";
    @Getter
    private final String UNKNOWN = "§cDieser Spieler war noch nie hier!";
    @Getter
    private final String NOPERM = "§cDazu hast du keine Berechtigung!";
    @Getter
    private final boolean LOGGER = true;
    @Getter
    private final long afkAfter = TimeUnit.MINUTES.toMillis(5);
    @Getter
    private final Cache<Player, Long> perkToggle = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();
    @Getter
    private final Cache<Player, Long> begleiterToggle = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();


    @Getter
    private final HashMap<Player, InventoryShowcase> caseShowcase = new HashMap<>();
    @Getter
    private final HashMap<Player, InventoryMyCases> myCases = new HashMap<>();
    @Getter
    private final HashMap<Player, InventoryPerk> perks = new HashMap<>();
    @Getter
    private final HashMap<Player, InventoryBegleiter> begleiter = new HashMap<>();
    @Getter
    private final HashMap<Player, InventoryPlotMenu> plotMenu = new HashMap<>();
    @Getter
    private final HashMap<Player, InventoryVoucher> voucher = new HashMap<>();


    @Getter
    private final HashMap<UUID, FastBoard> scoreboard = new HashMap<>();
    @Getter
    private final HashMap<Player, Long> afkTimer = new HashMap<>();
    @Getter
    private final HashMap<Player, Long> lastMovement = new HashMap<>();
    @Getter
    private final HashMap<Player, Boolean> previousData = new HashMap<>();
    @Getter
    private final HashMap<String, Integer> maxUsesMap = new HashMap<>(){{
       put("Obsidian Angel", 500);
    }};

    @Getter
    public static final HashMap<String, String> skulls = new HashMap<>(){{
        put("a67d813ae7ffe5be951a4f41f2aa619a5e3894e85ea5d4986f84949c63d7672e", "§7Buchstabe §8'§eA§8'");
        put("50c1b584f13987b466139285b2f3f28df6787123d0b32283d8794e3374e23", "§7Buchstabe §8'§eB§8'");
        put("abe983ec478024ec6fd046fcdfa4842676939551b47350447c77c13af18e6f", "§7Buchstabe §8'§eC§8'");
        put("3193dc0d4c5e80ff9a8a05d2fcfe269539cb3927190bac19da2fce61d71", "§7Buchstabe §8'§eD§8'");
        put("dbb2737ecbf910efe3b267db7d4b327f360abc732c77bd0e4eff1d510cdef", "§7Buchstabe §8'§eE§8'");
        put("b183bab50a3224024886f25251d24b6db93d73c2432559ff49e459b4cd6a", "§7Buchstabe §8'§eF§8'");
        put("1ca3f324beeefb6a0e2c5b3c46abc91ca91c14eba419fa4768ac3023dbb4b2", "§7Buchstabe §8'§eG§8'");
        put("31f3462a473549f1469f897f84a8d4119bc71d4a5d852e85c26b588a5c0c72f", "§7Buchstabe §8'§eH§8'");
        put("46178ad51fd52b19d0a3888710bd92068e933252aac6b13c76e7e6ea5d3226", "§7Buchstabe §8'§eI§8'");
        put("3a79db9923867e69c1dbf17151e6f4ad92ce681bcedd3977eebbc44c206f49", "§7Buchstabe §8'§eJ§8'");
        put("9461b38c8e45782ada59d16132a4222c193778e7d70c4542c9536376f37be42", "§7Buchstabe §8'§eK§8'");
        put("319f50b432d868ae358e16f62ec26f35437aeb9492bce1356c9aa6bb19a386", "§7Buchstabe §8'§eL§8'");
        put("49c45a24aaabf49e217c15483204848a73582aba7fae10ee2c57bdb76482f", "§7Buchstabe §8'§eM§8'");
        put("35b8b3d8c77dfb8fbd2495c842eac94fffa6f593bf15a2574d854dff3928", "§7Buchstabe §8'§eN§8'");
        put("d11de1cadb2ade61149e5ded1bd885edf0df6259255b33b587a96f983b2a1", "§7Buchstabe §8'§eO§8'");
        put("a0a7989b5d6e621a121eedae6f476d35193c97c1a7cb8ecd43622a485dc2e912", "§7Buchstabe §8'§eP§8'");
        put("43609f1faf81ed49c5894ac14c94ba52989fda4e1d2a52fd945a55ed719ed4", "§7Buchstabe §8'§eQ§8'");
        put("a5ced9931ace23afc351371379bf05c635ad186943bc136474e4e5156c4c37", "§7Buchstabe §8'§eR§8'");
        put("3e41c60572c533e93ca421228929e54d6c856529459249c25c32ba33a1b1517", "§7Buchstabe §8'§eS§8'");
        put("1562e8c1d66b21e459be9a24e5c027a34d269bdce4fbee2f7678d2d3ee4718", "§7Buchstabe §8'§eT§8'");
        put("607fbc339ff241ac3d6619bcb68253dfc3c98782baf3f1f4efdb954f9c26", "§7Buchstabe §8'§eU§8'");
        put("cc9a138638fedb534d79928876baba261c7a64ba79c424dcbafcc9bac7010b8", "§7Buchstabe §8'§eV§8'");
        put("269ad1a88ed2b074e1303a129f94e4b710cf3e5b4d995163567f68719c3d9792", "§7Buchstabe §8'§eW§8'");
        put("5a6787ba32564e7c2f3a0ce64498ecbb23b89845e5a66b5cec7736f729ed37", "§7Buchstabe §8'§eX§8'");
        put("c52fb388e33212a2478b5e15a96f27aca6c62ac719e1e5f87a1cf0de7b15e918", "§7Buchstabe §8'§eY§8'");
        put("90582b9b5d97974b11461d63eced85f438a3eef5dc3279f9c47e1e38ea54ae8d", "§7Buchstabe §8'§eZ§8'");
        put("15cd6db9ec3c7d9113e6dd49a16f99a326b9f594ce987f919559ac7dbd3b555", "§7Buchstabe §8'§eÄ§8'");
        put("6edbb44c639b95308ffcdf8c4770dfe8b02d752dec4b3196f4a8f9ac2315393a", "§7Buchstabe §8'§eÖ§8'");
        put("7b52b94c6516cbe461fea621d316cee0b875f0fbc239d25273e824b613e73dd4", "§7Buchstabe §8'§eÜ§8'");
        put("71bc2bcfb2bd3759e6b1e86fc7a79585e1127dd357fc202893f9de241bc9e530", "§7Zahl §8'§e1§8'");
        put("4cd9eeee883468881d83848a46bf3012485c23f75753b8fbe8487341419847", "§7Zahl §8'§e2§8'");
        put("1d4eae13933860a6df5e8e955693b95a8c3b15c36b8b587532ac0996bc37e5", "§7Zahl §8'§e3§8'");
        put("d2e78fb22424232dc27b81fbcb47fd24c1acf76098753f2d9c28598287db5", "§7Zahl §8'§e4§8'");
        put("6d57e3bc88a65730e31a14e3f41e038a5ecf0891a6c243643b8e5476ae2", "§7Zahl §8'§e5§8'");
        put("334b36de7d679b8bbc725499adaef24dc518f5ae23e716981e1dcc6b2720ab", "§7Zahl §8'§e6§8'");
        put("6db6eb25d1faabe30cf444dc633b5832475e38096b7e2402a3ec476dd7b9", "§7Zahl §8'§e7§8'");
        put("59194973a3f17bda9978ed6273383997222774b454386c8319c04f1f4f74c2b5", "§7Zahl §8'§e8§8'");
        put("e67caf7591b38e125a8017d58cfc6433bfaf84cd499d794f41d10bff2e5b840", "§7Zahl §8'§e9§8'");
        put("0ebe7e5215169a699acc6cefa7b73fdb108db87bb6dae2849fbe24714b27", "§7Zahl §8'§e0§8'");
        put("ccbee28e2c79db138f3977ba472dfae6b11a9bb82d5b3d7f25479338fff1fe92", "§7Sonderzeichen §8'§e:§8'");
        put("7f95d7c1bbf3afa285d8d96757bb5572259a3ae854f5389dc53207699d94fd8", "§7Sonderzeichen §8'§e/§8'");
        put("336febeca7c488a6671dc071655dde2a1b65c3ccb20b6e8eaf9bfb08e64b80", "§7Sonderzeichen §8'§e❤§8'");
        put("bd8a99db2c37ec71d7199cd52639981a7513ce9cca9626a3936f965b131193", "§7Sonderzeichen §8'§e-§8'");
        put("9ae85f74f8e2c054b781a29fa9b25934ba63bb79f1de8a95b436d9bfdcaf4cd", "§7Sonderzeichen §8'§e#§8'");
        put("7966f891c1546aecbfcc3baedcfb67079d7f2a6a8b739ed5bac2bb3cf308d38", "§7Sonderzeichen §8'§e_§8'");
        put("50851cf062548c436253c337a4112cfc985443a748d931cf201d1e84fc72b12c", "§7Sonderzeichen §8'§e%§8'");
    }};
}
