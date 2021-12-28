package gamemodel.gameentity.actionchain.updateaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.sound.MediaMgr;

public class PlayMediaUpdateAction extends _GameEntityActionChain {
    private MediaMgr.SoundName soundName;

    public PlayMediaUpdateAction(MediaMgr.SoundName soundName) {
        this.soundName = soundName;
    }

    @Override
    public void _doAction(IGameEntity gameEntity) {
        MediaMgr.play(soundName);
    }





}
