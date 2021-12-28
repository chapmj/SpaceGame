package gamemodel.gameentity.factory;

import gamemodel.gameentity.GameEntitySpriteSubscriber;
import gamemodel.gameentity.gameentitycomponent.GameEntityComposite;
import gamemodel.gameentity.gameentitycomponent.GameEntityLockable;
import gamemodel.gameentity.gameentitycomponent.GameEntityMgr;
import gamemodel.gameentity.gameentitycomponent._GameEntityComponent;
import gameview.gameimage.SpriteMgr;
import gameview.gameimage.SpriteName;

public class FactoryShieldGrid {

    int numShields;
    int shieldPadding;

    public FactoryShieldGrid (int numShields, int shieldPadding) {
        this.numShields = numShields;
        this.shieldPadding = shieldPadding;
    }

    public _GameEntityComponent createGrid() {

        var shieldFactory = new FactoryShield(new FactoryShieldCell());

        var shieldGrid = new GameEntityComposite(GameEntityMgr.RootNames.SHIELD_ROOT);

        for(int i = 0; i < numShields; i++) {
            var shield = shieldFactory.create();
            var shieldWidth = shield.getRect().w();
            shield.translatePosition((shieldWidth + shieldPadding) * i, 0);

            var lockableShield = new GameEntityLockable(shield.getName(), shield);
            shieldGrid.add(lockableShield);

            var sprite = SpriteMgr.getInstance().create(SpriteName.SHIELD);
            SpriteMgr.getInstance().add(sprite);
            var spriteSubscriber = new GameEntitySpriteSubscriber(lockableShield);
            sprite.attach(spriteSubscriber);
        }

        shieldGrid.init();

        return shieldGrid;

    }
}
