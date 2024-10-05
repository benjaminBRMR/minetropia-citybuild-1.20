package net.citybuild.backend.utility.sound;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import lombok.experimental.UtilityClass;
import net.citybuild.backend.utility.Utility;
import org.bukkit.entity.Player;

@UtilityClass
public class SoundUtility {

    public void playSong(Song song, Player p, boolean loop) {
        RadioSongPlayer rsp = new RadioSongPlayer(song);
        rsp.addPlayer(p);
        if (loop) {
            rsp.setRepeatMode(RepeatMode.ALL);
        }
        rsp.setPlaying(true);
    }

    public void rareDropJingle(Player p) {
        Utility.soundWithDelay(p, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.594604F, 6);
        Utility.soundWithDelay(p, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.793701F, 9);
        Utility.soundWithDelay(p, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1.059436F, 12);
        Utility.soundWithDelay(p, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1.189207F, 15);
    }

}
