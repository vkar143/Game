package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.general.Ability;

/**
 * key for the gate
 */
public class Key extends Item {
    /***
     * Constructor which creates an instance of Key with an unlock capability
     */
    public Key() {
        super("Gate Key", '-', true);
        this.capabilitySet.addCapability(Ability.UNLOCK);
    }
}
