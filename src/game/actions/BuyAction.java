package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.general.Ability;
import game.items.BuyableItem;

/**
 * A class that represents an action that can be used to buy an item
 */
public class BuyAction extends Action {
    private final String description;
    private final BuyableItem buyItem;
    private final int buyingAmount;

    /**
     * Constructor used for making an instance of BuyAction which needs
     * a description, a buyable item & its cost
     * @param description A String describing the action of buying
     * @param buyItem An item that can be bought by an Actor
     * @param sellingAmount The amount of money an item costs
     */
    public BuyAction(String description, BuyableItem buyItem, int sellingAmount) {
        this.description = description;
        this.buyItem = buyItem;
        this.buyingAmount = sellingAmount;
    }

    /**
     * This will allow an Actor to buy an item from a trader if the trader can trade
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string explaining the result of buying an item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (map.getActorAt(destination).hasCapability(Ability.CAN_TRADE)) {
                return actor + " " + buyItem.buyItem(actor, buyingAmount);
            }
        }
        return "Trade failed!";
    }

    /**
     * Displays a description of BuyableAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A string summarising the action of BuyableAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + description;
    }
}
