package net.citybuild.backend.user.creatorcode;

import eu.koboo.en2do.repository.entity.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatorCode {

    @Id
    String code;
    UUID creator;
    long incomingBits;
    long incomingBitsAlltime;
    List<UUID> uses;

}
