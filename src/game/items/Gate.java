package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.general.Ability;

/**
 * transports the actor between levels if they have the key
 */
public class Gate extends Item{
    private Action action;
    /***
     * Constructor.
     */
    public Gate() {
        super("Gate", '=', false);

    }

    /**
     * allows you to add a movement action
     * @param action action added.
     */
    public void addAllowableAction(Action action){
        this.action = action;
    }

    /**
     * checks to see if the actor has a key with the unlock ability if so it returns a Move action
     * @param location the location of the ground on which the item lies
     * @return returns a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        if(location.getActor() == null){
            return actions;
        }else{
            for(Item item: location.getActor().getItemInventory()){
                if(item.hasCapability(Ability.UNLOCK)){
                    actions.add(action);
                }
            }
        }
        return actions;

    }
}

