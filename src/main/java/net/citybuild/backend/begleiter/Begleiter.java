package net.citybuild.backend.begleiter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.EnumSet;

@AllArgsConstructor
public enum Begleiter {

    HORSE("Pferd", "2990821951fd9ab9301aebf5d76a9b60f15ebf619e1c9e8d668919d394d9a933"),
    CHICKEN("Huhn", "4d8fefad255d6e5c9f8245c516f0d9bbff4f0552db8b1afc3dcf7d1dd5be23fd"),
    CAT("Katze", "aa3a8adcfdb53e93f0ca1b068d759d8c651578f737dc65f2269dd33968c3cf95"),
    SHEEP("Schaf", "292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4"),
    BEE("Biene", "d0299a2aae9a605b5dbd1945fc4368ccee88ae06e47dc90f953131e0d903b322"),
    SNIFFER("Schnüffler", "fe5a8341c478a134302981e6a7758ea4ecfd8d62a0df4067897e75502f9b25de"),
    AXOLOTL("Axolotl", "6ebb4d93feb49d904c61a8fa0eaeac41fe34442cafea7801038ebf7c381c8397"),
    RACOON("Waschbär", "68ac5f49b09088b55d8c50bdd2af1f9a7d622d2ad0bcbad81739f73df475527f"),
    FROG("Frosch", "45852a95928897746012988fbd5dbaa1b70b7a5fb65157016f4ff3f245374c08"),
    CAMEL("Kamel", "ba4c95bfa0b61722255389141b505cf1a38bad9b0ef543de619f0cc9221ed974"),
    BABY_SHEEP("Baby Schaf", "358f51cff26a387b551d0f52ac277cb7ac617a3c220f3fa1c3d3a48190dc6be2");

    @Getter
    final String displayName;
    @Getter
    final String minecraftURL;

    public static Begleiter getBegleiterByDisplayName(final @NonNull String displayName) {
        for (Begleiter begleiter : EnumSet.allOf(Begleiter.class)) {
            if (begleiter.getDisplayName().equals(displayName)) return begleiter;
        }
        return null;
    }

    public static Begleiter getBegleiterByMinecraftURL(final @NonNull String minecraftURL) {
        for (Begleiter begleiter : EnumSet.allOf(Begleiter.class)) {
            if (begleiter.getMinecraftURL().equals(minecraftURL)) return begleiter;
        }
        return null;
    }

}
