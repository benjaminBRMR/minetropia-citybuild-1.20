package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.invite.Invite;
import net.citybuild.backend.user.User;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class InviteManager {

    private final LinkedHashMap<UUID, Invite> inviteCache = new LinkedHashMap<>();

    public InviteManager() {
        load();
    }

    public void load() {
        inviteCache.clear();

        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getInviteRepository().findAll().forEach(invite -> {
                inviteCache.put(invite.getInvitor(), invite);
            });
            return this;
        });
    }

    public void save(final @NonNull UUID uuid) {
        final Invite invite = inviteCache.get(uuid);
        CityBuild.getDbManager().getInviteRepository().save(invite);
    }

    public void saveAll() {
        CompletableFuture.supplyAsync(() -> {
            inviteCache.forEach((key, value) -> save(key));
            return this;
        });
    }

    public boolean hasInvite(final @NonNull UUID uuid) {
        final User user = CityBuild.getUserManager().handle(uuid);
        return user.getInvitor() != null;
    }

    public void setInvite(final @NonNull UUID uuid, final @NonNull UUID invitorUUID) {
        final User user = CityBuild.getUserManager().handle(uuid);
        user.setInvitor(invitorUUID);
    }

    public Invite getInviteByInvitor(final @NonNull UUID invitorUUID) {
        CompletableFuture.supplyAsync(() -> {
            CityBuild.getDbManager().getInviteRepository().findFirstById(invitorUUID);
            return this;
        });
        return null;
    }

    public @Nullable Invite getInvite(final @NonNull UUID uuid) {
        final User user = CityBuild.getUserManager().handle(uuid);
        return getInviteByInvitor(user.getInvitor());
    }

}
