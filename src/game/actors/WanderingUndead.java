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
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int number = random.nextInt(10);
        if(number < 2){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n" + name + " dropped a healing Vial" + "\n");
        }
        if(random.nextInt(4) == 1){
            map.locationOf(this).addItem(new Key());
            builder.append("\n" + name + " dropped a key");
        }
        map.locationOf(this).addItem(new Runes(50));
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }
}

