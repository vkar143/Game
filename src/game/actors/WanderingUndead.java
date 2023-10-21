package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.Key;
import game.items.Runes;

import java.util.Random;

/**
 * extends enemy actor the wandering undead is the class of the first enemy in the game
 *
 * Created by:
 * @author Ewan Lumsden-Smith & Phoebe Jiang
 * @version 1.0.0
 * @see EnemyActor
 */
public class WanderingUndead extends EnemyActor {
    /**
     * The amount of hit points WanderingUndead has
     */
    private static final int HIT_POINTS = 100;
    /**
     * The amount of runes the WanderingUndead has
     */
    private static final int RUNES = 50;
    /**
     * The amount of damage an intrinsic weapon can do
     */
    private final int DAMAGE = 30;
    /**
     * The chances of the attack being successful
     */
    private final int HIT_RATE = 50;
    /**
     * The chance of a healing vial being dropped
     */
    private final float HEALING_VIAL_CHANCE = 0.2f;
    /**
     * The chance of an old key being dropped
     */
    private final float OLD_KEY_CHANCE = 1.0f;

    /**
     * Constructor for creating instances of the WanderingUndead
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', HIT_POINTS, RUNES);
    }

    /**
     * sets its intrinsic weapon
     * @return returns the enemies intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DAMAGE, "Swings at",HIT_RATE);
    }
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        dropItem(location, new HealingVial(), HEALING_VIAL_CHANCE);
        dropItem(location, new Key(), OLD_KEY_CHANCE);
        dropItem(location, new Runes(ruinAmount), RUIN_DROP_ODDS);
        return super.unconscious(actor, map);
    }
}

