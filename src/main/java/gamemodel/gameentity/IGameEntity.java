package gamemodel.gameentity;

import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gamerect.IRect;
import gameview.gameimage.SpriteName;
import gameview.gameimage._Sprite;

public interface IGameEntity extends IUpdateable, ICollidable, IDestroyable
{
    IRect getRect();
    void swapRect(IRect rect);
    GameEntityMgr.RootNames getName();
    void setName(GameEntityMgr.RootNames name);
    int getSpeed();
    void setSpeed(int speed);
    void setSpriteName(SpriteName spriteName);
    _Sprite getSprite();
    void setSprite(_Sprite sprite);
}
