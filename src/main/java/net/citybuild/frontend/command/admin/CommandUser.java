package net.citybuild.frontend.command.admin;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.user.User;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class CommandUser extends ExecutableCommand {

    public CommandUser() {
        super("user", "citybuild.user");
    }

    private String[] syntax() {
        return new String[]{
                "§8/§euser setmoney <spieler> <anzahl>",
                "§8/§euser addmoney <spieler> <anzahl>",
                "§8/§euser removemoney <spieler> <anzahl>",
                "§8/§euser setbits <spieler> <anzahl>",
                "§8/§euser addbits <spieler> <anzahl>",
                "§8/§euser removebits <spieler> <anzahl>",
                "§8/§euser setpoints <spieler> <anzahl>",
                "§8/§euser addpoints <spieler> <anzahl>",
                "§8/§euser removepoints <spieler> <anzahl>",

        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {

        if (Permission.hasPermission(player, "citybuild.user")) {


            if (args.length == 3 && args[0].equalsIgnoreCase("setmoney")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast den Kontostand von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alter Kontostand§8: §c" + NumberFormat.getInstance().format(user.getMoney()));
                    Message.sendMessage(player, "§7Neuer Kontostand§8: §a" + NumberFormat.getInstance().format(newAmount));
                    user.setMoney(newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("addmoney")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast den Kontostand von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alter Kontostand§8: §c" + NumberFormat.getInstance().format(user.getMoney()));
                    Message.sendMessage(player, "§7Neuer Kontostand§8: §a" + NumberFormat.getInstance().format((user.getMoney()) + newAmount));
                    user.setMoney((user.getMoney()) + newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("removemoney")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);


                    if (newAmount > user.getMoney()) {
                        Message.sendMessage(player, "§cDer Kontostand von §e" + target.getDisplayName() + " §cist zu niedrig§8!");
                        return;
                    }

                    Message.sendMessage(player, "§7Du hast den Kontostand von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alter Kontostand§8: §c" + NumberFormat.getInstance().format(user.getMoney()));
                    Message.sendMessage(player, "§7Neuer Kontostand§8: §a" + NumberFormat.getInstance().format((user.getMoney()) - newAmount));
                    user.setMoney((user.getMoney()) - newAmount);
                    Utility.playSuccess(player);


                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            //

            if (args.length == 3 && args[0].equalsIgnoreCase("setbits")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast die Bits von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alte Bitsanzahl§8: §c" + NumberFormat.getInstance().format(user.getBits()));
                    Message.sendMessage(player, "§7Neue Bitsanzahl§8: §a" + NumberFormat.getInstance().format(newAmount));
                    user.setBits(newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("addbits")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast die Bits von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alte Bitsanzahl§8: §c" + NumberFormat.getInstance().format(user.getBits()));
                    Message.sendMessage(player, "§7Neue Bitsanzahl§8: §a" + NumberFormat.getInstance().format((user.getBits()) + newAmount));
                    user.setBits((user.getBits()) + newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("removebits")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);


                    if (newAmount > user.getBits()) {
                        Message.sendMessage(player, "§cDie Bits von §e" + target.getDisplayName() + " §cist zu niedrig§8!");
                        return;
                    }

                    Message.sendMessage(player, "§7Du hast die Bits von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alte Bitsanzahl§8: §c" + NumberFormat.getInstance().format(user.getBits()));
                    Message.sendMessage(player, "§7Neue Bitsanzahl§8: §a" + NumberFormat.getInstance().format((user.getBits()) - newAmount));
                    user.setBits((user.getBits()) - newAmount);
                    Utility.playSuccess(player);


                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            //

            if (args.length == 3 && args[0].equalsIgnoreCase("setpoints")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast die Punkte von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alte Punkte§8: §c" + NumberFormat.getInstance().format(user.getPoints()));
                    Message.sendMessage(player, "§7Neue Punkte§8: §a" + NumberFormat.getInstance().format(newAmount));
                    user.setPoints(newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("addpoints")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);

                    Message.sendMessage(player, "§7Du hast die Punkte von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alter Kontostand§8: §c" + NumberFormat.getInstance().format(user.getPoints()));
                    Message.sendMessage(player, "§7Neuer Kontostand§8: §a" + NumberFormat.getInstance().format((user.getPoints()) + newAmount));
                    user.setMoney((user.getPoints()) + newAmount);
                    Utility.playSuccess(player);

                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            if (args.length == 3 && args[0].equalsIgnoreCase("removepoints")) {

                final Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    Message.sendMessage(player, ConstStorage.getOFFLINE());
                    return;
                }

                final User user = CityBuild.getUserManager().handle(target.getUniqueId());

                try {
                    final long newAmount = Long.parseLong(args[2]);


                    if (newAmount > user.getPoints()) {
                        Message.sendMessage(player, "§cDie Punkte von §e" + target.getDisplayName() + " §csind zu niedrig§8!");
                        return;
                    }

                    Message.sendMessage(player, "§7Du hast die Punkte von §e" + target.getDisplayName() + " §7verändert§8!");
                    Message.sendMessage(player, "§7Alter Kontostand§8: §c" + NumberFormat.getInstance().format(user.getPoints()));
                    Message.sendMessage(player, "§7Neuer Kontostand§8: §a" + NumberFormat.getInstance().format((user.getPoints()) - newAmount));
                    user.setMoney((user.getPoints()) - newAmount);
                    Utility.playSuccess(player);


                } catch (Exception exception) {
                    Utility.playError(player);
                    Message.sendMessage(player, "§cBitte gebe eine gültige Anzahl an§8!");
                }


                return;
            }

            Utility.sendSyntax(player, syntax());
        }
    }
}
