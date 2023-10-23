package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BlacksmithConversation;
import game.actions.Conversation;
import game.actions.ConversationAction;
import game.general.Ability;
import game.general.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blacksmith extends NPC {
    private static final int HIT_POINTS = 10;
    Conversation blacksmithConversation = new BlacksmithConversation();

    /**
     * The constructor of the Actor class.
     */
    public Blacksmith() {
        super("Blacksmith", 'B', HIT_POINTS);
        addCapability(Ability.CAN_UPGRADE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        actionList.add(new ConversationAction("listen to the Blacksmith's monologue", blacksmithConversation));
        return actionList;
    }

}
