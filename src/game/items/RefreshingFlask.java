package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeFlaskAction;


/**
 * A class that represents a Refreshing Vial.
 * Created by:
 * @author Vasi Karabourniotis
 */
public class RefreshingFlask extends Item {
    /***
     * Constructor which shows the OldKey's name, character and if it can be picked up and put down
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
    }
    /**
     * List of allowable actions that the item can perform to the current actor
     * c
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeFlaskAction());
        return actions;
    }
}
