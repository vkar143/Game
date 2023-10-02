package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.general.Ability;
import game.items.SellableItem;

public class SellAction extends Action {
    private final String description;
    private final SellableItem sellItem;
    private final int sellingAmount;

    public SellAction(String description, SellableItem sellItem, int sellingAmount) {
        this.description = description;
        this.sellItem = sellItem;
        this.sellingAmount = sellingAmount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination) && map.getActorAt(destination).hasCapability(Ability.CAN_TRADE)) {
                return actor + " " + sellItem.sellItem(actor, sellingAmount);
            }
        }
        return "Trade failed";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + description;
    }
}
