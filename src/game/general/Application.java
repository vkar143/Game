package game.general;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.ground.*;
import game.ground.Graveyard;
import game.ground.Void;
import game.items.Broadsword;
import game.items.Gate;
import game.spawner.HollowSoldierSpawner;
import game.spawner.WanderingUndeadSpawner;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class  Application {

    public static void main(String[] args) {
        World world = new World(new Display());
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());
        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());
        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~.....+........###_###..........................",
                "...~~~~~~~~....+...........................................",
                "....~~~~~......+..........................###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);
        List<String> map2 = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        GameMap gameMap2 = new GameMap(groundFactory2, map2);
        world.addGameMap(gameMap2);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        gameMap.at(30, 11).setGround(new Graveyard(new WanderingUndeadSpawner(4,1)));
        gameMap2.at(30,11).setGround(new Graveyard(new HollowSoldierSpawner(1,10)));
        Gate gate1 = new Gate();
        gate1.addAllowableAction(new MoveActorAction(gameMap2.at(1,1),"to the next level!"));
        Gate gate2 = new Gate();
        gate2.addAllowableAction(new MoveActorAction(gameMap.at(1,1), "Back to level 1"));
        gameMap.at(1, 1).addItem(gate1);
        gameMap.at(27,6).addItem(new Broadsword("Broadsword", '1', 110, "swing broadsword", 90));
        Player player = new Player("The Abstracted One", '@', 150);

        world.addPlayer(player, gameMap.at(29, 5));
        world.run();
    }
}
