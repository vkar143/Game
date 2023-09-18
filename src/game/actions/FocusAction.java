package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;

/**
 * Class representing the Focus Action
 * Created by:
 * @author Vasi Karabourniotis
 */
public class FocusAction extends Action {

    /**
     * Constructor used for making an instance of FocusAction
     */
    public FocusAction(){
    }

    /**
     * This will decrease the actor's stamina by 20% every time focus action is activated
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string summarising the action that was just executed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 40);
        actor.addCapability(Ability.HAS_FOCUS_ACTION);
        return actor + " takes a deep breath and focuses all their might!";
    }

    /**
     * This will display a description of FocusAction to the user through the actor's menu
     * @param actor The actor performing the action.
     * @return A String summarising the action of FocusAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of broadsword";
    }
}
