package net.citybuild.backend.manager;

import net.citybuild.backend.gutschein.Gutschein;
import net.citybuild.backend.storage.VoucherStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VoucherManager {

    Map<String, Gutschein> gutscheinMap = new ConcurrentHashMap<>();

    public VoucherManager() {
        for (Gutschein gutschein : VoucherStorage.getVoucher()) {
            gutscheinMap.put(gutschein.getItem().getItemMeta().getDisplayName(), gutschein);
        }
    }

    public Gutschein getGutscheinByName(String name) {
        return gutscheinMap.get(name);
    }

    public Gutschein getGutscheinByDisplayName(String name) {
        return gutscheinMap.values().stream().filter(gutschein -> gutschein.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
