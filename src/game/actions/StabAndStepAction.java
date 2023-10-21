package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.GreatKnife;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Action for the player to use the "Stab and step" skill with the Great Knife.
 *
 * Created by:
 * @author Phoebe Jiang
 * @version 1.0.0
 * @see Action
 */
public class StabAndStepAction extends Action {
    /**
     * The item that is using this action
     */
    private GreatKnife weaponItem;
    /**
     * The target of this action
     */
    private Actor target;
    /**
     * The direction of the incoming attack.
     */
    private String direction;
    /**
     * Constant representing the stamina decrease from using the stab and step action.
     */
    private final int STAMINA_DECREASE = 50;

    /**
     * Constructor for the Stab and Step Action.
     * @param target the target who will receive the action
     * @param direction the direction the action will be executed
     * @param weapon the weapon to be used to execute the action
     */
    public StabAndStepAction(Actor target, String direction, GreatKnife weapon){
        this.weaponItem = weapon;
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the Great Slam Action by first attacking the target actor with the Great Knife
     * then stepping away.
     * @param actor the actor that will execute the action
     * @param map the map the action will be executed
     * @return returns a string describing the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.getAttribute(BaseActorAttributes.STAMINA) >= STAMINA_DECREASE) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, STAMINA_DECREASE);
            AttackAction stab = new AttackAction(target, direction, weaponItem);
            stab.execute(actor, map);

            ArrayList<Exit> exitList = new ArrayList<>(map.locationOf(actor).getExits());

            Iterator<Exit> iterator = exitList.iterator();
            while (iterator.hasNext()) {
                Exit exit = iterator.next();
                Location surroundingLocation = exit.getDestination();
                if (!surroundingLocation.canActorEnter(actor)) {
                    iterator.remove(); // Remove the current element using the iterator
                }
            }

            Random random = new Random();
            Exit chosenExit = exitList.get(random.nextInt(exitList.size()));
            MoveActorAction step = new MoveActorAction(chosenExit.getDestination(), "safety");
            step.execute(actor, map);

            return "STABBING AND STEPPING";
        }else{
            return "Not enough stamina";
        }
    }

    /**
     * Returns a string of the description of the action
     * @param actor The actor performing the action.
     * @return returns a string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs and steps with " + weaponItem ;
    }
}
