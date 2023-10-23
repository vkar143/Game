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

/**
 * A class that represents a trader actor called Traveller
 *
 * Created by:
 * @author Vasi Karabourniotis
 * @version 1.0.0
 * @see Trader
 */
public class Traveller extends Trader {
    /**
     * The amount of hit points the traveller has
     */
    private static final int HIT_POINTS = 10;
    /**
     * The cost of a broadsword
     */
    private final int BROADSWORD_COST = 250;
    /**
     * The cost of a refreshing flask
     */
    private final int REFRESHING_FLASK_COST = 75;
    /**
     * The cost of a healing Vial
     */
    private final int HEALING_VIAL_COST = 100;
    /**
     * The cost of a Great Knife
     */
    private final int GREAT_KNIFE_COST = 300;

    /**
     * A constructor that creates an instance of Traveller
     */
    public Traveller() {
        super("Traveller", 'ඞ', HIT_POINTS);
    }

    /**
     * A list of buyable actions that an actor can choose to buy from the Traveller
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of actions to be executed by the Traveller
     */
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
