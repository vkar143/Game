package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;

import java.util.Random;

public class ForestKeeper extends EnemyActor {

    /**
     * sets the attributes as well as the behaviours and capabilities on an enemy actor
     */
    public ForestKeeper() {
        super("Forest Keeper", '8', 125);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(25, "swings at", 75);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int number = random.nextInt(10);
        if(number < 2){
            map.locationOf(this).addItem(new HealingVial());
            builder.append("\n" + name + " dropped a healing Vial" + "\n");
        }
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }

}
