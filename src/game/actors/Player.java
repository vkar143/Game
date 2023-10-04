package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.general.Ability;
import game.general.FancyMessage;
import game.general.Status;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    private final int MAX_HEALTH = 200;
    private final int MILLIS = 200;
    private final int EXIT_STATUS = 0;
    private final int INTRINSIC_DAMAGE = 15;
    private final int INTRINSIC_HIT_RATE = 80;


    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hit points
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.WALK_ON_FLOOR);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(MAX_HEALTH));
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
     * @return returns a string descibring the death of the player
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        map.removeActor(this);
        return this + " met their demise at the hands of " + actor + "\n\n" + FancyMessage.YOU_DIED;
    }

    /**
     * when the player dies it removes them from the map and returns the death message
     * @param map where the actor fell unconscious
     * @return returns a string describing the death of the player
     */
//  Currently not in use
    @Override
    public String unconscious(GameMap map) {
        map.removeActor(this);
        return this + " ceased to exist.\n\n" + FancyMessage.YOU_DIED;
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
