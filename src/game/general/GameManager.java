package game.general;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Abxervyer;
import game.actors.Blacksmith;
import game.actors.Player;
import game.actors.Traveller;
import game.extended.ForestGameMap;
import game.ground.*;
import game.ground.Void;
import game.spawner.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    private final Player player;
    private final World world;
    private final ArrayList<GameMap> gameMaps = new ArrayList<>();
    private final ArrayList<ForestGameMap> forestMaps = new ArrayList<>();
    private final GroundFactory groundFactory;

    public GameManager(Player player, World world) {
        this.player = player;
        this.world = world;
        this.groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Puddle(), new Void());

    }

    public void addForestMap(ForestGameMap map) {
        forestMaps.add(map);
        world.addGameMap(map);
    }

    public void addMap(GameMap map) {
        this.gameMaps.add(map);
        world.addGameMap(map);
    }

    public void initialiseGame() {
        GameMap abandonedVillageGameMap = new GameMap(groundFactory, GameMapCollection.abandonedVillageMap);
        addMap(abandonedVillageGameMap);

        GameMap burialGroundGameMap = new GameMap(groundFactory, GameMapCollection.burialGroundMap);
        addMap(burialGroundGameMap);

        ForestGameMap ancientWoodsGameMap = new ForestGameMap(groundFactory, GameMapCollection.ancientWoodsMap);
        addForestMap(ancientWoodsGameMap);

        ForestGameMap abxervyerGameMap = new ForestGameMap(groundFactory, GameMapCollection.abxervyerMap);
        addForestMap(abxervyerGameMap);

        GameMap overGrownSanctuary = new GameMap(groundFactory, GameMapCollection.overGrownSanctuary);
        addMap(overGrownSanctuary);
        setActors();
        setGrounds();
    }

    public void setActors() {
        GameMap abandonedVillageGameMap = gameMaps.get(0);
        GameMap burialGroundMap = gameMaps.get(1);
        GameMap overGrownSanctuary = gameMaps.get(2);
        ForestGameMap ancientWoodsGameMap = forestMaps.get(0);
        ForestGameMap abxervyerGameMap = forestMaps.get(1);

        Gate defeatedAbxervyerGate = new Gate();
        defeatedAbxervyerGate.addAllowableAction(new MoveActorAction(ancientWoodsGameMap.at(1, 1), "to the Ancient Woods!"));
        defeatedAbxervyerGate.addAllowableAction(new MoveActorAction(overGrownSanctuary.at(1, 1), "to the overGrown Sanctuary"));
        Abxervyer abxervyer = new Abxervyer(defeatedAbxervyerGate);
        abxervyerGameMap.at(8, 11).addActor(abxervyer);
        abxervyer.addWeatherMap(abxervyerGameMap);
        abxervyer.addWeatherMap(ancientWoodsGameMap);
        abxervyer.addBattleGameMap(abxervyerGameMap);
        world.addPlayer(player, abandonedVillageGameMap.at(28, 6));
        abxervyerGameMap.at(28, 7).addActor(new Blacksmith());
        ancientWoodsGameMap.at(20, 3).addActor(new Traveller());
    }

    public void setGrounds() {
        GameMap abandonedVillageGameMap = gameMaps.get(0);
        GameMap burialGroundGameMap = gameMaps.get(1);
        GameMap overGrownSanctuary = gameMaps.get(2);
        ForestGameMap ancientWoodsGameMap = forestMaps.get(0);
        ForestGameMap abxervyerGameMap = forestMaps.get(1);
        overGrownSanctuary.at(7,5).setGround(new Graveyard(new HollowSoldierSpawner(0.1f, new Random())));
        overGrownSanctuary.at(2,2).setGround(new Bush(new LivingTreeBranchSpawner(0.9f, new Random())));
        overGrownSanctuary.at(6,6).setGround(new Hut(new EldenTreeSpawner(0.2f, new Random())));
        abxervyerGameMap.at(7, 8).setGround(new Bush(new RedWolfSpawner(0.3f,new Random())) );
        abxervyerGameMap.at(7, 9).setGround(new Bush(new RedWolfSpawner(0.3f,new Random())) );
        abxervyerGameMap.at(7, 10).setGround(new Hut(new ForestKeeperSpawner(0.15f,new Random())));

        ancientWoodsGameMap.at(30,5).setGround(new Bush(new RedWolfSpawner(0.3f,new Random())));
        ancientWoodsGameMap.at(20,7).setGround(new Hut(new ForestKeeperSpawner(0.15f,new Random())));
        Gate gateToBurialGround = new Gate();
        gateToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1),"to the Burial Grounds!"));
        abandonedVillageGameMap.at(10, 6).setGround(gateToBurialGround);

        Gate gateBackToAbandonedVillage = new Gate();
        gateBackToAbandonedVillage.addAllowableAction(new MoveActorAction(abandonedVillageGameMap.at(1,1), "Back to Abandoned Village"));
        burialGroundGameMap.at(5,7).setGround(gateBackToAbandonedVillage);

        burialGroundGameMap.at(30,11).setGround(new Graveyard(new HollowSoldierSpawner(0.1f, new Random())));

        abandonedVillageGameMap.at(30, 11).setGround(new Graveyard(new WanderingUndeadSpawner(0.25f, new Random())));

        Gate gateToAncientWoods = new Gate();
        gateToAncientWoods.addAllowableAction(new MoveActorAction(ancientWoodsGameMap.at(1,1), "to the Ancient Woods!"));
        burialGroundGameMap.at(1,1).setGround(gateToAncientWoods);

        Gate gateBackToBurialGround = new Gate();
        gateBackToBurialGround.addAllowableAction(new MoveActorAction(burialGroundGameMap.at(1,1), "Back to Burial Grounds"));
        ancientWoodsGameMap.at(10,5).setGround(gateBackToBurialGround);

        Gate gateToAbxervyerRoom = new Gate();
        gateToAbxervyerRoom.addAllowableAction(new MoveActorAction(abxervyerGameMap.at(10,11), "to Abxervyer battle field"));
        ancientWoodsGameMap.at(10,6).setGround(gateBackToBurialGround);
    }

    public void clear(GameMap map) {
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                Location currentLocation = map.at(x, y);
                if (currentLocation.getActor() != null && !(currentLocation.getActor().hasCapability(Status.NOT_AFFECTED_BY_RESPAWN))) {
                    map.removeActor(currentLocation.getActor());
                }

                List<Item> itemsToRemove = new ArrayList<>();
                for (Item item : currentLocation.getItems()) {
                    if (!(item.hasCapability(Status.NOT_AFFECTED_BY_RESPAWN))) {
                        itemsToRemove.add(item);
                    }
                }
                for (Item item : itemsToRemove) {
                    currentLocation.removeItem(item);
                }
            }
        }
    }

    public void clearMaps() {
        for (GameMap map : gameMaps) {
            clear(map);
        }
        for (ForestGameMap map : forestMaps) {
            clear(map);
        }
    }
    public void respawn(){
        clearMaps();
        setGrounds();
        setActors();
    }
}
