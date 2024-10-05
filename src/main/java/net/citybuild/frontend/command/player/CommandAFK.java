package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CommandAFK extends ExecutableCommand {

    public CommandAFK() {
        super("afk", null);
    }

    // FIXME: 19.08.2023 syntax @Benni


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (!ConstStorage.getAfkTimer().containsKey(player)) {
            ConstStorage.getAfkTimer().put(player, System.currentTimeMillis());
            Message.sendMessage(player, "§aDu hast dich selber als §c§lInaktiv §amarkiert§8.");
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
            return;
        }

        Message.sendMessage(player, "§cBewege dich, um dich als §a§lAktiv §czu markieren§8.");

    }
}
