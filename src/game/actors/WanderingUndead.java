package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
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
     * The bound of the healing vial
     */
    private final int HEALING_VIAL_BOUND = 10;
    /**
     * The bound of the old key
     */
    private final int OLD_KEY_BOUND = 4;
    /**
     * The chance of a healing vial being dropped
     */
    private final int HEALING_VIAL_CHANCE = 2;
    /**
     * The chance of an old key being dropped
     */
    private final int OLD_KEY_CHANCE = 1;
    /**
     * The offset amount
     */
    private final int OFFSET = 0;

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
        StringBuilder builder = new StringBuilder();
        Random healingVialRandom = new Random();
        int healingVialNumber = healingVialRandom.nextInt(HEALING_VIAL_BOUND);
        if(healingVialNumber < HEALING_VIAL_CHANCE){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n").append(name).append(" dropped a healing Vial").append("\n");
        }
        Random oldKeyRandom = new Random();
        int oldKeyNumber = oldKeyRandom.nextInt(OLD_KEY_BOUND);
        if(oldKeyNumber < OLD_KEY_CHANCE){
            map.locationOf(this).addItem(new Key());
            builder.append("\n").append(name).append(" dropped a key");
        }
        builder.insert(OFFSET,super.unconscious(actor, map));
        return builder.toString();
    }
}

