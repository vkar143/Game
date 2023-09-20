package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * extends enemy actor the wanderingundead is the class of the first enemy in the game
 */
public class WanderingUndead extends EnemyActor {

    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
    }

    /**
     * sets its intrinsic weapon
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "Swings at",50);
    }
    @Override
    public String unconscious(Actor actor, GameMap map) {
        return super.unconscious(actor, map);
    }
}

