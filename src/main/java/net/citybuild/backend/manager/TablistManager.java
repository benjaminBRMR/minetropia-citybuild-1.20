package net.citybuild.backend.manager;

import lombok.NonNull;
import net.citybuild.CityBuild;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.color.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class TablistManager {

    private final Scoreboard scoreboard;

    private final Team owner;
    private final Team srdeveloper;
    private final Team srcontent;
    private final Team srsupporter;
    private final Team srbuilder;
    private final Team developer;
    private final Team content;
    private final Team supporter;
    private final Team builder;
    private final Team jrdeveloper;
    private final Team jrcontent;
    private final Team jrsupporter;
    private final Team jrbuilder;
    private final Team mediamanager;
    private final Team cutter;
    private final Team mediaplus;
    private final Team media;
    private final Team king;
    private final Team myzel;
    private final Team lunar;
    private final Team nova;
    private final Team kristall;
    private final Team spieler;

    public TablistManager() {
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        owner = ((scoreboard.getTeam("01owner") == null) ? scoreboard.registerNewTeam("01owner") : scoreboard.getTeam("01owner"));
        srdeveloper = ((scoreboard.getTeam("02srdeveloper") == null) ? scoreboard.registerNewTeam("02srdeveloper") : scoreboard.getTeam("02srdeveloper"));
        srcontent = ((scoreboard.getTeam("03srcontent") == null) ? scoreboard.registerNewTeam("03srcontent") : scoreboard.getTeam("03srcontent"));
        srsupporter = ((scoreboard.getTeam("04srsupporter") == null) ? scoreboard.registerNewTeam("04srsupporter") : scoreboard.getTeam("04srsupporter"));
        srbuilder = ((scoreboard.getTeam("05srbuilder") == null) ? scoreboard.registerNewTeam("05srbuilder") : scoreboard.getTeam("05srbuilder"));
        developer = ((scoreboard.getTeam("06developer") == null) ? scoreboard.registerNewTeam("06developer") : scoreboard.getTeam("06developer"));
        content = ((scoreboard.getTeam("07content") == null) ? scoreboard.registerNewTeam("07content") : scoreboard.getTeam("07content"));
        supporter = ((scoreboard.getTeam("08supporter") == null) ? scoreboard.registerNewTeam("08supporter") : scoreboard.getTeam("08supporter"));
        builder = ((scoreboard.getTeam("09builder") == null) ? scoreboard.registerNewTeam("09builder") : scoreboard.getTeam("09builder"));
        jrdeveloper = ((scoreboard.getTeam("10jrdeveloper") == null) ? scoreboard.registerNewTeam("10jrdeveloper") : scoreboard.getTeam("10jrdeveloper"));
        jrcontent = ((scoreboard.getTeam("11jrcontent") == null) ? scoreboard.registerNewTeam("11jrcontent") : scoreboard.getTeam("11jrcontent"));
        jrsupporter = ((scoreboard.getTeam("12jrsupporter") == null) ? scoreboard.registerNewTeam("12jrsupporter") : scoreboard.getTeam("12jrsupporter"));
        jrbuilder = ((scoreboard.getTeam("13jrbuilder") == null) ? scoreboard.registerNewTeam("13jrbuilder") : scoreboard.getTeam("13jrbuilder"));
        mediamanager = ((scoreboard.getTeam("14mediamanager") == null) ? scoreboard.registerNewTeam("14mediamanager") : scoreboard.getTeam("14mediamanager"));
        cutter = ((scoreboard.getTeam("15cutter") == null) ? scoreboard.registerNewTeam("15cutter") : scoreboard.getTeam("15cutter"));
        mediaplus = ((scoreboard.getTeam("16mediaplus") == null) ? scoreboard.registerNewTeam("16mediaplus") : scoreboard.getTeam("16mediaplus"));
        media = ((scoreboard.getTeam("17media") == null) ? scoreboard.registerNewTeam("17media") : scoreboard.getTeam("17media"));
        king = ((scoreboard.getTeam("18king") == null) ? scoreboard.registerNewTeam("18king") : scoreboard.getTeam("18king"));
        myzel = ((scoreboard.getTeam("19myzel") == null) ? scoreboard.registerNewTeam("19myzel") : scoreboard.getTeam("19myzel"));
        lunar = ((scoreboard.getTeam("20lunar") == null) ? scoreboard.registerNewTeam("20lunar") : scoreboard.getTeam("20lunar"));
        nova = ((scoreboard.getTeam("21nova") == null) ? scoreboard.registerNewTeam("21nova") : scoreboard.getTeam("21nova"));
        kristall = ((scoreboard.getTeam("22kristall") == null) ? scoreboard.registerNewTeam("22kristall") : scoreboard.getTeam("22kristall"));
        spieler = ((scoreboard.getTeam("99spieler") == null) ? scoreboard.registerNewTeam("99spieler") : scoreboard.getTeam("99spieler"));


        owner.setPrefix(Color.translateColorCodes("&#864BA2ʙ&#93438Bᴇ&#A03B75ꜱ&#AD335Eɪ&#B92B48ᴛ&#C62331ᴢ&#D31B1Bᴇ&#E01304ʀ §8● §7"));
        srdeveloper.setPrefix(Color.translateColorCodes("&#3a6073ꜱʀᴅᴇᴠᴇʟᴏᴘᴇʀ §8● §7"));
        srcontent.setPrefix(Color.translateColorCodes("&#f7ff00ꜱʀᴄᴏɴᴛᴇɴᴛ §8● §7"));
        srsupporter.setPrefix(Color.translateColorCodes("&#2C7744ꜱʀꜱᴜᴘᴘᴏʀᴛᴇʀ §8● §7"));
        srbuilder.setPrefix(Color.translateColorCodes("&#DBD65Cꜱʀʙᴜɪʟᴅᴇʀ §8● §7"));
        developer.setPrefix(Color.translateColorCodes("&#3a6073ᴅᴇᴠᴇʟᴏᴘᴇʀ §8● §7"));
        content.setPrefix(Color.translateColorCodes("&#f7ff00ᴄᴏɴᴛᴇɴᴛ §8● §7"));
        supporter.setPrefix(Color.translateColorCodes("&#2C7744ꜱᴜᴘᴘᴏʀᴛᴇʀ §8● §7"));
        builder.setPrefix(Color.translateColorCodes("&#DBD65Cʙᴜɪʟᴅᴇʀ §8● §7"));
        jrdeveloper.setPrefix(Color.translateColorCodes("&#3a6073ᴊʀᴅᴇᴠᴇʟᴏᴘᴇʀ §8● §7"));
        jrcontent.setPrefix(Color.translateColorCodes("&#f7ff00ᴊʀᴄᴏɴᴛᴇɴᴛ §8● §7"));
        jrsupporter.setPrefix(Color.translateColorCodes("&#2C7744ᴊʀꜱᴜᴘᴘᴏʀᴛᴇʀ §8● §7"));
        jrbuilder.setPrefix(Color.translateColorCodes("&#DBD65Cᴊʀʙᴜɪʟᴅᴇʀ §8● §7"));
        mediamanager.setPrefix(Color.translateColorCodes("&#EB00FFᴍᴇᴅɪᴀ ᴍᴀɴᴀɢᴇʀ §8● §7"));
        cutter.setPrefix(Color.translateColorCodes("&#EB00FFᴄᴜᴛᴛᴇʀ §8● §7"));
        mediaplus.setPrefix(Color.translateColorCodes("&#EB00FFᴍᴇᴅɪᴀ+ §8● §7"));
        media.setPrefix(Color.translateColorCodes("&#EB00FFᴍᴇᴅɪᴀ §8● §7"));
        king.setPrefix(Color.translateColorCodes("&#5614B0ᴋ&#825594ɪ&#AF9578ɴ&#DBD65Cɢ §8● §7"));
        myzel.setPrefix(Color.translateColorCodes("&#834D9Bᴍ&#964DAAʏ&#AA4EB9ᴢ&#BD4EC7ᴇ&#D04ED6ʟ §8● §7"));
        lunar.setPrefix(Color.translateColorCodes("&#2C3E50ʟ&#345768ᴜ&#3C7080ɴ&#448897ᴀ&#4CA1AFʀ §8● §7"));
        nova.setPrefix(Color.translateColorCodes("&#4CA1AFɴ&#74B6C1ᴏ&#9CCBD3ᴠ&#C4E0E5ᴀ §8● §7"));
        kristall.setPrefix(Color.translateColorCodes("&#E0EAFCᴋ&#DEE8FBʀ&#DBE7F9ɪ&#D9E5F8ꜱ&#D6E3F7ᴛ&#D4E1F6ᴀ&#D1E0F4ʟ&#CFDEF3ʟ §8● §7"));
        spieler.setPrefix(Color.translateColorCodes("§7"));

        scoreboard.getTeams().forEach(teams -> {
            teams.setColor(ChatColor.GRAY);
        });

    }

    public void hook(final @NonNull Player player) {

        if (Utility.getGroup(player).equalsIgnoreCase("owner")) {
            Objects.requireNonNull(owner).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srdeveloper")) {
            Objects.requireNonNull(srdeveloper).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srcontent")) {
            Objects.requireNonNull(srcontent).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srsupporter")) {
            Objects.requireNonNull(srsupporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srbuilder")) {
            Objects.requireNonNull(srbuilder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("developer")) {
            Objects.requireNonNull(developer).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("content")) {
            Objects.requireNonNull(content).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("supporter")) {
            Objects.requireNonNull(supporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("builder")) {
            Objects.requireNonNull(builder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrdeveloper")) {
            Objects.requireNonNull(jrdeveloper).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrcontent")) {
            Objects.requireNonNull(jrcontent).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrsupporter")) {
            Objects.requireNonNull(jrsupporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrbuilder")) {
            Objects.requireNonNull(jrbuilder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("mediamanager")) {
            Objects.requireNonNull(mediamanager).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("cutter")) {
            Objects.requireNonNull(cutter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("mediaplus")) {
            Objects.requireNonNull(mediaplus).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("media")) {
            Objects.requireNonNull(media).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("king")) {
            Objects.requireNonNull(king).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("myzel")) {
            Objects.requireNonNull(myzel).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("lunar")) {
            Objects.requireNonNull(lunar).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("nova")) {
            Objects.requireNonNull(nova).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("kristall")) {
            Objects.requireNonNull(kristall).addEntry(player.getName());
        } else {
            Objects.requireNonNull(spieler).addEntry(player.getName());
        }

        player.setScoreboard(this.scoreboard);

    }

    public void update(final @NonNull Player player) {
        if (Utility.getGroup(player).equalsIgnoreCase("owner")) {
            if (!owner.hasEntry(player.getName())) owner.addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srdeveloper")) {
            Objects.requireNonNull(srdeveloper).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srcontent")) {
            Objects.requireNonNull(srcontent).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srsupporter")) {
            Objects.requireNonNull(srsupporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("srbuilder")) {
            Objects.requireNonNull(srbuilder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("developer")) {
            Objects.requireNonNull(developer).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("content")) {
            Objects.requireNonNull(content).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("supporter")) {
            Objects.requireNonNull(supporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("builder")) {
            Objects.requireNonNull(builder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrdeveloper")) {
            Objects.requireNonNull(jrdeveloper).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrcontent")) {
            Objects.requireNonNull(jrcontent).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrsupporter")) {
            Objects.requireNonNull(jrsupporter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("jrbuilder")) {
            Objects.requireNonNull(jrbuilder).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("mediamanager")) {
            Objects.requireNonNull(mediamanager).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("cutter")) {
            Objects.requireNonNull(cutter).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("mediaplus")) {
            Objects.requireNonNull(mediaplus).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("media")) {
            Objects.requireNonNull(media).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("king")) {
            Objects.requireNonNull(king).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("myzel")) {
            Objects.requireNonNull(myzel).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("lunar")) {
            Objects.requireNonNull(lunar).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("nova")) {
            Objects.requireNonNull(nova).addEntry(player.getName());
        } else if (Utility.getGroup(player).equalsIgnoreCase("kristall")) {
            Objects.requireNonNull(kristall).addEntry(player.getName());
        } else {
            Objects.requireNonNull(spieler).addEntry(player.getName());
        }

        player.getScoreboard().getPlayerTeam(player).setSuffix((CityBuild.getAfkManager().isAFK(player) ? " §8︳ " + Color.translateColorCodes("&#FFEFBAᴀ&#FFF7DDꜰ&#FFFFFFᴋ") : ""));
        player.setDisplayName(player.getScoreboard().getPlayerTeam(player).getPrefix() + player.getName());
        player.setScoreboard(this.scoreboard);
    }
}
