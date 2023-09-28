package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.Broadsword;
import game.items.HealingVial;
import game.items.RefreshingFlask;

public class Traveller extends Trader {

    /**
     * The constructor of the Actor class.
     */
    public Traveller() {
        super("Traveller", 'ඞ', 10);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        actionList.add(new BuyAction("buys Broadsword", new Broadsword(), 250));
        actionList.add(new BuyAction("buys Refreshing Flask", new RefreshingFlask(), 75));
        actionList.add(new BuyAction("buys Healing Vial", new HealingVial(), 100));
        return actionList;
    }

}
