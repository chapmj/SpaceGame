package gameview.sound;

import javafx.scene.media.AudioClip;

import java.util.EnumMap;

import static gameview.sound.SoundMgr.SoundName.NO_SOUND;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class SoundMgr {

    private static SoundMgr instance;
    private EnumMap<SoundName, AudioClip> soundClips = new EnumMap<>(SoundName.class);

    private SoundMgr() { }

    public static SoundMgr getInstance() {
        if (instance == null) {
            instance = new SoundMgr();
        }
        return instance;
    }

    public void add(SoundName soundName, AudioClip soundClip) {
        this.soundClips.put(soundName, soundClip);
    }

    private AudioClip getSoundClip(SoundName soundName) {
        var soundClip = soundClips.get(soundName);
        return soundClip;
    }

    public static AudioClip get(SoundName soundName) {
        return getInstance().getSoundClip(soundName);
    }

    public static void play(SoundName soundName, double volume) {
        if(soundName != NO_SOUND) {
            var clip = get(soundName);
            clip.setVolume(volume);
            clip.play();
        }
    }

    public static void playInterrupting(SoundName soundName, double volume) {
        if(soundName != NO_SOUND) {
            var clip = get(soundName);
            if (clip.isPlaying()) {
                clip.stop();
            }
            clip.setVolume(volume);
            clip.play();
        }
    }

    public static void playLoop(SoundName soundName) {
        if(soundName != NO_SOUND) {
            var clip = get(soundName);
            if (!clip.isPlaying()) {
                clip.setCycleCount(INDEFINITE);
                clip.play();
            }
        }
    }

    public static void stop(SoundName soundName) {
        var clip = get(soundName);
        clip.stop();
    }


    public enum SoundName {
        MISSILE_EXPLODE, ALIEN_EXPLODE, MISSILE_SHOOT, UFO_LOOP, UFO_HIGH, UFO_LOW, NO_SOUND,
        BEAT_70, BEAT_60, BEAT_50, BEAT_40, SILENCE
    }
}
