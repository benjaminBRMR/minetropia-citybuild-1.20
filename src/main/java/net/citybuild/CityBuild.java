package net.citybuild;

import com.plotsquared.core.PlotAPI;
import lombok.Getter;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.internal.HolographicDisplaysAPIProvider;
import net.citybuild.backend.begleiter.controller.BegleiterController;
import net.citybuild.backend.initializer.Initializer;
import net.citybuild.backend.manager.*;
import net.citybuild.backend.perk.controller.PerkController;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.task.TaskClearLag;
import net.citybuild.backend.task.TaskMobRemover;
import net.citybuild.backend.task.TaskSaver;
import net.citybuild.backend.task.TaskTablist;
import net.citybuild.backend.utility.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class CityBuild extends JavaPlugin {

    @Getter
    private static CityBuild INSTANCE;
    @Getter
    private static DBManager dbManager;
    @Getter
    private static UserManager userManager;
    @Getter
    private static CaseManager caseManager;
    @Getter
    private static LocationManager locationManager;
    @Getter
    private static CCManager ccManager;
    @Getter
    private static ScoreboardManager scoreboardManager;
    @Getter
    private static BoosterManager boosterManager;
    @Getter
    private static TablistManager tablistManager;
    @Getter
    private static AFKManager afkManager;
    @Getter
    private static InviteManager inviteManager;
    @Getter
    private static VoucherManager voucherManager;
    @Getter
    private static UsesManager usesManager;

    private Initializer initializer;
    private TaskTablist taskTablist;
    private TaskSaver taskSaver;
    @Getter
    private TaskClearLag taskClearLag;
    @Getter
    private TaskMobRemover taskMobRemover;
    @Getter
    private BegleiterController begleiterController;
    @Getter
    private PerkController perkController;
    @Getter
    private PlotAPI plotAPI;

    @Override
    public void onEnable() {
        INSTANCE = this;

        dbManager = new DBManager();

        userManager = new UserManager();
        caseManager = new CaseManager();
        locationManager = new LocationManager();
        scoreboardManager = new ScoreboardManager();
        ccManager = new CCManager();
        boosterManager = new BoosterManager();
        tablistManager = new TablistManager();
        afkManager = new AFKManager();
        inviteManager = new InviteManager();
        voucherManager = new VoucherManager();
        usesManager = new UsesManager();
        initializer = new Initializer();


        taskClearLag = new TaskClearLag();
        taskMobRemover = new TaskMobRemover();
        taskTablist = new TaskTablist();
        taskSaver = new TaskSaver();
        begleiterController = new BegleiterController();
        perkController = new PerkController();
        plotAPI = new PlotAPI();
    }


    @Override
    public void onDisable() {
        try {

            begleiterController.deleteAll();
            Bukkit.getOnlinePlayers().forEach(players -> players.kickPlayer("\n\n" + ConstStorage.getPREFIX() + Utility.toLuni("Der Server startet nun neu!")));

            System.out.println("[DB] Saving data... this may take a moment!");
            userManager.saveAll();
            caseManager.saveAll();
            locationManager.saveAll();
            ccManager.saveAll();
            inviteManager.saveAll();
            Thread.sleep(2000);
            dbManager.getMongoManager().close();
            System.out.println("[DB] Successfully saved data. Closing...");


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
