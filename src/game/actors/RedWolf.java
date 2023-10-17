package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehavior;
import game.general.Status;
import game.items.HealingVial;
import game.items.Runes;
import java.util.Random;

/**
 * RedWolf enemy actor class
 *
 * Created by:
 * @author Ewan Lumsden-Smith & Phoebe Jiang
 * @version 1.0.0
 * @see EnemyActor
 */
public class RedWolf extends EnemyActor {
    /**
     * Constant for hit points of the actor
     */
    private static final int HIT_POINTS = 25;
    /**
     * Constant for the amount of runes RedWolf drops
     */
    private static final int COST = 25;
    /**
     * Constant for the damage the enemy actor deals.
     */
    private final int INTRINSIC_DAMAGE = 15;
    /**
     * constant for the hit rate of the RedWolf
     */
    private final int INTRINSIC_HIT_RATE = 80;
    /**
     * Constant for the Follow Behaviour Priority
     */
    private final int FOLLOW_BEHAVIOUR_PRIORITY = 998;
    /**
     * constant the odds of the item drop
     */
    private final int BOUND = 10;
    /**
     * constant for the odds of the item drop
     */
    private final int CHANCE = 1;
    /**
     * Constant for the position in the string builder to add the super
     */
    private final int OFFSET = 1;

    /**
     * RedWolf constructor
     */
    public RedWolf() {
        super("Red Wolf", 'r',HIT_POINTS, COST);
    }

    /**
     * sets the intrinsic weapon of the RedWolf
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(INTRINSIC_DAMAGE,"bites" , INTRINSIC_HIT_RATE);
    }

    /**
     * drops all the necessary items after actor dies and returns a string describing the events
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return String of what dropped
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int number = random.nextInt(BOUND);
        if(number < CHANCE){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n").append(name).append(" dropped a healing Vial").append("\n");
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        builder.insert(OFFSET,super.unconscious(actor, map));
        return builder.toString();
    }

    /**
     * Sets the follow behaviour in the object if the player comes within range and then returns allowable actions
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of allowable actions for a given other actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            addBehavior(FOLLOW_BEHAVIOUR_PRIORITY,new FollowBehavior(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);
    }

//TODO: TESTER
public float getDamage(){
    return damageMultiplier * INTRINSIC_DAMAGE;  
}
}