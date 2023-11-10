package game.actors;

import game.general.Status;

public class BossEnemy extends EnemyActor {
    public BossEnemy(String name, char displayChar, int hitPoints, int runeAmount){
        super(name, displayChar, hitPoints, runeAmount);
        this.removeCapability(Status.AFFECTED_BY_RESPAWN);
    }
}
