package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.sound.MediaMgr;

public class StopMediaHitAction extends _GameEntityActionChain {
    // --Commented out by Inspection (2021-12-27 23:44):IGameEntity ufoEntity;
    MediaMgr.SoundName soundName;

// --Commented out by Inspection START (2021-12-27 23:44):
//    public StopMediaHitAction(IGameEntity ufoEntity) {
//        this.ufoEntity = ufoEntity;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:44)

    public StopMediaHitAction(MediaMgr.SoundName soundName) {
       this.soundName = soundName;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        var soundClip = MediaMgr.get(soundName);
        soundClip.stop();
    }

}
