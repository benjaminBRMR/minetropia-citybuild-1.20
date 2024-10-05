package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AFKManager {

    //private final long MOVEMENT_THRESHOLD = 6000;

    public void hook(final @NonNull Player player) {
        ConstStorage.getLastMovement().put(player, System.currentTimeMillis());
    }

    public void update(final @NonNull Player player) {
        ConstStorage.getLastMovement().put(player, System.currentTimeMillis());
    }

    public void delete(final @NonNull Player player) {
        ConstStorage.getLastMovement().remove(player);
    }

    public boolean isAFK(final @NonNull Player player) {

        if (ConstStorage.getLastMovement().containsKey(player)) {
            if (ConstStorage.getLastMovement().get(player) == -1L) {
                return true;
            } else {
                long timeElapsed = System.currentTimeMillis() - ConstStorage.getLastMovement().get(player);
                return timeElapsed >= ConstStorage.getAfkAfter();
            }
        } else {
            ConstStorage.getLastMovement().put(player, System.currentTimeMillis());
        }

        return false;
    }

    public void checkAll() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (ConstStorage.getLastMovement().containsKey(onlinePlayer)) {
                final boolean nowAFK = isAFK(onlinePlayer);

                if (ConstStorage.getPreviousData().containsKey(onlinePlayer)) {
                    boolean wasAFK = ConstStorage.getPreviousData().get(onlinePlayer);

                    if (wasAFK && !nowAFK) {
                        long entryTime = ConstStorage.getAfkTimer().getOrDefault(onlinePlayer, 0L);
                        long currentTime = System.currentTimeMillis();
                        long afkTime = currentTime - entryTime;
                        Message.sendMessage(onlinePlayer, "§6Schön, dass du wieder da bist §e❤ §6Du warst §e" + Utility.timeToString(afkTime, true) + " §6Inaktiv§8.");
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
                        ConstStorage.getAfkTimer().remove(onlinePlayer);
                        ConstStorage.getPreviousData().put(onlinePlayer, false);
                        return;

                    }

                    if (!wasAFK && nowAFK && !ConstStorage.getAfkTimer().containsKey(onlinePlayer)) {
                        Message.sendMessage(onlinePlayer, "§cDu wurdest als Inaktiv von unserem System erkannt§8.");
                        onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
                        final long time = System.currentTimeMillis();
                        ConstStorage.getAfkTimer().put(onlinePlayer, time);
                        ConstStorage.getPreviousData().put(onlinePlayer, true);
                    }
                } else {
                    ConstStorage.getPreviousData().put(onlinePlayer, nowAFK);
                }


            }
        }
    }
}
