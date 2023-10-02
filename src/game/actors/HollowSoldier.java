package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Runes;

import java.util.Random;

/**
 * hollow soldier extends enemy actor
 */
public class HollowSoldier extends EnemyActor {
    /**
     * init sets name and hitpoints and display char
     */

    public HollowSoldier() {
        super("Hollow soldier", '&', 200, 100);
    }

    /**
     * sets intrinsic weapon
     * @return returns an Intrinsic weapon item for the class
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "Swings at",50);
    }

    /**
     * drops any necessary items removes the actor from the map and returns a string of those events
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return returns a String describing the death sequence
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int number = random.nextInt(10);
        if(number < 2){
            map.locationOf(this).addItem(new HealingVial());
            String tempString = "\n" + name + " dropped a healing Vial" + "\n";
            builder.append(tempString);
        }
        if(number < 4){
            map.locationOf(this).addItem(new RefreshingFlask());
            String tempString = "\n" + name + " dropped a refreshing flask" + "\n";
            builder.append(tempString);
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        return builder.toString();

    }
}
