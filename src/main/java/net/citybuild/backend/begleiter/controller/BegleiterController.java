package net.citybuild.backend.begleiter.controller;

import lombok.Getter;
import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.begleiter.Begleiter;
import net.citybuild.backend.utility.Pair;
import net.citybuild.backend.utility.color.Color;
import net.citybuild.backend.utility.creator.ItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class BegleiterController {

    @Getter
    private final HashMap<Player, Pair<ArmorStand, Begleiter>> begleiterHashMap = new HashMap<>();

    public void hook(final @NonNull Player player) {
        final Begleiter begleiter = CityBuild.getUserManager().getToggledBegleiter(player.getUniqueId());
        if (begleiter == null) return;

        Vector playerDirection = player.getLocation().getDirection();
        float yaw = (float) Math.toDegrees(Math.atan2(playerDirection.getX(), playerDirection.getZ()));
        Location armorStandLocation = new Location(player.getWorld(),
                player.getLocation().getX() + 2.0,
                player.getLocation().getY(),
                player.getLocation().getZ());


        final ArmorStand armorStand = player.getWorld().spawn(armorStandLocation, ArmorStand.class);
        armorStand.setRotation(yaw, 0);
        armorStand.setInvulnerable(true);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true); // FIXME: 29.08.2023 nur test @benni
        armorStand.setCustomName(Color.translateColorCodes("&#213c15&l" + begleiter.getDisplayName()) + " §7von §a" + player.getName());
        armorStand.setCustomNameVisible(true);
        armorStand.setHelmet(new ItemCreator(Material.PLAYER_HEAD)
                .setSkullValue(begleiter.getMinecraftURL()));

        begleiterHashMap.put(player, new Pair<>(armorStand, begleiter));

    }

    public void delete(final @NonNull Player player) {
        if (begleiterHashMap.get(player) == null) return;
        final ArmorStand armorStand = begleiterHashMap.get(player).key();
        if (armorStand == null) return;
        armorStand.remove();
        begleiterHashMap.remove(player);
    }

    public void deleteAll() {
        for (Map.Entry<Player, Pair<ArmorStand, Begleiter>> playerPairEntry : begleiterHashMap.entrySet()) {
            playerPairEntry.getValue().key().remove();
            begleiterHashMap.remove(playerPairEntry.getKey());
        }
    }

    public void update(final @NonNull Player player) {
        if (begleiterHashMap.get(player) == null) return;
        final ArmorStand armorStand = begleiterHashMap.get(player).key();
        if (armorStand == null) return;
        if (!player.isOnline() || player.isDead() || player.getHealth() <= 0) {
            delete(player);
            return;
        }

        Vector playerDirection = player.getLocation().getDirection();
        float yaw = (float) Math.toDegrees(Math.atan2(playerDirection.getX(), playerDirection.getZ()));
        Location armorStandLocation = new Location(player.getWorld(),
                player.getLocation().getX() + 2.0,
                player.getLocation().getY(),
                player.getLocation().getZ());

        armorStand.teleport(armorStandLocation);
    }

    public void sync(final @NonNull Player player) {

        if (begleiterHashMap.containsKey(player)) {
            final ArmorStand armorStand = begleiterHashMap.get(player).key();
            armorStand.remove();
        }
        if (CityBuild.getUserManager().getToggledBegleiter(player.getUniqueId()) == null) return;

        final Begleiter begleiter = CityBuild.getUserManager().getToggledBegleiter(player.getUniqueId());
        if (begleiter != null) {
            hook(player);
        }

    }
}
