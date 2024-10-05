package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScoreboardManager {

    public void hook(final @NonNull Player player) {
        ConstStorage.getScoreboard().put(player.getUniqueId(), new FastBoard(player));

    }

    public void delete(final @NonNull Player player) {
        ConstStorage.getScoreboard().remove(player.getUniqueId());
    }


    public void update(final @NonNull UUID uuid) {
        final FastBoard fastBoard = ConstStorage.getScoreboard().get(uuid);
        final User user = CityBuild.getUserManager().handle(uuid);
        final OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        final long hours = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 3600;
        String dateFormat = new SimpleDateFormat("MMM dd, yyy").format(new Date());

        fastBoard.updateTitle(ConstStorage.getTITLE());
        fastBoard.updateLines(
                "  §7⌚ " + dateFormat,
                "",
                "§2€ §8┃ §7Kontostand",
                "   §8» §a" + NumberFormat.getInstance().format(user.getMoney()),
                "",
                "§2₪ §8┃ §7Bits",
                "   §8» §a" + NumberFormat.getInstance().format(user.getBits()),
                "",
                "§2⌛ §8┃ §7Spielzeit",
                "   §8» §a" + (hours < 1 ? "0  §aStd" : hours + " §aStd."),
                "",
                "§2✎ §8┃ §7Spieler",
                "   §8» §a" + Bukkit.getOnlinePlayers().size(),
                "",
                "§8dc.minetropia.de"
        );
    }
}
