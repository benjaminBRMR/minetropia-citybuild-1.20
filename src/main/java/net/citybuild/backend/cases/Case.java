package net.citybuild.backend.cases;

import eu.koboo.en2do.repository.entity.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.citybuild.backend.utility.color.Color;

import java.util.LinkedHashMap;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Case {

    @Id
    String name;

    String displayName;
    long opened;
    boolean enabled;
    UUID lastOpened;
    LinkedHashMap<String, Float> caseItems;

    public String getDisplayName() {
        return Color.translateColorCodes(displayName) + "§r";
    }
}
