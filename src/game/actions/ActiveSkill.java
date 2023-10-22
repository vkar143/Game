package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface ActiveSkill {
    public String activateSkill(Actor user, Actor target, String direction, String description, GameMap map);
}
