package net.citybuild.backend.manager;

import lombok.Getter;
import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.utility.Pair;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;

public class CaseManager {

    private final LinkedHashMap<String, Case> caseCache = new LinkedHashMap<>();
    @Getter
    private final LinkedHashMap<Integer, Pair<ItemStack, ItemStack>> showcase = new LinkedHashMap<>();
    @Getter
    private final LinkedHashMap<Pair<Player, String>, Pair<ItemStack, Float>> chanceEdit = new LinkedHashMap<>();


    // FIXME: 06.08.2023
    /*
    
    
            final User user = CityBuild.getUserManager().handle(UUID.fromString(""));
        Location location = new Location(
                Bukkit.getWorld(""),
                0,
                0,
                0,
                0,
                0
        );
        user.getCustomLocations().put("mobileEC_1", net.citybuild.utility.location.Location.locationToString(location));
    
    
     */

    public CaseManager() {
        load();
    }

    public void load() {
        caseCache.clear();

        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getCaseRepository().findAll().forEach(cases -> {
                caseCache.put(cases.getName(), cases);
            });
            return this;
        });

    }

    public void createCase(final @NonNull Case caseA) {
        caseCache.put(caseA.getName(), caseA);
    }

    public void delete(final @NonNull String caseName) {
        caseCache.remove(caseName);
    }

    /**
     * Deletes the mongo-entry
     *
     * @param caseName
     */
    public void deleteEntry(final @NonNull String caseName) {
        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getCaseRepository().delete(CityBuild.getCaseManager().getCase(caseName));
            caseCache.remove(caseName);
            return this;
        });
    }

    public Case getCase(final @NonNull String caseName) {
        return caseCache.get(caseName);
    }

    public LinkedList<Case> getCases() {
        return new LinkedList<>(caseCache.values());
    }


    public boolean exist(final @NonNull String caseName) {
        return caseCache.containsKey(caseName);
    }


    public void save(final @NonNull String caseName) {
        final Case caseA = caseCache.get(caseName);
        CityBuild.getDbManager().getCaseRepository().save(caseA);
    }

    public void saveAll() {
        CompletableFuture.supplyAsync(() -> {
            caseCache.forEach((key, value) -> {
                save(key);
            });
            return this;
        });

    }

}
