package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.frontend.inventory.invite.InventoryAffiliateShop;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandAffiliate extends ExecutableCommand {
    public CommandAffiliate() {
        super("invite", null);

        setAliases(new ArrayList<>() {{
            add("einladen");
            add("affiliate");
        }});
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/invite",
                "§7Beschreibung §8→ §eLade Spieler ein und bekomme viele tolle Belohnungen§8.",
                "§7Verwendung §8→ §e/invite <check, spieler, shop> ",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("shop")) {
            player.openInventory(InventoryAffiliateShop.getAffiliateShopInventory(player));
            return;
        }

        if (args.length == 1) {

            final String invitor = args[0];

            if (!Bukkit.getOfflinePlayer(invitor).hasPlayedBefore()) {
                Message.sendMessage(player, ConstStorage.getOFFLINE());
                return;
            }

            final OfflinePlayer target = Bukkit.getOfflinePlayer(invitor);

            if (CityBuild.getInviteManager().hasInvite(player.getUniqueId())) {
                Message.sendMessage(player, "§cDu hast bereits einen Spieler angegeben vom Einladungssystem§8.");
                return;
            }

            CityBuild.getInviteManager().setInvite(player.getUniqueId(), target.getUniqueId());
            Message.sendMessage(player, "§b" + target.getName() + " §7erhält jetzt seine Belohnung§8.");
            CityBuild.getInviteManager().getInvite(player.getUniqueId()).setPoints((CityBuild.getInviteManager().getInvite(player.getUniqueId()).getPoints() + 2));
            return;
        }

        Utility.sendSyntax(player, syntax(player));
        return;
    }
}
