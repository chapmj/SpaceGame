package gameview.sound;

import gamecontroller.HeatMgr;
import gamecontroller.command.PlaySoundCommand;
import gamecontroller.command.StopSoundCommand;

public class Drumbeat {

    private static Drumbeat instance;

    int beatCounter = 0;
    PlaySoundCommand[] playBeats = {
        new PlaySoundCommand(SoundMgr.SoundName.BEAT_70, 0.15),
        new PlaySoundCommand(SoundMgr.SoundName.BEAT_60, 0.15),
        new PlaySoundCommand(SoundMgr.SoundName.BEAT_50, 0.15),
        new PlaySoundCommand(SoundMgr.SoundName.BEAT_40, 0.15)
    };
    StopSoundCommand[] stopBeats = {
            new StopSoundCommand(SoundMgr.SoundName.BEAT_70),
            new StopSoundCommand(SoundMgr.SoundName.BEAT_60),
            new StopSoundCommand(SoundMgr.SoundName.BEAT_50),
            new StopSoundCommand(SoundMgr.SoundName.BEAT_40)
    };

    public static Drumbeat getInstance() {
        if (instance == null) {
            instance = new Drumbeat();
        }
        return instance;
    }

    public void update() {
        var isHot = HeatMgr.CheckHeat();

        if(isHot) {

            var stopCounter = beatCounter - 1;
            if (stopCounter < 0) stopCounter = playBeats.length - 1;
            stopBeats[stopCounter].execute();
            playBeats[beatCounter].execute();
            beatCounter = (beatCounter + 1) % playBeats.length;
        }
    }
}
