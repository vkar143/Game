package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.HollowSoldier;
import game.actors.Player;
import game.actors.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.items.Broadsword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Vasi Karabourniotis
 */
public class Application {

    /**
     * Initiates the game by displaying the game map, the ground items and the rest of the actors and items included in this game
     * @param args
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void(), new Graveyard());

        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~................................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

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

        GameMap burialGround = new GameMap(groundFactory, burialGroundMap);
        world.addGameMap(burialGround);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Graveyard burialGroundGraveYard = new Graveyard();
        burialGround.at(22,2).setGround(burialGroundGraveYard);

        // this was my attempt to make the spawning of enemies automatic but unfortunately it failed as nothing is displayed on the map
        // and I cannot understand why, I tried to debug and it kept giving me location as a string so I thought that may have been the issue
        // but other methods pass the location as a string as well, maybe I was passing too much
        SpawnEnemy burialGroundSpawnEnemy = new SpawnEnemy(burialGround, 22, 1, 10, new HollowSoldier());
        burialGroundSpawnEnemy.setGround(burialGroundGraveYard);

        HollowSoldier hollowSoldier1 = new HollowSoldier();
        burialGround.at(1,1).addActor(hollowSoldier1);
//        burialGround.at(1,6).addActor(hollowSoldier1);
//        burialGround.at(35,6).addActor(hollowSoldier1);

        HollowSoldier hollowSoldier2 = new HollowSoldier();
        burialGround.at(1,6).addActor(hollowSoldier2);

        HollowSoldier hollowSoldier3 = new HollowSoldier();
        burialGround.at(35,6).addActor(hollowSoldier3);

        Graveyard gameMapGraveYard = new Graveyard();
        gameMap.at(50,1).setGround(gameMapGraveYard);
        gameMap.at(19,11).setGround(gameMapGraveYard);

        WanderingUndead wanderingUndead = new WanderingUndead();
        gameMap.at(23, 10).addActor(wanderingUndead);

        WeaponItem broadsword = new Broadsword();
        gameMap.at(27, 6).addItem(broadsword);

        Player player = new Player("The Abstracted One", '@', 150, 200);
        world.addPlayer(player, gameMap.at(29, 5));

        Void aVoid = new Void();
        gameMap.at(32, 9).setGround(aVoid);
        gameMap.at(33, 9).setGround(aVoid);
        gameMap.at(34, 9).setGround(aVoid);
        gameMap.at(35, 9).setGround(aVoid);
        gameMap.at(33, 10).setGround(aVoid);
        gameMap.at(34, 10).setGround(aVoid);
        gameMap.at(35, 10).setGround(aVoid);

        LockedGate lockedGateForMap = new LockedGate();
        gameMap.at(30, 0).setGround(lockedGateForMap);
        lockedGateForMap.addSampleAction(new MoveActorAction(burialGround.at(22,1), "to Burial Grounds!"));

        LockedGate lockedGateForBurialGround = new LockedGate();
        burialGround.at(22, 0).setGround(lockedGateForBurialGround);
        lockedGateForBurialGround.addSampleAction(new MoveActorAction(gameMap.at(22,1), "to map!"));

        world.run();
    }
}
