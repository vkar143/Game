package game.actors;

import game.general.Status;

public abstract class BossEnemy extends EnemyActor {
    public BossEnemy(String name, char displayChar, int hitPoints, int runeAmount){
        super(name, displayChar, hitPoints, runeAmount);
        this.addCapability(Status.BOSS);
    }
}
