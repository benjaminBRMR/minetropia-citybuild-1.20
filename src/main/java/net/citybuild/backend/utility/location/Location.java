package net.citybuild.backend.utility.location;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.World;

@UtilityClass
public class Location {


    public String locationToString(final @NonNull org.bukkit.Location location) {
        final World world = location.getWorld();
        final double x = location.getX();
        final double y = location.getY();
        final double z = location.getZ();
        final float yaw = location.getYaw();
        final float pitch = location.getPitch();

        return world.getName() + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
    }

    public org.bukkit.Location locationFromString(final @NonNull String locationName) {
        final String[] parts = locationName.split(";");
        if (parts.length != 6) {
            return null;
        }

        final String worldName = parts[0];
        final double x = Double.parseDouble(parts[1]);
        final double y = Double.parseDouble(parts[2]);
        final double z = Double.parseDouble(parts[3]);
        final float yaw = Float.parseFloat(parts[4]);
        final float pitch = Float.parseFloat(parts[5]);

        final World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }

        return new org.bukkit.Location(world, x, y, z, yaw, pitch);
    }
}
