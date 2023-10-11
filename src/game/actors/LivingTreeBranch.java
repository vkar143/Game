package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;

public class LivingTreeBranch extends EnemyActor{
    /**
     * construct for the enemyActor abstract class.
     */
    public LivingTreeBranch() {
        super("Living Tree Branch", '?', 75, 500);
        behaviours.remove(997);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(250, "attacks player", 90);
    }
}
