package gameview.sound;

import gamecontroller.command.PlaySoundCommand;
import gamecontroller.gameroundinitializer._Initializer;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.nio.file.Paths;

public class SoundInitializer extends _Initializer {
    @Override
    public void init() {
        var soundMgr = SoundMgr.getInstance();

        //From http://www.classicgaming.cc/classics/space-invaders/sounds
        {
            var filename = Paths.get("src/main/resources/explosion.wav").toUri().toString();
            var clip = new AudioClip(filename);
            soundMgr.add(SoundMgr.SoundName.MISSILE_EXPLODE, clip);
        }

        {
            var filename = Paths.get("src/main/resources/invaderpop.wav").toUri().toString();
            var clip = new AudioClip(filename);
            soundMgr.add(SoundMgr.SoundName.ALIEN_EXPLODE, clip);
        }

        {
            var filename = Paths.get("src/main/resources/shoot.wav").toUri().toString();
            var clip = new AudioClip(filename);
            clip.setVolume(0);
            clip.play();
            clip.setVolume(0.5);

            soundMgr.add(SoundMgr.SoundName.MISSILE_SHOOT, clip);
        }

/*
        {
            var filename = Paths.get("src/main/resources/ufo_loop.wav").toUri().toString();
            var clip = new AudioClip(filename);
            soundMgr.add(SoundMgr.SoundName.UFO_LOOP, clip);
        }

        {
            var filename = Paths.get("src/main/resources/ufo_highpitch.wav").toUri().toString();
            var clip = new AudioClip(filename);
            soundMgr.add(SoundMgr.SoundName.UFO_HIGH, clip);
        }

        {
            var filename = Paths.get("src/main/resources/ufo_lowpitch_bad.wav").toUri().toString();
            var clip = new AudioClip(filename);
            soundMgr.add(SoundMgr.SoundName.UFO_LOW, clip);
        }
*/

        {
            var filename = Paths.get("src/main/resources/ufo_buzz.wav").toUri().toString();
            var clip = new Media(filename);
            MediaMgr.getInstance().add(MediaMgr.SoundName.UFO_BUZZ, clip);
        }
        {
            var filename = Paths.get("src/main/resources/ufo_crash.wav").toUri().toString();
            var clip = new Media(filename);
            MediaMgr.getInstance().add(MediaMgr.SoundName.UFO_CRASH, clip);
        }

        {
            var filename = Paths.get("src/main/resources/square-70.wav").toUri().toString();
            var clip = new AudioClip(filename);
            SoundMgr.getInstance().add(SoundMgr.SoundName.BEAT_70, clip);
        }

        {
            var filename = Paths.get("src/main/resources/square-60.wav").toUri().toString();
            var clip = new AudioClip(filename);
            SoundMgr.getInstance().add(SoundMgr.SoundName.BEAT_60, clip);
        }

        {
            var filename = Paths.get("src/main/resources/square-50.wav").toUri().toString();
            var clip = new AudioClip(filename);
            SoundMgr.getInstance().add(SoundMgr.SoundName.BEAT_50, clip);
        }

        {
            var filename = Paths.get("src/main/resources/square-40.wav").toUri().toString();
            var clip = new AudioClip(filename);
            SoundMgr.getInstance().add(SoundMgr.SoundName.BEAT_40, clip);
        }

        {
            var filename = Paths.get("src/main/resources/silence.wav").toUri().toString();
            var clip = new AudioClip(filename);
            SoundMgr.getInstance().add(SoundMgr.SoundName.SILENCE, clip);
        }


        var playSilence = new PlaySoundCommand(SoundMgr.SoundName.SILENCE, 1);
        playSilence.execute();

    }
}
