package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.IGameEntity;
import gamemodel.gameentity.actionchain.destroyaction.DestroyAlienDestroyAction;
import gamemodel.gameentity.actionchain.hitaction.*;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;
import gameview.sound.SoundMgr;

import java.util.Objects;

import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.MISSILE_ROOT;
import static gamemodel.gameentity.gameentitycomponent.GameEntityMgr.RootNames.WALL_ROOT;

public class FactoryAlien extends _FactoryGameEntity {
    private static FactoryAlien crabFactory = new FactoryAlien(AlienType.CRAB);
    private static FactoryAlien octopusFactory = new FactoryAlien(AlienType.OCTOPUS);
    private static FactoryAlien squidFactory = new FactoryAlien(AlienType.SQUID);
    AlienType alienType;
    private int points = 0;

    public static FactoryAlien getFactory(AlienType alienType) {
        Objects.requireNonNull(alienType);

        switch(alienType) {
            case CRAB:
                Objects.requireNonNull(crabFactory);
                return crabFactory;

            case OCTOPUS:
                Objects.requireNonNull(octopusFactory);
                return octopusFactory;

            case SQUID:
                Objects.requireNonNull(squidFactory);
                return squidFactory;

        }
        return null;
    }

    public FactoryAlien(AlienType alienType) {
        this.alienType = alienType;
    }

    public IGameEntity create() {
        switch(alienType) {
            case CRAB:
                return createCrab();
            case OCTOPUS:
                return createOcto();
            case SQUID:
                return createSquid();
        }
        return null;
    }

    private IGameEntity createCrab() {
        points = 20;
        return createAlienEntity(SpriteName.CRAB);
    }

    private IGameEntity createOcto() {
        points = 10;
        return createAlienEntity(SpriteName.OCTOPUS);
    }

    private IGameEntity createSquid() {
        points = 30;
        return createAlienEntity(SpriteName.SQUID);
    }

    private IGameEntity createAlienEntity(SpriteName spriteName) {
        var sprite = SpriteMgr.getInstance().create(spriteName);
        //SpriteMgr.getInstance().add(sprite);

        var w = sprite.getW();
        var h = sprite.getH();

        var gameEntity = FactoryGameEntity.Create(w, h, 0, GameEntityMgr.RootNames.ALIEN_ROOT);
        gameEntity.setSprite(sprite);

        gameEntity.setSpriteName(spriteName);

        var spriteSubscriber = new GameEntitySpriteSubscriber(gameEntity);
        sprite.attach(spriteSubscriber);

        var hitAction = new ChoiceAction(gameEntity);
        hitAction.add(MISSILE_ROOT, new UpdateScoreHitAction(points));
        hitAction.add(MISSILE_ROOT, new PlaySoundHitAction(SoundMgr.SoundName.ALIEN_EXPLODE));
        hitAction.add(MISSILE_ROOT, new CreateTimedSplatHitAction(gameEntity, SpriteName.ALIEN_SPLAT, (char) 8));
        hitAction.add(MISSILE_ROOT, new DestroyGameEntityHitAction(gameEntity));
        hitAction.add(MISSILE_ROOT, new DecreaseHeatHitAction());
        hitAction.add(MISSILE_ROOT, new UpdateAlienEliminatedHitAction());
        hitAction.add(MISSILE_ROOT, new PauseMarchHitAction(8));
        hitAction.add(WALL_ROOT, new AlienInWallHitAction());
        gameEntity.setHitAction(hitAction);

        var destroyAction = new UnsubscribeSpriteAction(sprite, spriteSubscriber);
        destroyAction.addAction(new DestroyAlienDestroyAction());
        gameEntity.setDestroyAction(destroyAction);

        return gameEntity;
    }

    public enum AlienType {
        CRAB, OCTOPUS, SQUID
    }

}
