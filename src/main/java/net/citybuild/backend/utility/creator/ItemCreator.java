package net.citybuild.backend.utility.creator;

import lombok.NonNull;
import net.citybuild.backend.utility.color.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import javax.annotation.Nonnegative;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ItemCreator extends ItemStack {

    public ItemCreator(final @NonNull ItemStack itemStack) {
        super(itemStack);
    }

    public ItemCreator(final @NonNull Material material) {
        super(material);
    }

    public ItemCreator setLore(final @NonNull String string) {
        final ItemMeta itemMeta = this.getItemMeta();
        final List<String> lore = new ArrayList<>();
        lore.add(Color.translateColorCodes(string));
        assert itemMeta != null;
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setLore(final @NonNull String... list) {
        final ItemMeta itemMeta = this.getItemMeta();
        final List<String> lore = new ArrayList<>();
        for (String s : list) {
            lore.add(Color.translateColorCodes(s));
        }
        assert itemMeta != null;
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
        return this;
    }


    public ItemCreator setLoreAsArray(final @NonNull List<String> list) {
        final ItemMeta itemMeta = this.getItemMeta();
        final List<String> lore = new ArrayList<>();
        for (String s : list) {
            lore.add(Color.translateColorCodes(s));
        }
        assert itemMeta != null;
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator addToLore(final @NonNull String... lore) {
        final ItemMeta meta = this.getItemMeta();
        final List<String> loreList = (List<String>) meta.getLore();
        Collections.addAll(loreList, lore);
        meta.setLore((List) loreList);
        this.setItemMeta(meta);
        return this;
    }


    public ItemCreator setSkullValue(final @NonNull String minecraftURL) {
        final ItemMeta itemMeta = this.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        assert itemMeta != null;
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures playerTextures = playerProfile.getTextures();

        try {
            playerTextures.setSkin(new URL("http://textures.minecraft.net/texture/" + minecraftURL));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        skullMeta.setOwnerProfile(playerProfile);
        this.setItemMeta(skullMeta);
        return this;
    }

    public ItemCreator setSkullPlayer(final @NonNull String playerName) {
        final ItemMeta itemMeta = this.getItemMeta();
        SkullMeta skullMeta = (SkullMeta) itemMeta;
        assert itemMeta != null;
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(playerName);
        skullMeta.setOwnerProfile(playerProfile);
        this.setItemMeta(skullMeta);
        return this;
    }

    public String getName() {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        return itemMeta.getDisplayName();
    }

    public ItemCreator setName(final @NonNull String name) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(Color.translateColorCodes(name));
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator amount(final @Nonnegative long amount) {
        this.setAmount((int) amount);
        return this;
    }

    public ItemCreator glow(final boolean glowing) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        if (glowing) {
            itemMeta.addEnchant(Enchantment.LUCK, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            itemMeta.removeEnchant(Enchantment.LUCK);
            itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator addItemFlag(final @NonNull ItemFlag itemFlag) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.addItemFlags(itemFlag);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator removeItemFlag(final @NonNull ItemFlag itemFlag) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.removeItemFlags(itemFlag);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator addEnchant(final @NonNull Enchantment enchantment, final @Nonnegative long level) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.addEnchant(enchantment, (int) level, true);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator removeEnchant(final @NonNull Enchantment enchantment) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.removeEnchant(enchantment);
        this.setItemMeta(itemMeta);
        return this;
    }

    public ItemCreator setUnbreakable(final boolean unbreakable) {
        final ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.setUnbreakable(unbreakable);
        this.setItemMeta(itemMeta);
        return this;
    }

}
