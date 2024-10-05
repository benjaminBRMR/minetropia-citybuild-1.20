package net.citybuild.backend.task;


import lombok.Getter;
import net.citybuild.CityBuild;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TaskMobRemover {

    private final BukkitTask mobRemoverTask;
    private long targetBroadcastTime;

    @Getter
    private String remaining;


    public TaskMobRemover() {
        targetBroadcastTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(45);

        mobRemoverTask = new BukkitRunnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long timeRemaining = targetBroadcastTime - currentTime;

                remaining = new SimpleDateFormat("mm:ss").format(timeRemaining);


                long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(timeRemaining);
                long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(timeRemaining) - TimeUnit.MINUTES.toSeconds(minutesRemaining);

                if (minutesRemaining == 10 && secondsRemaining == 0) {
                    Message.sendMessageAll("§7Alle Tiere & Monster werden in §e10 Minuten §7entfernt§8.");
                } else if (minutesRemaining == 5 && secondsRemaining == 0) {
                    Message.sendMessageAll("§7Alle Tiere & Monster werden in §e5 Minuten §7entfernt§8.");
                } else if (minutesRemaining == 0 && secondsRemaining == 30) {
                    Message.sendMessageAll("§7Alle Tiere & Monster werden in §e30 Sekunden §7entfernt§8.");
                } else if (timeRemaining <= 0) {
                    for (World world : Bukkit.getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity instanceof Mob) {
                                // FIXME: 21.08.2023
                                // if(!entity.getCustomName().contains("&a&l✔ &8┃ &7Geschützter ") + entity.getName()) hab name hingemacht wegen anti mob clear
                                entity.remove();
                            }
                        }
                    }
                    Message.sendMessageAll("§cAlle Tiere & Monster wurden entfernt§8.");
                    targetBroadcastTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(45);
                }
            }
        }.runTaskTimer(CityBuild.getINSTANCE(), 0L, 20L);
    }
}
