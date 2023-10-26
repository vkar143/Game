package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.general.Ability;
import game.general.FancyMessage;
import game.general.Status;
import game.items.*;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Ewan Lumsden-Smith & Phoebe Jiang
 * @see Actor
 */

public class Player extends Actor {
    private Location spawnPoint;
    /**
     * The maximum health of the player
     */
    private final int MAX_HEALTH = 200;
    /**
     * The time the player sleeps/rests for
     */
    private final int MILLIS = 200;
    /**
     * Whether the game can stop or not
     */
    private final int EXIT_STATUS = 0;
    /**
     * The damage inflicted by the player's intrinsic weapon
     */
    private final int INTRINSIC_DAMAGE = 15;
    /**
     * The rate in which the intrinsic weapon is successful in its execution
     */
    private final int INTRINSIC_HIT_RATE = 80;

    /**
     * Constructor.
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hit points
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.WALK_ON_FLOOR);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(MAX_HEALTH));
        this.addBalance(10000);
        this.addItemToInventory(new Broadsword());
        this.addItemToInventory(new HealingVial());
        this.addItemToInventory(new RefreshingFlask());
        this.addItemToInventory(new GreatKnife());
        this.addItemToInventory(new GiantHammer());
    }

    /**
     * simply prints current stats and then returns a menu with all possible actions in it
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns an action taken by the player
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, getAttributeMaximum(BaseActorAttributes.STAMINA)/100);
        display.print(getName()+ "\n");
        display.print(getAttribute(BaseActorAttributes.HEALTH) + "/" + getAttributeMaximum(BaseActorAttributes.HEALTH) + " HP" + "\n");
        display.print(getAttribute(BaseActorAttributes.STAMINA) + "/" + getAttributeMaximum(BaseActorAttributes.STAMINA) + " Stamina" + "\n");
        display.print(getBalance() + " Runes\n");
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        if(!this.isConscious()){
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(MILLIS);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            System.exit(EXIT_STATUS);
        }
        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * returns a string of actor name
     * @return returns a String of the name of the player
     */
    public String getName(){
        return name;
    }

    /**
     * sets the actors intrinsic weapon
     * @return returns the players intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, "Punches",INTRINSIC_HIT_RATE);
    }

    /**
     * when the player dies it removes them from the map and returns the death message
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return returns a string describing the death of the player
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location location = map.locationOf(this);
        location.addItem(new Runes(getBalance()));
        deductBalance(getBalance());
        MoveActorAction respawn = new MoveActorAction(spawnPoint, "Respawning");
        return respawn.execute(this, map);
    }

    /**
     * returns the string for the player
     * @return returns a string describing the player
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
