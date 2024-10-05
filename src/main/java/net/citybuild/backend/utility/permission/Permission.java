package net.citybuild.backend.utility.permission;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.message.Message;
import org.bukkit.entity.Player;

@UtilityClass
public class Permission {

    public boolean hasPermission(final @NonNull Player player, final @NonNull String permission) {
        if (!player.hasPermission(permission)) {
            Message.sendMessage(player, ConstStorage.getNOPERM());
            return false;
        }
        return true;
    }

}
