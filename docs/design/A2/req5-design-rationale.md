# refactor made to current code
- spawnEnemy is modified to spawn actor because I believe it suits the purpose of Spawner better. What type of Actor a Ground will produce depends on the Spawner it receives and there is no reason to assume SpawningGround only spawns Enemy.
- Spawner refactored from interface to abstract class because spawning actors is what Spawners are supposed to do. Inheritance can reduce code repeatition.
# class(s) responsibility
- `WeatherController`: a class that keep track of weather and provide main API for Actor to manipulate weather.
- `AncientWoodWeatherController`: implementation of WeatherController.
- `Weather`: 
- `ForestGameMap`: an extension to `GameMap` that allows to look up for certain SpawningGround and EnemyActor and modify certain attributes accordingly.