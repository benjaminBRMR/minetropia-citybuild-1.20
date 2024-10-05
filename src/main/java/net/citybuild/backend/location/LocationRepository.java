package net.citybuild.backend.location;

import eu.koboo.en2do.repository.Collection;
import eu.koboo.en2do.repository.Repository;

@Collection("location")
public interface LocationRepository extends Repository<Location, String> {
}
