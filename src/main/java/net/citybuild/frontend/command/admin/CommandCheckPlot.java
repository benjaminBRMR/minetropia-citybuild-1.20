package net.citybuild.frontend.command.admin;

import com.plotsquared.core.plot.Plot;
import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class CommandCheckPlot extends ExecutableCommand {

    public CommandCheckPlot() {
        super("checkplot", "citybuild.checkplot");
    }

    // FIXME: 19.08.2023 syntax @Benni


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (Permission.hasPermission(player, "citybuild.checkplot")) {

            if (args.length == 0) {

                Plot plot = Utility.getPlotFromLocation(player);

                if (!player.getWorld().getName().equals(ConstStorage.getPlotWelt())) {
                    Message.sendMessage(player, "§cDu bist nicht in der Plot Welt§8!");
                    return;
                }

                if (plot == null || plot.getOwner() == null) {
                    Message.sendMessage(player, "§cDieses Plot hat aktuell keinen Besitzer§8.");
                    return;
                }

                final OfflinePlayer target = Bukkit.getOfflinePlayer(plot.getOwner());

                if (target.isOnline()) {
                    Message.sendMessage(player, "§7Der Besitzer §e" + target.getName() + " §7ist aktuell §a§lonline§8.");
                    return;
                }
                Message.sendMessage(player, "§7Der Besitzer §e" + target.getName() + " §7war zuletzt am §e" + new SimpleDateFormat("dd.MM.yyyy, HH:mm").format(target.getLastPlayed()) + " §7online§8.");
                return;
            }


            return;
        }
    }
}
