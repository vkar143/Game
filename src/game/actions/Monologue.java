package game.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monologue {

    public Monologue() {}

    public void addMonologue(List<String> monologueList, String monologue) {
        monologueList.add(monologue);
    }

    public void removeMonologue(List<String> monologueList, String monologue) {
        monologueList.remove(monologue);
    }

    public String getRandomMonologue(List<String> monologueList) {
        Random random = new Random();
        int randomDraw = random.nextInt(monologueList.size());
        return monologueList.get(randomDraw);
    }
}
