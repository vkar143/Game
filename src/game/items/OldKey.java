package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class that represents an Old Key
 * Created by:
 * @author Vasi Karabourniotis
 */
public class OldKey extends Item {

    /**
     * Constructor which shows the OldKey's name, character and if it can be picked up and put down
     */
    public OldKey() {
        super("Old Key", '-', true);
    }
}
