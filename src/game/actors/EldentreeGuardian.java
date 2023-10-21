package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.general.Ability;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Runes;

import java.util.Random;

/**
 * Class for the EldentreeGuardian enemy
 * Created by:
 * @author Ewan Lumsden-Smith
 * @version 1.0.0
 * @see FollowingEnemy
 */
public class EldentreeGuardian extends FollowingEnemy {
    /**
     * Constant that stores the max hit points
     */
    private static final int HIT_POINTS = 250;
    /**
     * Constant that stores the amount of ruins
     */
    private static final int RUINS = 250;
    /**
     * Constant that stores the weapon hit rate.
     */
    private final int HIT_RATE = 80;
    /**
     * Constant that stores the weapon damage
     */
    private final int DAMAGE = 50;
    /**
     * Constant that stores the chance of a healing vial dropping
     */
    private final float HEALING_VIAL_DROP_CHANCE = 0.25f;
    /**
     * Constant that stores the change of a refreshing flask dropping
     */
    private final float REFRESHING_FLASK_DROP_CHANCE = 0.15f;

    /**
     * construct for the enemyActor abstract class.
     */
    public EldentreeGuardian() {
        super("Eldentree Guardian", 'e', HIT_POINTS, RUINS);
        addCapability(Ability.WALK_ON_VOID);
    }

    /**
     * Creates the weapon for the Eldentree Guardian.
     * @return an IntrinsicWeapon object
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "attacks player", HIT_RATE);
    }

    /**
     * unconscious method checks to see if the actor can drop any items then returns the death sequence
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return String describing the sequence of events of the death.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        dropItem(location, new HealingVial(), HEALING_VIAL_DROP_CHANCE);
        dropItem(location, new RefreshingFlask(), REFRESHING_FLASK_DROP_CHANCE);
        dropItem(location, new Runes(ruinAmount), RUIN_DROP_ODDS);
        return super.unconscious(actor, map);
    }
}
