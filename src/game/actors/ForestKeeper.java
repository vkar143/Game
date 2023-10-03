package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehavior;
import game.general.Status;
import game.items.HealingVial;
import game.items.Runes;

import java.util.Random;

public class ForestKeeper extends EnemyActor {
    /**
     * sets the attributes as well as the behaviours and capabilities on an enemy actor
     */
    public ForestKeeper() {
        super("Forest Keeper", '8', 125, 50);
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
            builder.append("\n").append(name).append(" dropped a healing Vial").append("\n");
        }
        map.locationOf(this).addItem(new Runes(this.getRuneAmount()));
        return builder.toString();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            addBehavior(998,new FollowBehavior(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);
    }
}
