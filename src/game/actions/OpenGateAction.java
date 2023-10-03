package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Gate;

public class OpenGateAction extends Action {
    private final Gate gate;
    public OpenGateAction(Gate gate){
        this.gate = gate;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        gate.Unlock();
        return actor + " opens the gate!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the gate!";
    }
}
