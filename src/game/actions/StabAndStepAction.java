package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import game.items.GreatKnife;

import java.util.ArrayList;
import java.util.Random;

public class StabAndStepAction extends Action {
    /**
     * the item that is using this action
     */
    private GreatKnife weaponItem;
    private Actor target;
    private String direction;

    /**
     * sets the weapon
     * @param weapon
     */
    public StabAndStepAction(Actor target, String direction, GreatKnife weapon){
        this.weaponItem = weapon;
        this.target = target;
        this.direction = direction;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 50);
        AttackAction stab = new AttackAction(target, direction, weaponItem);
        stab.execute(actor, map);

        ArrayList<Exit> exitList = new ArrayList<>(map.locationOf(actor).getExits());
        for (Exit exit : exitList) {
            Location surroundingLocation = exit.getDestination();
            if (surroundingLocation.containsAnActor()){
                exitList.remove(exit);
            }
        }

        Random random = new Random();
        Exit chosenExit = exitList.get(random.nextInt(exitList.size()) - 1);
        MoveActorAction step = new MoveActorAction(chosenExit.getDestination(), "safety");
        step.execute(actor, map);

        return "STABBING AND STEPPING";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs and steps with " + weaponItem ;
    }
}
