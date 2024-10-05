package net.citybuild.backend.user;

import eu.koboo.en2do.repository.entity.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    UUID uuid;

    long money;
    long joined;
    long points;
    long bits;
    long votes;
    long votestreak;
    long boosters;
    long ultraBoosters;
    String creatorCode;
    UUID invitor;
    LinkedHashMap<String, Long> cooldowns;
    LinkedHashMap<String, Long> cases;
    LinkedHashMap<String, String> customLocations;
    LinkedHashMap<String, String> perks;
    LinkedHashMap<String, String> begleiter;
    LinkedList<String> messages;
}
