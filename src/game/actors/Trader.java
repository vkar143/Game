package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.general.Ability;

public abstract class Trader extends Actor {
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.CAN_TRADE);
    }
}
