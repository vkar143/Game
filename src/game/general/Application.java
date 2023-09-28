package game.general;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Traveller;
import game.ground.*;
import game.ground.Graveyard;
import game.ground.Void;
import game.items.*;
import game.spawner.ForestKeeperSpawner;
import game.spawner.HollowSoldierSpawner;
import game.spawner.RedWolfSpawner;
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
        List<String> map3 = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

        GameMap gameMap2 = new GameMap(groundFactory, map2);
        GameMap gameMap3 = new GameMap(groundFactory, map3);
        world.addGameMap(gameMap2);
        world.addGameMap(gameMap3);

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
        gameMap.at(20, 11).setGround(new Graveyard(new WanderingUndeadSpawner(4,1)));
        Gate gate1 = new Gate();
        gate1.addAllowableAction(new MoveActorAction(gameMap2.at(1,1),"to the next level!"));
        Gate gate2 = new Gate();
        gate2.addAllowableAction(new MoveActorAction(gameMap.at(1,1), "Back to level 1"));
        Gate gate3 = new Gate();
        gate3.addAllowableAction(new MoveActorAction(gameMap3.at(1,1), "to the next level!"));
        gameMap.at(28, 6).addItem(gate1);
        gameMap2.at(1,1).addItem(gate3);
        gameMap.at(10, 10).addItem(new BloodBerry());
        gameMap.at(27,6).addItem(new Broadsword());
        Player player = new Player("The Abstracted One", '@', 150);
        player.addItemToInventory(new Broadsword());
        player.addItemToInventory(new BloodBerry());
//        player.addItemToInventory(new RefreshingFlask());
//        player.addItemToInventory(new HealingVial());
        gameMap3.at(30,5).setGround(new Bushes(new RedWolfSpawner(player,3,10)));
        gameMap3.at(20,7).setGround(new Hut(new ForestKeeperSpawner(player,15,100)));
        Traveller traveller = new Traveller();
        world.addPlayer(player, gameMap3.at(20, 5));
        world.addPlayer(traveller, gameMap3.at(20, 3));
        world.run();
    }
}
