package net.citybuild.backend.listener;

import net.citybuild.CityBuild;
import net.citybuild.backend.booster.Booster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ListenBooster {

    private final String[] forbiddenWorlds = new String[]{"MineTropiaPlot"};

    @EventHandler
    public void onLevelChange(final PlayerExpChangeEvent event) {

        System.out.println("yessss");

        if (CityBuild.getBoosterManager().isRunning(Booster.XP)) {

            event.setAmount((int) (event.getAmount() * CityBuild.getBoosterManager().getMultiplier(Booster.XP)));
        }
    }


    /*
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        if (CityBuild.getBoosterManager().isRunning(Booster.BREAK)) {

            final Booster booster = Booster.BREAK;
            final Player player = event.getPlayer();
            final long multiplier = CityBuild.getBoosterManager().getMultiplier(booster);


            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            if (event.getPlayer().getInventory().getItemInHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                return;
            }
            boolean DropItem = false;
            final String name = event.getBlock().getType().name();
            switch (name) {
                case "GOLD_ORE", "IRON_ORE", "COAL_ORE", "DEEPSLATE_COAL_ORE", "NETHER_GOLD_ORE", "DIAMOND_ORE", "DEEPSLATE_DIAMOND_ORE", "EMERALD_ORE", "DEEPSLATE_EMERALD_ORE", "NETHER_QUARTZ_ORE", "DEEPSLATE_IRON_ORE", "COPPER_ORE", "DEEPSLATE_COPPER_ORE", "DEEPSLATE_GOLD_ORE", "DEEPSLATE_LAPIS_ORE", "DEEPSLATE_REDSTONE_ORE", "REDSTONE_ORE", "LEGACY_GLOWING_REDSTONE_ORE", "GLOWING_REDSTONE_ORE", "LAPIS_ORE" -> {
                    DropItem = true;
                }
            }
            if (DropItem) {
                Collection<ItemStack> drops;
                drops = event.getBlock().getDrops();

                for (final ItemStack item : drops) {
                    for (int o = 0; o < multiplier; ++o) {
                        Objects.requireNonNull(event.getBlock().getLocation().getWorld()).dropItem(event.getBlock().getLocation(), item);
                    }
                }
                event.getBlock().setType(Material.AIR);
                ((ExperienceOrb) event.getBlock().getWorld().spawn(event.getBlock().getLocation(), (Class) ExperienceOrb.class)).setExperience(event.getExpToDrop());
                event.setCancelled(true);
            }
        }
    }

     */

    /*
        @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        final Material type = e.getItem().getType();
        if (!type.name().equals("EXP_BOTTLE") && !type.name().equals("EXPERIENCE_BOTTLE")) {
            return;
        }
        final Player player = e.getPlayer();
        if (Main.getBooster(player, Main.XP).size() != 0 && Config.getDisableXPBottles()) {
            Util.sendMessage(e.getPlayer(), Messages.getMessage("Booster.XP.NoXPBottle", player));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDispenser(final BlockDispenseEvent e) {
        final Material type = e.getItem().getType();
        if (!type.name().equals("EXP_BOTTLE") && !type.name().equals("EXPERIENCE_BOTTLE")) {
            return;
        }
        if (!Config.getDisableXPBottles()) {
            return;
        }
        if (Main.XP.isGlobal() && Main.getBooster((String)null, Main.XP).size() == 0) {
            return;
        }
        e.setCancelled(true);
    }
     */
}
