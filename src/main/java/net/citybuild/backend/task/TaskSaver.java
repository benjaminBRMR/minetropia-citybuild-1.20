package net.citybuild.backend.task;

import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class TaskSaver {

    private final BukkitTask taskSaverTask;

    public TaskSaver() {
        taskSaverTask = new BukkitRunnable() {
            @Override
            public void run() {

                CityBuild.getCaseManager().saveAll();
                CityBuild.getUserManager().saveAll();
                CityBuild.getCcManager().saveAll();

                if (ConstStorage.isLOGGER()) {
                    System.out.println("[USER] Autosaving cases... *" + CityBuild.getCaseManager().getCases().size());
                    System.out.println("[USER] Autosaving users... *" + CityBuild.getUserManager().getUsers().size());
                    System.out.println("[USER] Autosaving creatorcodes... *" + CityBuild.getCcManager().getCreatorCodes().size());
                }
            }
        }.runTaskTimer(CityBuild.getINSTANCE(), 0L, TimeUnit.HOURS.toMillis(1));
    }
}
