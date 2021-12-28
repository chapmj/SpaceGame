package gamemodel.gameentity.actionchain.hitaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.sound.SoundMgr;

public class PlaySoundHitAction extends _GameEntityActionChain {
    SoundMgr.SoundName soundName;
    // --Commented out by Inspection (2021-12-27 23:49):IGameEntity gameEntityCollidedWith;

    public PlaySoundHitAction(SoundMgr.SoundName soundName) {
       this.soundName = soundName;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        SoundMgr.playInterrupting(soundName, 0.1);
    }

// --Commented out by Inspection START (2021-12-27 23:49):
//    public void setGameEntityCollidedWith(IGameEntity gameEntityCollidedWith) {
//        this.gameEntityCollidedWith = gameEntityCollidedWith;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:49)

// --Commented out by Inspection START (2021-12-27 23:49):
//    public void wash() {
//        this.soundName = SoundMgr.SoundName.NO_SOUND;
//        this.gameEntityCollidedWith = null;
//    }
// --Commented out by Inspection STOP (2021-12-27 23:49)
}
