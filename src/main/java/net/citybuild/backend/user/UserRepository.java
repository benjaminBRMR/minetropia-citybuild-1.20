package net.citybuild.backend.user;

import eu.koboo.en2do.repository.Collection;
import eu.koboo.en2do.repository.Repository;

import java.util.UUID;

@Collection("user")
public interface UserRepository extends Repository<User, UUID> {
}
