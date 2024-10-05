package net.citybuild.backend.task;

import lombok.Getter;
import net.citybuild.CityBuild;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TaskClearLag {

    private final BukkitTask clearLagTask;
    private long targetBroadcastTime;

    @Getter
    private String remaining;

    public TaskClearLag() {
        targetBroadcastTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15);

        clearLagTask = new BukkitRunnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long timeRemaining = targetBroadcastTime - currentTime;

                remaining = new SimpleDateFormat("mm:ss").format(timeRemaining);

                long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(timeRemaining);
                long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(timeRemaining) - TimeUnit.MINUTES.toSeconds(minutesRemaining);

                if (minutesRemaining == 10 && secondsRemaining == 0) {
                    Message.sendMessageAll("§cAchtung, es werden in §e10 Minuten §calle Gegenstände auf dem Boden entfernt§8.");
                } else if (minutesRemaining == 5 && secondsRemaining == 0) {
                    Message.sendMessageAll("§cAchtung, es werden in §e5 Minuten §calle Gegenstände auf dem Boden entfernt§8.");
                } else if (minutesRemaining == 0 && secondsRemaining == 30) {
                    Message.sendMessageAll("§cAchtung, es werden in §e30 Sekunden §calle Gegenstände auf dem Boden entfernt§8.");
                } else if (timeRemaining <= 0) {
                    for (World world : Bukkit.getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity instanceof Item) {
                                entity.remove();
                            }
                        }
                    }
                    Message.sendMessageAll("§cAlle Gegenstände auf dem Boden wurden entfernt§8.");
                    targetBroadcastTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15);
                }
            }
        }.runTaskTimer(CityBuild.getINSTANCE(), 0L, 20L);
    }
}


