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
 */
public class WanderingUndead extends EnemyActor {

    private static final int HIT_POINTS = 100;
    private static final int COST = 50;
    private final int DAMAGE = 30;
    private final int HIT_RATE = 50;
    private final int HEALING_VIAL_BOUND = 10;
    private final int OLD_KEY_BOUND = 4;
    private final int HEALING_VIAL_CHANCE = 2;
    private final int OLD_KEY_CHANCE = 1;
    private final int OFFSET = 0;

    public WanderingUndead() {
        super("Wandering Undead", 't', HIT_POINTS, COST);
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

