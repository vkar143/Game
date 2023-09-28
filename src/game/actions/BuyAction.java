package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.general.Ability;
import game.general.Status;
import game.items.BuyableItem;

public class BuyAction extends Action {
    private String description;
    private BuyableItem buyItem;

    public BuyAction(String description, BuyableItem buyItem) {
        this.description = description;
        this.buyItem = buyItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination) && map.getActorAt(destination).hasCapability(Ability.CAN_TRADE)) {
                buyItem.buyItem(actor);
            }
        }
        return actor + " " + description;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + description;
    }
}
