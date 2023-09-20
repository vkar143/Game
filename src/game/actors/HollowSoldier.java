package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.RefreshingFlask;

import java.util.Random;

/**
 * hollow soldier extends enemy actor
 */
public class HollowSoldier extends EnemyActor {
    /**
     * init sets name and hitpoints and display char
     */
    public HollowSoldier() {
        super("Hollow soldier", '&', 200);
    }

    /**
     * sets intrinsic weapon
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "Swings at",50);
    }

    /**
     * drops any necessary items removes the actor from the map and returns a string of those events
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int number = random.nextInt(10);
        if(number < 4){
            map.locationOf(this).addItem(new RefreshingFlask());
            builder.append("\n" + name + " dropped a refreshing flask" + "\n");
        }
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }
}
