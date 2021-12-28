package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.sound.SoundMgr;

public class StopSoundHitAction extends _GameEntityActionChain {
    IGameEntity ufoEntity;
    SoundMgr.SoundName soundName;

    public StopSoundHitAction(IGameEntity ufoEntity) {
        this.ufoEntity = ufoEntity;
    }

    public StopSoundHitAction(SoundMgr.SoundName soundName) {
       this.soundName = soundName;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var soundClip = SoundMgr.get(soundName);
        soundClip.stop();
    }

}
