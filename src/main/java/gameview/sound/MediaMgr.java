package gameview.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.EnumMap;

import static gameview.sound.MediaMgr.SoundName.*;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class MediaMgr {

    private static MediaMgr instance;
    private EnumMap<SoundName, MediaPlayer> soundClips = new EnumMap<>(SoundName.class);

    private MediaMgr() { }

    public static MediaMgr getInstance() {
        if (instance == null) {
            instance = new MediaMgr();
        }
        return instance;
    }

    public static void fadeOut(SoundName soundName) {
        if(soundName != NO_SOUND) {
            var clip = getInstance().getSoundClip(soundName);
            clip.cycleCountProperty().setValue(0);
        }
    }


    public void add(SoundName soundName, Media soundClip) {
        if(soundName != NO_SOUND) {
            var clip = new MediaPlayer(soundClip);
            clip.setRate(1.0);
            clip.setCycleCount(INDEFINITE);
            clip.setOnEndOfMedia(() -> MediaMgr.stop(soundName));
            this.soundClips.put(soundName, clip);
        }
    }

    private MediaPlayer getSoundClip(SoundName soundName) {
        var soundClip = soundClips.get(soundName);
        return soundClip;
    }

    public static MediaPlayer get(SoundName soundName) {
        return getInstance().getSoundClip(soundName);
    }

    public static void play(SoundName soundName) {
        if(soundName != NO_SOUND) {
            var clip = get(soundName);
            clip.cycleCountProperty().setValue(INDEFINITE);
            clip.play();
        }

    }

    public static void stop(SoundName soundName) {
        if(soundName != NO_SOUND) {
            var clip = get(soundName);
            clip.stop();
            clip.setVolume(1);
        }
    }

    public enum SoundName {
        MISSILE_EXPLODE, ALIEN_EXPLODE, MISSILE_SHOOT, UFO_LOOP, UFO_HIGH, UFO_LOW, UFO_BUZZ, UFO_CRASH, NO_SOUND,
        MOOG_0, MOOG_1, MOOG_2, MOOG_3
    }
}
