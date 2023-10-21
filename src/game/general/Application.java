package game.general;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Abxervyer;
import game.actors.Player;
import game.actors.RedWolf;
import game.actors.Traveller;
import game.extended.ForestGameMap;
import game.ground.*;
import game.ground.Graveyard;
import game.ground.Void;
import game.items.*;
import game.spawner.ForestKeeperSpawner;
import game.spawner.HollowSoldierSpawner;
import game.spawner.RedWolfSpawner;
import game.spawner.WanderingUndeadSpawner;

import static game.general.GameMapCollection.abandonedVillageMap;
import static game.general.GameMapCollection.burialGroundMap;

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

        GameMap abandonedVillageGameMap = new GameMap(groundFactory, GameMapCollection.abandonedVillageMap);
        world.addGameMap(abandonedVillageGameMap);

        GameMap burialGroundGameMap = new GameMap(groundFactory, GameMapCollection.burialGroundMap);
        world.addGameMap(burialGroundGameMap);

        ForestGameMap ancientWoodsGameMap = new ForestGameMap(groundFactory, GameMapCollection.ancientWoodsMap);
        world.addGameMap(ancientWoodsGameMap);

        ForestGameMap abxervyerGameMap = new ForestGameMap(groundFactory, GameMapCollection.abxervyerMap);
        world.addGameMap(abxervyerGameMap);

        GameMap overGrownSanctuary = new GameMap(groundFactory, GameMapCollection.overGrownSanctuary);
        world.addGameMap(overGrownSanctuary);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        abandonedVillageGameMap.at(30, 11).setGround(new Graveyard(new WanderingUndeadSpawner(4,1,new Random())));
        abandonedVillageGameMap.at(10, 10).addItem(new BloodBerry());
        abandonedVillageGameMap.at(27,6).addItem(new Broadsword());

        Gate gateToBurialGround = new Gate();
        gateToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1),"to the Burial Grounds!"));
        abandonedVillageGameMap.at(10, 6).setGround(gateToBurialGround);

        Gate gateBackToAbandonedVillage = new Gate();
        gateBackToAbandonedVillage.addAllowableAction(new MoveActorAction(abandonedVillageGameMap.at(1,1), "Back to Abandoned Village"));
        burialGroundGameMap.at(5,7).setGround(gateBackToAbandonedVillage);

        burialGroundGameMap.at(30,11).setGround(new Graveyard(new HollowSoldierSpawner(1,10, new Random())));

        Gate gateToAncientWoods = new Gate();
        gateToAncientWoods.addAllowableAction(new MoveActorAction(ancientWoodsGameMap.at(1,1), "to the Ancient Woods!"));
        burialGroundGameMap.at(1,1).setGround(gateToAncientWoods);

        Gate gateBackToBurialGround = new Gate();
        gateBackToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1), "Back to Burial Grounds"));
        ancientWoodsGameMap.at(10,5).setGround(gateBackToBurialGround);

        Gate gateToAbxervyerRoom = new Gate();
        gateToAbxervyerRoom.addAllowableAction(new MoveActorAction(abxervyerGameMap.at(10,11), "to Abxervyer battle field"));
        ancientWoodsGameMap.at(10,6).setGround(gateBackToBurialGround);

        Player player = new Player("The Abstracted One", '@', 150);

        ancientWoodsGameMap.at(30,5).setGround(new Bush(new RedWolfSpawner(30,100,new Random())));
        ancientWoodsGameMap.at(20,7).setGround(new Hut(new ForestKeeperSpawner(15,100,new Random())));
        ancientWoodsGameMap.at(27,6).addItem(new Runes(0));
        world.addPlayer(player, ancientWoodsGameMap.at(28, 6));
        ancientWoodsGameMap.at(20, 3).addActor(new Traveller());
        abxervyerGameMap.at(1,12).addItem(new GiantHammer());
        abxervyerGameMap.at(7, 8).setGround(new Bush(new RedWolfSpawner(30,100,new Random())) );
        abxervyerGameMap.at(7, 9).setGround(new Bush(new RedWolfSpawner(30,100,new Random())) );
        abxervyerGameMap.at(7, 10).setGround(new Hut(new ForestKeeperSpawner(15,100,new Random())) );
        Abxervyer abxervyer = new Abxervyer();
        abxervyerGameMap.at(8, 11).addActor(abxervyer);
        abxervyer.addWeatherMap(abxervyerGameMap);
        abxervyer.addWeatherMap(ancientWoodsGameMap);
        abxervyer.addBattleGameMap(abxervyerGameMap);
        abxervyer.addLeaveGameMap(ancientWoodsGameMap);

        abxervyerGameMap.at(8, 12).addActor(new RedWolf());
        world.run();
    }
}
