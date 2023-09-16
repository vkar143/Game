package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeVialAction;

/**
 * A class that represents a Healing Vial.
 * Created by:
 * @author Vasi Karabourniotis
 */
public class HealingVial extends Item {
    /**
     * Constructor which shows the healingVial's name, character and if it can be picked up and put down
     */
    public HealingVial(){
        super("Healing VIal", 'a', true);
    }

    /**
     * List of allowable actions that the item can perform to the current actor
     * Allows actor to increase their health
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeVialAction());
        return actions;
    }

}
