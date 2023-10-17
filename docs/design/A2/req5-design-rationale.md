# refactor made to current code
- spawnEnemy is modified to spawn actor because I believe it suits the purpose of Spawner better. What type of Actor a Ground will produce depends on the Spawner it receives and there is no reason to assume SpawningGround only spawns Enemy.
- Spawner refactored from interface to abstract class because spawning actors is what Spawners are supposed to do. Inheritance can reduce code repeatition.
- odds and bound of spawning should always be its original to make spawn rate multilier work properly.
# class(s) responsibility
- `WeatherController`: a class that keep track of weather and provide main API for Actor to manipulate weather.
- `AncientWoodWeatherController`: implementation of WeatherController.
- `Weather`: provides API for abxervyer to  manipulate weather.
- `SunnyForestWeather`: handles concrete implementation of manipulations to be done.
- `ForestGameMap`: an extension to `GameMap` that allows to look up for certain SpawningGround and EnemyActor and modify certain attributes accordingly.
# my design choice
## Weather
I assume a boss will always stay in one battlefield, and weather will only affect limited game maps. Different game maps may have different weather effects combinations. Since GameMap does not provide API essential for certain implementations, e.g. return ArrayList of locations stored in a game map for other client to look up certain Grounds that are affected by weather(in order to implement modifySpawnRate()), I have to implement GameMap extensions to provide necessary methods to achieve weather effects. 
# extensibility
## Weather Effect Functionality
Different GameMap extensions may have different weather effects combinations and implemented accordingly. 
## potential hacky method & justification
- casting is used in 
    - ForestGameMap.modifySpawnRate(), because I need to use spawnGround.updateSpawnRateMultiplier(multiplier) which is a method implemented in SpawningGround(which is one responsibility of SpawningGround, and We are not allowed to modify engine code anyway)