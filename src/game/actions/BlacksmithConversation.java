package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import game.general.Ability;
import game.general.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlacksmithConversation implements Conversation {
    @Override
    public String conversation(Actor actor) {
        Random random = new Random();
        List<String> blacksmithMonologues = new ArrayList<>();
        //Create the available monologues
        String monologueRandom1 = "I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.";
        String monologueRandom2 = "It’s dangerous to go alone. Take my creation with you on your adventure!";
        String monologueRandom3 = "Ah, it’s you. Let’s get back to make your weapons stronger.";
        String monologueHasHammer = "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.";
        String monologueAbxNotDefeated = "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!";
        String monologueAbxDefeated = "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.";

        //Add the common monologue lines
        blacksmithMonologues.add(monologueRandom1);
        blacksmithMonologues.add(monologueRandom2);
        blacksmithMonologues.add(monologueRandom3);

        //If actor has a giant hammer add this monologue
        if(actor.hasAttribute(Ability.HAS_GIANT_HAMMER)){
            blacksmithMonologues.add(monologueHasHammer);
        }

        //If actor hasn't defeated the Abxervyer add this monologue
        if(!actor.hasAttribute(Status.ABXERVYER_DEFEATED)){
            blacksmithMonologues.add(monologueAbxNotDefeated);
        }

        //If actor has defeated the Abxervyer add this monologue and remove the not defeated monologue
        if(actor.hasAttribute(Status.ABXERVYER_DEFEATED)){
            blacksmithMonologues.add(monologueAbxDefeated);
            blacksmithMonologues.remove(monologueAbxNotDefeated);
        }

        //return a random monologue from the monologue array list
        int randomDraw = random.nextInt(blacksmithMonologues.size());
        return blacksmithMonologues.get(randomDraw);
    }
}
