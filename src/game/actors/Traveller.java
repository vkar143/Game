package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.Broadsword;
import game.items.GreatKnife;
import game.items.HealingVial;
import game.items.RefreshingFlask;

public class Traveller extends Trader {
    private static final int HIT_POINTS = 10;
    private final int BROADSWORD_COST = 250;
    private final int REFRESHING_FLASK_COST = 75;
    private final int HEALING_VIAL_COST = 100;
    private final int GREAT_KNIFE_COST = 300;

    /**
     * The constructor of the Actor class.
     */
    public Traveller() {
        super("Traveller", 'ඞ', HIT_POINTS);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        actionList.add(new BuyAction("buys Broadsword", new Broadsword(), BROADSWORD_COST));
        actionList.add(new BuyAction("buys Refreshing Flask", new RefreshingFlask(), REFRESHING_FLASK_COST));
        actionList.add(new BuyAction("buys Healing Vial", new HealingVial(), HEALING_VIAL_COST));
        actionList.add(new BuyAction("buys Great Knife", new GreatKnife(), GREAT_KNIFE_COST));
        return actionList;
    }

}
