package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.GreatKnife;

public class ActivateSkillAction extends Action {
    private Actor target;
    private String direction;
    private ActiveSkill item;
    private String description;

    public ActivateSkillAction(Actor target, String direction, ActiveSkill item, String description){
        this.target = target;
        this.direction = direction;
        this.item = item;
        this.description = description;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.activateSkill(actor, target, direction, description, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + description;
    }
}
