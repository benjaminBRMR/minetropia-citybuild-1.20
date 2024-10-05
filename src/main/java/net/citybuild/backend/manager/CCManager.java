package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.user.creatorcode.CreatorCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CCManager {

    private final LinkedHashMap<String, CreatorCode> creatorCodeCache = new LinkedHashMap<>();

    public CCManager() {
        load();
    }

    public void load() {
        creatorCodeCache.clear();

        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getCreatorCodeRepository().findAll().forEach(creatorCode -> {
                creatorCodeCache.put(creatorCode.getCode(), creatorCode);
            });
            return this;
        });
    }

    public void save(final @NonNull String name) {
        final CreatorCode creatorCode = creatorCodeCache.get(name);
        CityBuild.getDbManager().getCreatorCodeRepository().save(creatorCode);
    }

    public void saveAll() {
        CompletableFuture.supplyAsync(() -> {
            creatorCodeCache.forEach((key, value) -> save(key));
            return this;
        });
    }

    public boolean hasCC(final @NonNull UUID uuid) {
        for (CreatorCode creatorCode : getCreatorCodes()) {
            if (creatorCode.getCreator().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCC(final @NonNull UUID uuid, final @NonNull CreatorCode creatorCode) {
        return creatorCode.getCreator().equals(uuid);
    }

    public long getCreatorCodeUses(final @NonNull CreatorCode creatorCode) {
        if (creatorCode.getUses() == null) return 0L;
        return creatorCode.getUses().size();
    }


    public UUID getCreator(final @NonNull String creatorCode) {
        return creatorCodeCache.get(creatorCode).getCreator();
    }

    /**
     * @return null if the uuid has not cc
     */
    public CreatorCode getCreatorCodeByUUID(final @NonNull UUID uuid) {
        for (CreatorCode creatorCode : getCreatorCodes()) {
            if (creatorCode.getCreator().equals(uuid)) return creatorCode;
        }
        return null;
    }

    public ArrayList<CreatorCode> getCreatorCodes() {
        return new ArrayList<>(creatorCodeCache.values());
    }

    public void createCC(final @NonNull CreatorCode creatorCode) {
        creatorCodeCache.put(creatorCode.getCode(), creatorCode);
    }

    public boolean existCC(final @NonNull String name) {
        return creatorCodeCache.containsKey(name);
    }

    public void deleteCC(final @NonNull String name) {
        creatorCodeCache.remove(name);
    }

    public CreatorCode getCreatorCode(final @NonNull String name) {
        return creatorCodeCache.get(name);
    }

    /**
     * Deletes the mongo-entry
     *
     * @param name
     */
    public void deleteEntry(final @NonNull String name) {
        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getCreatorCodeRepository().delete(getCreatorCode(name));
            creatorCodeCache.remove(name);
            return this;
        });
    }

}
