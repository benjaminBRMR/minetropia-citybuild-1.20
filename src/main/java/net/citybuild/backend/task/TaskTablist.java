package net.citybuild.backend.task;

import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import net.citybuild.backend.storage.ConstStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;

public class TaskTablist {

    private final BukkitTask tablistTask;


    public TaskTablist() {

        tablistTask = new BukkitRunnable() {
            @Override
            public void run() {

                String flyRunning = (CityBuild.getBoosterManager().isRunning(Booster.FLY) ? "§a✔ " + "§8x§2" + CityBuild.getBoosterManager().getMultiplier(Booster.FLY) + " §8(§e" + new SimpleDateFormat("HH:mm:ss").format(CityBuild.getBoosterManager().getRemainingBooster(Booster.FLY)) + "§8)" : "§c✘");
                String xpRunning = (CityBuild.getBoosterManager().isRunning(Booster.XP) ? "§a✔ " + "§8x§2" + CityBuild.getBoosterManager().getMultiplier(Booster.XP) + " §8(§e" + new SimpleDateFormat("mm:ss").format(CityBuild.getBoosterManager().getRemainingBooster(Booster.XP)) + "§8)" : "§c✘");
                String breakRunning = (CityBuild.getBoosterManager().isRunning(Booster.BREAK) ? "§a✔ " + "§8x§2" + CityBuild.getBoosterManager().getMultiplier(Booster.BREAK) + " §8(§e" + new SimpleDateFormat("HH:mm:ss").format(CityBuild.getBoosterManager().getRemainingBooster(Booster.BREAK)) + "§8)" : "§c✘");


                final String header = "\n" + ConstStorage.getTITLE() + "\n§8ʙᴇᴅʀᴏᴄᴋ ― ʙʀ.ᴍɪɴᴇᴛʀᴏᴘɪᴀ.ᴅᴇ\n\n§2Clearlag in §e" + CityBuild.getINSTANCE().getTaskClearLag().getRemaining() + " §8● §2Mobremover in §e" + CityBuild.getINSTANCE().getTaskMobRemover().getRemaining() + "\n                                                       ";
                final String footer = "\n§2Fly §8┃ §e" + flyRunning + " §8● §2XP §8┃ §e" + xpRunning + " §8● §2Break §8┃ §e" + breakRunning + "\n§8§oᴍᴇʜʀ ʙᴏᴏꜱᴛᴇʀ ꜱɪᴇʜꜱᴛ ᴅᴜ ᴍɪᴛ /ʙᴏᴏꜱᴛᴇʀ\n";


                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setPlayerListHeaderFooter(header, footer);
                    CityBuild.getScoreboardManager().update(onlinePlayer.getUniqueId());
                    CityBuild.getTablistManager().update(onlinePlayer);
                    CityBuild.getINSTANCE().getPerkController().tickPerk(onlinePlayer);
                }
                CityBuild.getAfkManager().checkAll();
                
                


            }
        }.runTaskTimer(CityBuild.getINSTANCE(), 0L, 20L);

    }
}
