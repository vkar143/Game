package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.general.Ability;
import game.items.Broadsword;
import game.items.HealingVial;
import game.items.RefreshingFlask;

public class Traveller extends Trader {

    /**
     * The constructor of the Actor class.
     */
    public Traveller() {
        super("Traveller", 'ඞ', 10);
        this.addItemToInventory(new HealingVial());
        this.addItemToInventory(new RefreshingFlask());
        this.addItemToInventory(new Broadsword());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

}
