package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import org.bukkit.Location;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public class LocationManager {

    private final LinkedHashMap<String, net.citybuild.backend.location.Location> locationCache = new LinkedHashMap<>();

    public LocationManager() {
        load();
    }

    public void load() {
        locationCache.clear();

        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getLocationRepository().findAll().forEach(locations -> {
                locationCache.put(locations.getName(), new net.citybuild.backend.location.Location(locations.getName(), locations.getLocationString()));
            });
            return this;
        });
    }

    /**
     * Deletes the mongo-entry
     *
     * @param name
     */
    public void deleteEntry(final @NonNull String name) {
        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getLocationRepository().delete(getLocationEntry(name));
            locationCache.remove(name);
            return this;
        });
    }

    public LinkedList<net.citybuild.backend.location.Location> getLocations() {
        return new LinkedList<>(locationCache.values());
    }

    public void createLocation(final @NonNull String name, final @NonNull Location location) {
        locationCache.put(name, new net.citybuild.backend.location.Location(name, net.citybuild.backend.utility.location.Location.locationToString(location)));
    }

    public boolean existLocation(final @NonNull String name) {
        return locationCache.containsKey(name);
    }

    public void deleteLocation(final @NonNull String name) {
        locationCache.remove(name);
    }

    public Location getLocation(final @NonNull String name) {
        final net.citybuild.backend.location.Location location = locationCache.get(name);
        return net.citybuild.backend.utility.location.Location.locationFromString(location.getLocationString());
    }

    public net.citybuild.backend.location.Location getLocationEntry(final @NonNull String name) {
        return locationCache.get(name);
    }

    public void save(final @NonNull String name) {
        final net.citybuild.backend.location.Location location = locationCache.get(name);
        CityBuild.getDbManager().getLocationRepository().save(location);

    }

    public void saveAll() {
        CompletableFuture.supplyAsync(() -> {
            locationCache.forEach((key, value) -> {
                save(key);
            });
            return this;
        });
    }

}
