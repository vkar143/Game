package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a wall which cannot be entered by actor
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Vasi Karabourniotis
 */
public class Wall extends Ground {

    public Wall() {
        super('#');
    }

    /**
     * No actor can enter or walk through a Wall
     * @param actor the Actor to check
     * @return Returns a boolean value forbidding any actor to enter a wall
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
