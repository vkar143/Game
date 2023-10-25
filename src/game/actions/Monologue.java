package game.actions;

import java.util.List;
import java.util.Random;

/**
 * A wrapper concrete class which deals with adding, removing and randomly choosing a monologue
 *
 * Created by:
 * @author Vasi Karabourniotis
 * @version 1.0.0
 */
public class Monologue {
    /**
     * Constructor used for making an instance of Monologue
     */
    public Monologue() {}

    /**
     * This adds a monologue string to a List<String> Array List
     * @param monologueList A list of possible monologue options to be said by an actor
     * @param monologue A monologue string to be added to the array list
     */
    public void addMonologue(List<String> monologueList, String monologue) {
        monologueList.add(monologue);
    }

    /**
     * This removes a monologue string from a List<String> Array List
     * @param monologueList A list of possible monologue options to be said by an actor
     * @param monologue A monologue string to be added to the array list
     */
    public void removeMonologue(List<String> monologueList, String monologue) {
        monologueList.remove(monologue);
    }

    /**
     * This randomly chooses one of the string options from the array list
     * @param monologueList A list of possible monologue options to be said by an actor
     * @return A randomly chosen monologue string
     */
    public String getRandomMonologue(List<String> monologueList) {
        Random random = new Random();
        int randomDraw = random.nextInt(monologueList.size());
        return monologueList.get(randomDraw);
    }
}
