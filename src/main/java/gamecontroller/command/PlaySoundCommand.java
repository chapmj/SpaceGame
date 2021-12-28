package gamecontroller.command;

import gameview.sound.SoundMgr;

public class PlaySoundCommand implements _ICommand {


    private final SoundMgr.SoundName soundName;
    private double volume;

    public PlaySoundCommand(SoundMgr.SoundName soundName, double volume) {
        this.soundName = soundName;
        this.volume = volume;
    }

    @Override
    public void execute() {
        SoundMgr.play(soundName, volume);
    }
}
