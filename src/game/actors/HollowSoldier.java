package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Runes;

/**
 * hollow soldier extends enemy actor
 */
/**
 * abstract class that enemy actors inherit from
 *
 * Created by:
 * @author Ewan Lumsden-Smith & Phoebe Jiang
 * @version 1.0.0
 * @see EnemyActor
 */
public class HollowSoldier extends EnemyActor {
    /**
     * init sets name and hit points and display char
     */
    private static final int HIT_POINTS = 200;
    /**
     * The runes held by the HollowSoldier
     */
    private static final int RUNES = 100;
    /**
     * The intrinsic damage the weapon of the HollowSoldier can deal
     */
    private final int INTRINSIC_DAMAGE = 50;
    /**
     * The chance the HollowSoldier can hit the actor
     */
    private final int INTRINSIC_HIT_RATE = 50;
    /**
     * The chance a healing vial will be dropped
     */
    private final float HEALING_VIAL_CHANCE = 0.2f;
    /**
     * The chance a refreshing flask will be dropped
     */
    private final float REFRESHING_FLASK_CHANCE = 0.4f;

    /**
     * Constructor for instantiating a HollowSoldier
     */
    public HollowSoldier() {
        super("Hollow soldier", '&', HIT_POINTS, RUNES);
    }

    /**
     * sets intrinsic weapon
     * @return returns an Intrinsic weapon item for the class
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, "Swings at",INTRINSIC_HIT_RATE);
    }

    /**
     * drops any necessary items removes the actor from the map and returns a string of those events
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return returns a String describing the death sequence
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        drop(location , new HealingVial(), HEALING_VIAL_CHANCE);
        drop(location, new RefreshingFlask(), REFRESHING_FLASK_CHANCE);
        drop(location, new Runes(runeAmount), RUNE_DROP_ODDS);
        return super.unconscious(actor, map);
    }
}
