package net.citybuild.frontend.command.player;

import lombok.NonNull;
import net.citybuild.backend.storage.ConstStorage;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import net.citybuild.frontend.inventory.vote.InventoryVote;
import org.bukkit.entity.Player;

public class CommandVote extends ExecutableCommand {
    public CommandVote() {
        super("vote", null);
    }

    private String[] syntax() {
        return new String[]{ // überarbeiten
                "§7Befehl §8→ §e/vote",
                "§7Beschreibung §8→ §eVote für den Server und bekomme viele tolle Belohnungen. Vergiss nicht, dass wir ein ausführliches und besonderes Votesystem haben!",
                "§7Verwendung §8→ §e/vote §8- §7Öffnet ein Inventar von unserem Votesystem mit Votelinks etc.",
        };
    }

    @Override
    public void execute(@NonNull Player player, @NonNull String alias, String[] args) {

        if (args.length == 0) {
            player.sendMessage(ConstStorage.getPREFIX() + "§7Link zur Vote-Seite§8: §emonkeangry.com");
            player.openInventory(InventoryVote.getVoteInventory(player));
            return;
        }

        Utility.sendSyntax(player, syntax());

    }

}
