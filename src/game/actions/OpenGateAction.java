package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Gate;

/**
 * Action for the player to use the "Great Slam" skill with the Giant Hammer.
 *
 * Created by:
 * @author Ewan Lumsden-Smith
 * @version 1.0.0
 * @see Action
 */
public class OpenGateAction extends Action {
    /**
     * The Gate item is used as a variable
     */
    private final Gate gate;

    /**
     * Action to open a gate to enter a different map
     * @param gate the gate item to be unlocked
     */
    public OpenGateAction(Gate gate){
        this.gate = gate;
    }

    /**
     * Action to execute to unlock the gate
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string description verifying the date is open
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        gate.Unlock();
        return actor + " opens the gate!";
    }

    /**
     * Description to be viewed by the user in the menu description
     * @param actor The actor performing the action.
     * @return A string description of the action to execute
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the gate!";
    }
}
