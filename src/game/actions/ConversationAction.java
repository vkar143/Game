package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

public class ConversationAction extends Action {
    private final String description;
    private final Conversation conversation;

    public ConversationAction (String description, Conversation conversation) {
        this.description = description;
        this.conversation = conversation;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " " + conversation.conversation(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + description;
    }
}
