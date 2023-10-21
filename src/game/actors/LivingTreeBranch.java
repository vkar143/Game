package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.WanderBehaviour;
import game.general.Ability;

public class LivingTreeBranch extends EnemyActor{
    /**
     * construct for the enemyActor abstract class.
     */
    public LivingTreeBranch() {
        super("Living Tree Branch", '?', 75, 500);
        addCapability(Ability.WALK_ON_VOID);
        behaviours.remove(997);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(250, "attacks player", 90);
    }
}
