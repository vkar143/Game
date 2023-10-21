# A3 Requirement 1 Design Rationale
## New Classes
- `FollowingEnemy` abstract class that extends the `EnemyActor` abstract class used for the behaviour of enemies that follow a target.
- `LivingTreeBranch` & `EldentreeGuardian` new enemy actors that extend the `EnemyActor`  and `FollowingEnemy` abstract class respectively.
- `TreeBranchSpawner` & `EldenTreeSpawner` new classes for spawning `EldentreeGuardian`s and `LivingTreeBranch`s.

## Modified Classes
- `Spawner` was changed from a interface to an abstract class so that it can hold all of the variables required in the spawning logic without having to repeat code.
- `Gate` the MoveActionAction was changed from a single instance variable to an ArrayList<MoveActorAction> so that a gate can lead to multiple locations.

