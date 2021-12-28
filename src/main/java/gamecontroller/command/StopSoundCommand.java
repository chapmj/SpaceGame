package gamecontroller.command;

import gameview.sound.SoundMgr;

public class StopSoundCommand implements _ICommand {


    private final SoundMgr.SoundName soundName;

    public StopSoundCommand(SoundMgr.SoundName soundName) {
        this.soundName = soundName;
    }

    @Override
    public void execute() {
        SoundMgr.stop(soundName);
    }
}
