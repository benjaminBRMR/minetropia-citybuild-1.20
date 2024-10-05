package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import net.citybuild.backend.utility.permission.Permission;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static net.citybuild.backend.storage.ConstStorage.skulls;

public class CommandFreeSkull extends ExecutableCommand {
    public CommandFreeSkull() {
        super("randomkopf", null);
        setAliases(new ArrayList<String>() {
            {
                add("freeskull");
                add("freekopf");
                add("randomskull");
            }
        });
    }

    private String[] syntax(final @NonNull Player player) {
        return new String[]{
                "§7Befehl §8→ §e/randomkopf",
                "§7Beschreibung §8→ §eBekomme einen zufälligen kostenlosen Kopf vom Alphabet oder Zahlen/Sonderzeichen§8.",
                "§7Verwendung §8→ §e/randomkopf",
        };
    }

    private Map.Entry<String, String> getRandomSkullEntry() {
        if (!skulls.isEmpty()) {
            int randomIndex = Utility.random.nextInt(skulls.size());
            Iterator<Map.Entry<String, String>> iterator = skulls.entrySet().iterator();
            for (int i = 0; i < randomIndex; i++) {
                iterator.next();
            }
            return iterator.next();
        }
        return null;
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, @NonNull String[] args) {
        if(Permission.hasPermission(player, "citybuild.randomskull")) {
            if(args.length == 0) {
                if(CityBuild.getUserManager().hasCooldown(player.getUniqueId(), "randomskull") && !player.hasPermission("citybuild.randomskull.bypass")) {
                    Message.sendMessage(player, "§cDu hast deinen zufälligen Kopf bereits abgeholt§8.");
                    Message.sendMessage(player, "§7Bereit in§8: §c" + Utility.timeToString(CityBuild.getUserManager().getTime(player.getUniqueId(), "randomskull"), true));
                    return;
                }
                if(!player.hasPermission("citybuild.randomskull.bypass")) {
                    CityBuild.getUserManager().setCooldown(player.getUniqueId(), "randomskull", TimeUnit.DAYS.toMillis(7));
                }

                Map.Entry<String, String> randomSkullEntry = getRandomSkullEntry();
                if (randomSkullEntry != null) {
                    String base64Texture = randomSkullEntry.getKey();
                    String skullName = randomSkullEntry.getValue();
                    Utility.addItem(player, new ItemCreator(Material.PLAYER_HEAD)
                            .setSkullValue(base64Texture)
                            .setName(skullName));
                    Message.sendMessage(player, "§aDir wurde erfolgreich folgender Kopf gutgeschrieben:");
                    Message.sendMessage(player, skullName);
                    return;
                }
            }
            Utility.sendSyntax(player, syntax(player));
        }
    }
}
