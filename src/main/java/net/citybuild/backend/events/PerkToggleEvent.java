package net.citybuild.backend.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import net.citybuild.backend.perk.Perk;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
@AllArgsConstructor
public class PerkToggleEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    Player player;
    Perk perk;
    boolean state;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NonNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
