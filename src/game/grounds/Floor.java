package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Vasi Karabourniotis
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }

    /**
     * Only actors that have the a not dead status can enter
     * @param a the Actor to check
     * @return a boolean value if the actor can or cannot enter
     */
    @Override
    public boolean canActorEnter(Actor a) {
        return a.hasCapability(Status.NOT_DEAD);
    }
}
