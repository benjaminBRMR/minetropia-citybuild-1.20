package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.begleiter.Begleiter;
import net.citybuild.backend.cases.Case;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nonnegative;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class UserManager {

    private final LinkedHashMap<UUID, User> userCache = new LinkedHashMap<>();

    public CompletableFuture<Boolean> load(final @NonNull UUID uuid) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        if (!exist(uuid)) {
            userCache.put(uuid, new User(
                    uuid,
                    250,
                    System.currentTimeMillis(),
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    null,
                    null,
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedList<>()
            ));
            future.complete(false);
        } else {

            CompletableFuture.supplyAsync(() -> {
                userCache.put(uuid, CityBuild.getDbManager().getUserRepository().findFirstById(uuid));
                return this;
            }).thenAccept(result -> future.complete(true));
        }

        return future;
    }

    public LinkedList<User> getUsers() {
        return new LinkedList<>(userCache.values());
    }


    public void save(final @NonNull UUID uuid) {

        CompletableFuture.supplyAsync(() -> {
            final User user = userCache.get(uuid);
            CityBuild.getDbManager().getUserRepository().save(user);
            return userCache.containsKey(uuid);
        }).thenAccept(i -> {
            if (i) {
                userCache.remove(uuid);
            }
        });


    }

    public User handle(final @NonNull UUID uuid) {
        return userCache.get(uuid);
    }

    public void setCooldown(final @NonNull UUID uuid, final @NonNull String name, final @Nonnegative long time) {
        final long endTime = (System.currentTimeMillis() + time);
        final User user = handle(uuid);
        user.getCooldowns().put(name, endTime);
    }

    public long getTime(final @NonNull UUID uuid, final @NonNull String name) {
        final User user = handle(uuid);
        return (user.getCooldowns().getOrDefault(name, 0L) - System.currentTimeMillis());
    }

    private long getRemaingTime(final @NonNull UUID uuid, final @NonNull String name) {
        final User user = handle(uuid);
        return user.getCooldowns().getOrDefault(name, 0L);
    }

    public boolean hasCooldown(final @NonNull UUID uuid, final @NonNull String name) {
        final long endTime = (getRemaingTime(uuid, name) - System.currentTimeMillis());
        if (endTime <= 0L) {
            deleteCooldown(uuid, name);
        }
        return endTime > 0L;
    }

    public void deleteCooldown(final @NonNull UUID uuid, final @NonNull String name) {
        final User user = handle(uuid);
        user.getCooldowns().remove(name);
    }

    public boolean hasCase(final @NonNull UUID uuid, final @NonNull Case caseA) {
        final User user = handle(uuid);
        return user.getCases().getOrDefault(caseA.getName(), 0L) > 0;
    }

    public long getCase(final @NonNull UUID uuid, final @NonNull Case caseA) {
        final User user = handle(uuid);
        return user.getCases().getOrDefault(caseA.getName(), 0L);
    }

    public void addCase(final @NonNull UUID uuid, final @NonNull Case caseA, final @Nonnegative long amount) {
        final User user = handle(uuid);
        final long current = user.getCases().getOrDefault(caseA.getName(), 0L);
        user.getCases().put(caseA.getName(), (current + amount));
    }

    public void removeCase(final @NonNull UUID uuid, final @NonNull Case caseA) {
        final User user = handle(uuid);
        final long current = user.getCases().getOrDefault(caseA.getName(), 0L);
        if (current > 0) {
            user.getCases().put(caseA.getName(), (current - 1));
        }
    }

    public void addMessage(final @NonNull UUID uuid, final @NonNull String message) {
        final User user = handle(uuid);
        user.getMessages().add(message);
    }

    public boolean hasPerk(final @NonNull UUID uuid, final @NonNull Perk perk) {
        final User user = handle(uuid);
        if (user.getPerks().get(perk.name()) == null) return false;

        final String input = user.getPerks().get(perk.name());
        final String[] inputStrings = input.split(";");
        return Boolean.parseBoolean(inputStrings[0]);
    }

    public boolean hasPerkToggled(final @NonNull UUID uuid, final @NonNull Perk perk) {
        final User user = handle(uuid);
        if (user.getPerks().get(perk.name()) == null) return false;
        final String input = user.getPerks().get(perk.name());
        final String[] inputStrings = input.split(";");
        return Boolean.parseBoolean(inputStrings[1]);
    }

    public void givePerk(final @NonNull UUID uuid, final @NonNull Perk perk) {
        final User user = handle(uuid);
        if (hasPerk(uuid, perk)) return;
        user.getPerks().put(perk.name(), "true;false");
    }

    public void removePerk(final @NonNull UUID uuid, final @NonNull Perk perk) {
        final User user = handle(uuid);
        if (!hasPerk(uuid, perk)) return;
        user.getPerks().remove(perk.name());
    }

    public void setPerkToggled(final @NonNull UUID uuid, final @NonNull Perk perk, final boolean toggled) {
        final User user = handle(uuid);
        if (!hasPerk(uuid, perk)) return;
        final String input = user.getPerks().get(perk.name());
        final String[] inputStrings = input.split(";");
        user.getPerks().put(perk.name(), inputStrings[0] + ";" + toggled);
    }


    //*

    public boolean hasBegleiter(final @NonNull UUID uuid, final @NonNull Begleiter begleiter) {
        final User user = handle(uuid);
        if (user.getBegleiter().get(begleiter.name()) == null) return false;
        final String input = user.getBegleiter().get(begleiter.name());
        final String[] inputStrings = input.split(";");
        return Boolean.parseBoolean(inputStrings[0]);
    }

    public boolean hasBegleiterToggled(final @NonNull UUID uuid, final @NonNull Begleiter begleiter) {
        final User user = handle(uuid);
        if (user.getBegleiter().get(begleiter.name()) == null) return false;
        final String input = user.getBegleiter().get(begleiter.name());
        final String[] inputStrings = input.split(";");
        return Boolean.parseBoolean(inputStrings[1]);
    }

    public void giveBegleiter(final @NonNull UUID uuid, final @NonNull Begleiter begleiter) {
        final User user = handle(uuid);
        if (hasBegleiter(uuid, begleiter)) return;
        user.getBegleiter().put(begleiter.name(), "true;false");
    }

    public void removeBegleiter(final @NonNull UUID uuid, final @NonNull Begleiter begleiter) {
        final User user = handle(uuid);
        if (!hasBegleiter(uuid, begleiter)) return;
        user.getBegleiter().remove(begleiter.name());
    }

    public void setBegleiterToggled(final @NonNull UUID uuid, final @NonNull Begleiter begleiter, final boolean toggled) {
        final User user = handle(uuid);
        if (!hasBegleiter(uuid, begleiter)) return;
        final String input = user.getBegleiter().get(begleiter.name());
        final String[] inputStrings = input.split(";");
        // FIXME: 21.08.2023 Vielleicht abfrage anders machen(funktioniert aktuell auch so) damit man nicht mehr als einen begleiter aufeinmal auswählen kann.
        if (!getBegleiters(uuid).isEmpty()) {
            for (Begleiter all : getBegleiters(uuid)) {
                if (all == begleiter) continue;
                user.getBegleiter().put(all.name(), inputStrings[0] + ";false");
            }
        }
        user.getBegleiter().put(begleiter.name(), inputStrings[0] + ";" + toggled);
    }

    public Begleiter getToggledBegleiter(final @NonNull UUID uuid) {
        final User user = handle(uuid);


        Optional<String> begleiterOptional = user.getBegleiter().entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), "true;true"))
                .map(Map.Entry::getKey)
                .findAny();

        if (begleiterOptional.isPresent()) {
            String begleiter = begleiterOptional.get();
            return Begleiter.valueOf(begleiter);
        } else {
            return null;
        }
    }


    public List<Begleiter> getBegleiters(final @NonNull UUID uuid) {
        final List<Begleiter> begleiter = new ArrayList<>();
        final User user = handle(uuid);
        user.getBegleiter().forEach((key, value) -> {
            begleiter.add(Begleiter.valueOf(key));
        });
        return begleiter;
    }

    public List<Player> getPlayersWithPerkOn(final @NonNull Perk perk) {
        final List<Player> users = new ArrayList<>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!isInCache(onlinePlayer.getUniqueId())) continue;
            if (!hasPerkToggled(onlinePlayer.getUniqueId(), perk)) continue;
            users.add(onlinePlayer);
        }
        return users;
    }


    private boolean exist(final @NonNull UUID uuid) {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> CityBuild.getDbManager().getUserRepository().existsById(uuid));
        return future.join();
    }

    private boolean isInCache(final @NonNull UUID uuid) {
        return userCache.containsKey(uuid);
    }


    public void saveAll() {
        userCache.forEach((key, value) -> save(key));
        //Bukkit.getOnlinePlayers().forEach(all -> save(all.getUniqueId()));
    }


}
