package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.general.Ability;
import game.general.FancyMessage;
import game.general.Status;
import game.items.Base;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.WALK_ON_FLOOR);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));
        addItemToInventory(new Base());
    }


    /**
     * simply prints current stats and then returns a menu with all possible actions in it
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
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
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            System.exit(0);
        }
        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * returns a string of actor name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * sets the actors intrisic weapon
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "Punches",80);
    }

    /**
     * when the player dies it removes them from the map and returns the death message
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        map.removeActor(this);
        StringBuilder builder = new StringBuilder(this + " met their demise at the hands of " + actor + "\n\n");
        builder.append(FancyMessage.YOU_DIED);
        return builder.toString();
    }

    /**
     * when the player dies it removes them from the map and returns the death message
     * @param map where the actor fell unconscious
     * @return
     */
//  Currently not in use
    @Override
    public String unconscious(GameMap map) {
        map.removeActor(this);
        StringBuilder builder = new StringBuilder(this + " ceased to exist.\n\n");
        builder.append(FancyMessage.YOU_DIED);
        return builder.toString();
    }

    /**
     * returns the string for the player
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
