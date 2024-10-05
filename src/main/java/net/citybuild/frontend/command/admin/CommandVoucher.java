package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.permission.Permission;
import net.citybuild.frontend.inventory.voucher.InventoryVoucher;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandVoucher extends ExecutableCommand {

    public CommandVoucher() {
        super("voucher", "citybuild.voucher");

        setAliases(new ArrayList<>() {{
            add("gutschein");
            add("gutscheine");
        }});
    }


    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.voucher")) {
            final InventoryVoucher inventoryVoucher = new InventoryVoucher(player);
            ConstStorage.getVoucher().put(player, inventoryVoucher);
            inventoryVoucher.update(0, player);
            return;
        }

    }
}
