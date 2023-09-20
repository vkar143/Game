package game.ground;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumableAction;
import game.items.Consumable;

import java.util.List;

/**
 * a cosmetic piece of ground
 */
public class Puddle extends Ground implements Consumable {

    @Override
    public void consume(Actor actor) {
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, actor.getAttributeMaximum(BaseActorAttributes.STAMINA)/100);
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionlist = super.allowableActions(actor, location, direction);
        if(location.containsAnActor()){
            actionlist.add(new ConsumableAction("drinks from puddle",this));
        }
        return actionlist;
    }

    public Puddle() {
        super('~');
    }

}
