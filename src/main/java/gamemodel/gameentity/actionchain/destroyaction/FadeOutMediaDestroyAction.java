package gamemodel.gameentity.actionchain.destroyaction;

import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain._GameEntityActionChain;
import gameview.sound.MediaMgr;

public class FadeOutMediaDestroyAction extends _GameEntityActionChain {

    MediaMgr.SoundName soundName;

    public FadeOutMediaDestroyAction(MediaMgr.SoundName soundName) {
        this.soundName = soundName;
    }

    @Override
    public void _doAction(IGameEntity gameEntityCollidedWith) {
        MediaMgr.fadeOut(soundName);
    }
}
