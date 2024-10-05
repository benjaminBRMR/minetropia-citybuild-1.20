package net.citybuild.backend.utility;

import com.plotsquared.core.plot.Plot;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.citybuild.CityBuild;
import net.citybuild.backend.perk.Perk;
import net.citybuild.backend.utility.creator.ItemCreator;
import net.citybuild.backend.utility.message.Message;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utility {

    private final Pattern HEX_PATTERN = Pattern.compile("#[A-Fa-f0-9]{6}");
    public final Random random = new Random();

    public void giveRandomPerkWithCaseOpening(Player player) {
        Perk[] perkList = Perk.values();
        Perk randomPerk;
        boolean alreadyOwnAllPerks = Arrays.stream(perkList)
                .allMatch(perk -> CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk));
        if (alreadyOwnAllPerks) {
            Message.sendMessage(player, "§cDu besitzt bereits alle verfügbaren Perks.");
            return;
        }
        do {
            int randomIndex = new Random().nextInt(perkList.length);
            randomPerk = perkList[randomIndex];
        } while (CityBuild.getUserManager().hasPerk(player.getUniqueId(), randomPerk));
        CityBuild.getUserManager().givePerk(player.getUniqueId(), randomPerk);
        Utility.removeHand(player);
        Message.sendMessage(player, "§aDu hast erfolgreich deinen Gutschein benutzt");
        Message.sendMessage(player, "§aund folgendes Perk erhalten: §e§l" + randomPerk.getDisplayName());
    }

    public void giveRandomPerkWithoutCaseOpening(Player player) {
        Perk[] perkList = Arrays.stream(Perk.values())
                .filter(perk -> perk.getPrice() != -1)
                .toArray(Perk[]::new);
        if (perkList.length > 0) {
            Perk randomPerk;
            boolean alreadyOwnAllPerks = Arrays.stream(perkList)
                    .allMatch(perk -> CityBuild.getUserManager().hasPerk(player.getUniqueId(), perk));
            if (alreadyOwnAllPerks) {
                Message.sendMessage(player, "§cDu besitzt bereits alle Perks, welche nicht aus dem Case-Opening sind.");
                Utility.playError(player);
                return;
            }
            do {
                int randomIndex = new Random().nextInt(perkList.length);
                randomPerk = perkList[randomIndex];
            } while (CityBuild.getUserManager().hasPerk(player.getUniqueId(), randomPerk));
            CityBuild.getUserManager().givePerk(player.getUniqueId(), randomPerk);
            Utility.removeHand(player);
            Message.sendMessage(player, "§aDu hast erfolgreich deinen Gutschein benutzt");
            Message.sendMessage(player, "§aund folgendes Perk erhalten: §e§l" + randomPerk.getDisplayName());
        } else {
            Message.sendMessage(player, "§cDu besitzt bereits alle Perks, welche nicht aus dem Case-Opening sind.");
            Utility.playError(player);
        }
    }

    public boolean hasEmptyInventory(final @NonNull Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null) return false;
        }
        return true;
    }

    public String direction(float yaw) {
        yaw /= 90.0F;
        int i = Math.round(yaw);
        switch (i) {
            case -4:
            case 0:
            case 4:
                return "SOUTH";
            case -3:
            case 1:
                return "WEST";
            case -2:
            case 2:
                return "NORTH";
            case -1:
            case 3:
                return "EAST";
            default:
                return "";
        }
    }

    public int getCorrectDirection(final Player player) {
        int direction = -1;
        switch (direction(player.getLocation().getYaw())) {
            case "NORTH":
                direction = 0;
                break;
            case "EAST":
                direction = 1;
                break;
            case "SOUTH":
                direction = 2;
                break;
            case "WEST":
                direction = 3;
        }
        return direction;
    }

    public void removeHand(final @NonNull Player player) {
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getAmount() > 1) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
            return;
        }
        player.setItemInHand(null);
    }

    public void soundWithDelay(Player p, Sound sound, float volume, float pitch, long delay) {
        new BukkitRunnable() {

            @Override
            public void run() {
                p.playSound(p.getLocation(), sound, volume, pitch);
            }
        }.runTaskLater(CityBuild.getINSTANCE(), delay);
    }

    public static String toLuni(String input) {
        StringBuilder result = new StringBuilder();
        input = input.toLowerCase();

        Map<Character, Character> replacementMap = new HashMap<>();
        replacementMap.put('a', 'ᴀ');
        replacementMap.put('b', 'ʙ');
        replacementMap.put('c', 'ᴄ');
        replacementMap.put('d', 'ᴅ');
        replacementMap.put('e', 'ᴇ');
        replacementMap.put('f', 'ꜰ');
        replacementMap.put('g', 'ɢ');
        replacementMap.put('h', 'ʜ');
        replacementMap.put('i', 'ɪ');
        replacementMap.put('j', 'ᴊ');
        replacementMap.put('k', 'ᴋ');
        replacementMap.put('l', 'ʟ');
        replacementMap.put('m', 'ᴍ');
        replacementMap.put('n', 'ɴ');
        replacementMap.put('o', 'ᴏ');
        replacementMap.put('p', 'ᴘ');
        replacementMap.put('q', 'Q');
        replacementMap.put('r', 'ʀ');
        replacementMap.put('s', 'ꜱ');
        replacementMap.put('t', 'ᴛ');
        replacementMap.put('u', 'ᴜ');
        replacementMap.put('v', 'ᴠ');
        replacementMap.put('w', 'ᴡ');
        replacementMap.put('x', 'x');
        replacementMap.put('y', 'ʏ');
        replacementMap.put('z', 'ᴢ');

        for (char c : input.toCharArray()) {
            char replacement = replacementMap.getOrDefault(c, c);
            result.append(replacement);
        }

        return result.toString();
    }

    public CommandMap getCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            return (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getDifference(long number1, long number2) {
        if (number1 < number2) {
            return number2 - number1;
        } else {
            return number1 - number2;
        }
    }

    public void playSuccess(final @NonNull Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    public void playChange(final @NonNull Player player) {
        player.playSound(player.getLocation(), Sound.UI_TOAST_IN, 1, 1);
    }

    public void playError(final @NonNull Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
    }

    public void playSoundAll(final @NonNull Sound sound) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer.getLocation(), sound, 1, 1);
        }
    }

    public String getGroup(final @NonNull Player player) {
        return Objects.requireNonNull(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId())).getPrimaryGroup();
    }

    public String format(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void addItem(final @NonNull Player player, final @NonNull ItemStack itemStack) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        } else {
            player.getInventory().addItem(itemStack);
        }
    }

    public boolean getChance(float minimalChance) {
        return ThreadLocalRandom.current().nextInt(99) + 1 >= minimalChance;
    }

    public void sendSyntax(final @NonNull Player player, final String[] syntax) {
        for (String s : syntax) {
            Message.sendMessage(player, s);
        }
    }

    public void fillInventory(final @NonNull Inventory inventory, final @NonNull ItemStack itemStack) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }

    public void fillBorders(final @NonNull Inventory inventory, final ItemStack itemStack) {
        int size = inventory.getSize();
        int rows = (size + 1) / 9;

        for (int i = 0; i < rows * 9; ++i) {
            if (i <= 8 || i >= rows * 9 - 9 || i == 9 || i == 18 || i == 27 || i == 36 || i == 17 || i == 26 || i == 35 || i == 44) {
                inventory.setItem(i, itemStack);
            }
        }
    }

    public String getPing(final Player player) {
        final long ping = player.getPing();
        if (ping >= 200) return "§4" + ping + "ms";
        if (ping >= 150) return "§c" + ping + "ms";
        if (ping >= 100) return "§6" + ping + "ms";
        if (ping >= 70) return "§e" + ping + "ms";
        if (ping >= 40) return "§a" + ping + "ms";
        return "§2" + ping + "ms";
    }

    public ArrayList<Integer> slowSpin() {
        ArrayList<Integer> slow = new ArrayList<>();
        int full = 46;
        int cut = 9;

        for (int i = 46; cut > 0; full--) {
            if (full <= i - cut || full >= i - cut) {
                slow.add(i);
                i -= cut;
                cut--;
            }
        }

        return slow;
    }

    public ItemCreator getRandomPaneColor() {
        List<String> colors = Arrays.asList(
                Material.WHITE_STAINED_GLASS_PANE.toString(),
                Material.ORANGE_STAINED_GLASS_PANE.toString(),
                Material.MAGENTA_STAINED_GLASS_PANE.toString(),
                Material.LIGHT_BLUE_STAINED_GLASS_PANE.toString(),
                Material.YELLOW_STAINED_GLASS_PANE.toString(),
                Material.LIME_STAINED_GLASS_PANE.toString(),
                Material.PINK_STAINED_GLASS_PANE.toString(),
                Material.GRAY_STAINED_GLASS_PANE.toString(),
                Material.CYAN_STAINED_GLASS_PANE.toString(),
                Material.PURPLE_STAINED_GLASS_PANE.toString(),
                Material.BLUE_STAINED_GLASS_PANE.toString(),
                Material.BROWN_STAINED_GLASS_PANE.toString(),
                Material.GREEN_STAINED_GLASS_PANE.toString(),
                Material.RED_STAINED_GLASS_PANE.toString(),
                Material.BLACK_STAINED_GLASS_PANE.toString(),
                Material.LIGHT_GRAY_STAINED_GLASS_PANE.toString());
        return new ItemCreator(Material.valueOf(colors.get(new Random().nextInt(colors.size()))));
    }

    public static Integer randomNumber(int min, int max) {
        return min + new Random().nextInt(max - min);
    }

    public String timeToString(long time, boolean shorten) {
        String msg = "";
        long seconds = time / 1000L;
        if (seconds >= 86400L) {
            long days = seconds / 86400L;
            msg = msg + days + (shorten ? "d " : ((days == 1L) ? " Tag, " : " Tage, "));
            seconds %= 86400L;
        }
        if (seconds >= 3600L) {
            long hours = seconds / 3600L;
            msg = msg + hours + (shorten ? "h " : (" Std, "));
            seconds %= 3600L;
        }
        if (seconds >= 60L) {
            long minutes = seconds / 60L;
            msg = msg + minutes + (shorten ? "m " : (" Min, "));
            seconds %= 60L;
        }
        if (seconds > 0L) {
            msg = msg + seconds + (shorten ? "s " : (" Sek, "));
        }
        if (!msg.isEmpty()) {
            msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
        } else {
            msg = (shorten ? "0s" : "0 Sek");
        }
        return msg;
    }

    public String timeToString(long time, boolean shorten, boolean showSeconds) {
        String msg = "";
        long seconds = time / 1000L;
        if (seconds >= 86400L) {
            long days = seconds / 86400L;
            msg = msg + days + (shorten ? "d " : ((days == 1L) ? " Tag, " : " Tage, "));
            seconds %= 86400L;
        }
        if (seconds >= 3600L) {
            long hours = seconds / 3600L;
            msg = msg + hours + (shorten ? "h " : (" Std, "));
            seconds %= 3600L;
        }
        if (seconds >= 60L) {
            long minutes = seconds / 60L;
            msg = msg + minutes + (shorten ? "m " : (" Min, "));
            seconds %= 60L;
        }
        if (showSeconds && seconds > 0L) {
            msg = msg + seconds + (shorten ? "s " : (" Sek, "));
        }
        if (!msg.isEmpty()) {
            msg = msg.substring(0, msg.length() - (shorten ? 1 : 2));
        } else {
            msg = (shorten && showSeconds ? "0s" : "0 Sek");
        }
        return msg;
    }

    public @Nullable Plot getPlotFromLocation(final @NonNull Player player) {
        return Plot.getPlot(CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getLocation());
    }

    public boolean isPlotOwner(final @NonNull Player player) {
        return Objects.equals(Objects.requireNonNull(getPlotFromLocation(player)).getOwner(), player.getUniqueId());
    }

    public boolean isPlotOwner(final @NonNull Player player, final @NonNull Plot plot) {
        return Objects.equals(plot.getOwner(), player.getUniqueId());
    }

    public int getPlotsClaimed(final @NonNull Player player) {
        if (CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()) == null) return 0;
        return CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getPlotCount();
        //return CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getClusterCount();
        //return CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getPlotCount();
    }

    public int getAllowedPlots(final @NonNull Player player) {
        if (CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()) == null) return 0;
        return CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getAllowedPlots();
    }

    public int getPlots(final @NonNull Player player) {
        AtomicInteger count = new AtomicInteger();
        boolean countedMerged = false;

        for (Plot plot : CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getPlots()) {
            if (plot.isMerged()) {
                if (!countedMerged) {
                    count.addAndGet(1);
                    countedMerged = true;
                }
            } else {
                count.addAndGet(1);
                countedMerged = false; // Reset the flag for non-merged plots
            }
        }

        return count.get();
    }

    public List<Plot> getPlayerPlots(final @NonNull Player player) {
        List<Plot> playerPlots = new ArrayList<>();
        boolean countedMerged = false;

        for (Plot plot : CityBuild.getINSTANCE().getPlotAPI().wrapPlayer(player.getUniqueId()).getPlots()) {
            if (plot.isMerged()) {
                if (!countedMerged) {
                    playerPlots.add(plot);
                    countedMerged = true;
                }
            } else {
                playerPlots.add(plot);
                countedMerged = false; // Reset the flag for non-merged plots
            }
        }

        return playerPlots;
    }


    public String withGradient(String message, final String hex1, final String hex2, final boolean bold) {
        final Color startColor = ChatColor.of(hex1).getColor();
        final Color endColor = ChatColor.of(hex2).getColor();
        final int steps = message.length();
        double redDiff = (endColor.getRed() - startColor.getRed()) / (double) steps;
        double greenDiff = (endColor.getGreen() - startColor.getGreen()) / (double) steps;
        double blueDiff = (endColor.getBlue() - startColor.getBlue()) / (double) steps;

        StringBuilder gradientText = new StringBuilder();
        for (int i = 0; i < steps; i++) {
            final ChatColor currentColor = ChatColor.of(
                    new Color(
                            (int) (startColor.getRed() + i * redDiff),
                            (int) (startColor.getGreen() + i * greenDiff),
                            (int) (startColor.getBlue() + i * blueDiff)
                    )
            );
            final String temp = (bold ? "§l" : "") + message.charAt(i);
            gradientText.append(currentColor).append(temp);
        }
        return translateHex(gradientText.toString());
    }

    public String translateHex(String message) {
        Matcher matcher = HEX_PATTERN.matcher(message);

        while (matcher.find()) {
            final String code = message.substring(matcher.start(), matcher.end());
            message = message.replace(code, "" + net.md_5.bungee.api.ChatColor.of(code));
            matcher = HEX_PATTERN.matcher(message);
        }

        return message;
    }

}
