package net.citybuild.backend.invite;

import eu.koboo.en2do.repository.Collection;
import eu.koboo.en2do.repository.Repository;

import java.util.UUID;

@Collection("invite")
public interface InviteRepository extends Repository<Invite, UUID> {
}
