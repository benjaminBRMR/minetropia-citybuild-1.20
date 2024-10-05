package net.citybuild.backend.utility.command;

import com.google.common.collect.Lists;
import lombok.NonNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ExecutableCommand extends Command {

    public ExecutableCommand(final @NonNull String name, final String permission) {
        super(name);
        this.setPermission(permission);
        this.setAliases(Lists.newArrayList());
    }

    public abstract void execute(final @NonNull Player player, final @NonNull String alias, final @NonNull String[] args);

    @Override
    public boolean execute(final @NonNull CommandSender commandSender, final @NonNull String s, final @NonNull String[] strings) {
        if (commandSender instanceof Player player) {
            execute(player, s, strings);
            return true;
        }
        return false;
    }

}

