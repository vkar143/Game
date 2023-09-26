package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;

public interface Trade {

    String buyItems(Item item, Player player);
    String sellItems(Item item, Player player);

}
