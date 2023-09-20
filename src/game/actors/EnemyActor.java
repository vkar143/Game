package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.general.Ability;
import game.general.FancyMessage;
import game.general.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehavior;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * abstract class that enemy actors inherit from
 */
public abstract class EnemyActor extends Actor {
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * sets the attributes as well as the behaviours and capabilities on an enemy actor
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    public EnemyActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(998, new AttackBehavior());
        this.capabilitySet.addCapability(Ability.WALK_ON_VOID);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        if(!this.isConscious()){
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            System.exit(0);
        }
        return new DoNothingAction();
    }

    /**
     * The wandering undead can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    /**
     * checks for weapons in the other actors inventory and if there are any weapons it returns an attack action for those weapons
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
        }
        return actions;
    }

    /**
     * drops all of the items that the EnemyActors drop and then removes the actor drom the map
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return
     */

}
