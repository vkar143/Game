package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehavior;
import game.general.Status;
import game.items.HealingVial;
import game.items.Runes;

import java.util.Random;

public class ForestKeeper extends FollowingEnemies {

    /**
     * sets the attributes as well as the behaviours and capabilities on an enemy actor
     */
    public ForestKeeper(Actor target) {
        super("Forest Keeper", '8', 125, target);
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
        map.locationOf(this).addItem(new Runes(50));
        builder.insert(0,super.unconscious(actor, map));
        return builder.toString();
    }

}
