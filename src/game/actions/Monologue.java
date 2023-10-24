package game.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monologue {
    private List<String> monologueOptions = new ArrayList<>();

    public Monologue() {}

    public void addMonologue(String monologue) {
        monologueOptions.add(monologue);
    }

    public void removeMonologue(String monologue) {
        monologueOptions.remove(monologue);
    }

    public String getRandomMonologue() {
        Random random = new Random();
        int randomDraw = random.nextInt(monologueOptions.size());
        return monologueOptions.get(randomDraw);
    }
}
