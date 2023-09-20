package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumableAction;

/**
 * refreshing flask allows the actor to replenish stamina
 */
public class RefreshingFlask extends Item implements Consumable {
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true);
    }

    /**
     * returns a replinsh action that replenishes the users Stamina
     * @param owner the actor that owns the item
     * @return
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(super.allowableActions(owner));
        actionlist.add(new ConsumableAction(" drink refreshing flask",this));
        return actionlist;
    }

    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 40);
        actor.removeItemFromInventory(this);
    }
}

