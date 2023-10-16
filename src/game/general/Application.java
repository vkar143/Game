package game.general;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.ForestGameMap;
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
 * @author Phoebe Jiang
 * @author Vasiliki Karabourniotis
 * @author Ewan Lumsden Smith
 * @version 2.0.0
 */
public class  Application {
    /**
     * The main entry point for the game as well as starting its execution.
     * This method sets up the game world, including game maps, actors, items, and various game elements.
     *
     * @param args Command-line arguments.
     */

    public static void main(String[] args) {
        World world = new World(new Display());
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());
        List<String> abandonedVillageMap = Arrays.asList(
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

        GameMap abandonedVillageGameMap = new GameMap(groundFactory, abandonedVillageMap);
        world.addGameMap(abandonedVillageGameMap);

        List<String> burialGroundMap = Arrays.asList(
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
        GameMap burialGroundGameMap = new GameMap(groundFactory, burialGroundMap);
        world.addGameMap(burialGroundGameMap);

        List<String> ancientWoodsMap = Arrays.asList(
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
        GameMap ancientWoodsGameMap = new GameMap(groundFactory, ancientWoodsMap);
        world.addGameMap(ancientWoodsGameMap);

        List<String> abxervyerMap = Arrays.asList(
                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~");
        ForestGameMap abxervyerGameMap = new ForestGameMap(groundFactory, abxervyerMap);
        world.addGameMap(abxervyerGameMap);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        abandonedVillageGameMap.at(30, 11).setGround(new Graveyard(new WanderingUndeadSpawner(4,1)));
        abandonedVillageGameMap.at(10, 10).addItem(new BloodBerry());
        abandonedVillageGameMap.at(27,6).addItem(new Broadsword());

        Gate gateToBurialGround = new Gate();
        gateToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1),"to the Burial Grounds!"));
        abandonedVillageGameMap.at(10, 6).setGround(gateToBurialGround);

        Gate gateBackToAbandonedVillage = new Gate();
        gateBackToAbandonedVillage.addAllowableAction(new MoveActorAction(abandonedVillageGameMap.at(1,1), "Back to Abandoned Village"));
        burialGroundGameMap.at(5,7).setGround(gateBackToAbandonedVillage);

        burialGroundGameMap.at(30,11).setGround(new Graveyard(new HollowSoldierSpawner(1,10)));

        Gate gateToAncientWoods = new Gate();
        gateToAncientWoods.addAllowableAction(new MoveActorAction(ancientWoodsGameMap.at(1,1), "to the Ancient Woods!"));
        burialGroundGameMap.at(1,1).setGround(gateToAncientWoods);

        Gate gateBackToBurialGround = new Gate();
        gateBackToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1), "Back to Burial Grounds"));
        ancientWoodsGameMap.at(10,5).setGround(gateBackToBurialGround);

        Player player = new Player("The Abstracted One", '@', 150);

        ancientWoodsGameMap.at(30,5).setGround(new Bush(new RedWolfSpawner(3,10)));
        ancientWoodsGameMap.at(20,7).setGround(new Hut(new ForestKeeperSpawner(15,100)));

        world.addPlayer(player, abxervyerGameMap.at(28, 5));
        ancientWoodsGameMap.at(20, 3).addActor(new Traveller());
        abxervyerGameMap.at(1,12).addItem(new GiantHammer());
        abxervyerGameMap.at(7, 8).setGround(new Bush(new RedWolfSpawner(3,10)) );
        abxervyerGameMap.at(7, 9).setGround(new Bush(new RedWolfSpawner(3,10)) );

        world.run();
    }
}
