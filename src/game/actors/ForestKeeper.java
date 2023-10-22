package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.Runes;

/**
 * class for the Forest Keeper which extends EnemyActor
 * Created by:
 * @author Ewan Lumsden-Smith & Phoebe Jiang
 * @version 1.0.0
 * @see EnemyActor
 */
public class ForestKeeper extends FollowingEnemy {
    /**
     * Constant for the Hit points
     */
    private static final int HIT_POINTS = 125;
    /**
     * Constant for the number of runes dropped
     */
    private static final int RUNES = 50;
    /**
     * Constant for the amount of damage dealt
     */
    private final int INTRINSIC_DAMAGE = 25;
    /**
     * Constant for the likelihood of attack landing
     */
    private final int INTRINSIC_HIT_RATE = 75;
    /**
     * The chance of the enemy actor dropping a healing vial
     */
    private final float HEALING_VIAL_DROP_CHANCE = 0.2f;

    /**
     * Constructor: sets the attributes as well as the behaviours and capabilities on an enemy actor
     */
    public ForestKeeper() {
        super("Forest Keeper", '8', HIT_POINTS, RUNES);
    }

    /**
     * sets the intrinsic weapon of the enemy
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, "swings at", INTRINSIC_HIT_RATE);
    }

    /**
     * Drops necessary items and removes actor from map after they die
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return A String describing the events of their death
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        drop(location, new HealingVial(), HEALING_VIAL_DROP_CHANCE);
        drop(location, new Runes(runeAmount), RUNE_DROP_ODDS);
        return super.unconscious(actor, map);
    }
}
