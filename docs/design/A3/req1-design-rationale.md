# A3 Requirement 1 Design Rationale
## New Classes
- `ForestEnemy` abstract class that extends the `EnemyActor` abstract class used for the behaviour of enemies that follow a target.
- `LivingTreeBranch` & `EldentreeGuardian` new enemy actors that extend the `EnemyActor`  and `FollowingEnemy` abstract class respectively.
- `TreeBranchSpawner` & `EldenTreeSpawner` new classes for spawning `EldentreeGuardian`s and `LivingTreeBranch`s.
- `Droppable` interface, is implemented for game entities that can drop things, it contains a single drop method.

## Modified Classes
- `Spawner` was changed from an interface to an abstract class so that it can hold all the variables required in the spawning logic without having to repeat code.
- `Gate` the MoveActionAction was changed from a single instance variable to an ArrayList<MoveActorAction> so that a gate can lead to multiple locations.
- In the `EnemyActor` abstract class a drop method was added for dropping items after the enemy dies, this adds a layer of abstraction leading to less repeated code.

### Pros
- The `Droppable` interface satisfies a number of design principles, the Open & Closed Principle (OCP), this design is open for extension as any new entity that has a drop responsibility can implement this interface, and it's closed for modification as you don't need to change the interface everytime you add a new entity with a drop responsibility
- Creating new classes for the `EldentreeGuardian` and `LivingTreeBranch` satisfies the Single Responsibility Principle (SRP) as each new class defines the contract for each specific enemy actor.
- Adding a new abstract class `ForestEnemy` that contains the logic for enemies that follow the player adheres to DRY as there is less repeated code for each of these enemies that use this logic.

### Potential bad design decisions (cons)
- The added abstract class `ForestEnemy` could be determined as an excess considering it holds just a couple lines of logic. However, I think it aligns well with the principle of DRY, it makes the code more extendable, and it is used by many different types of enemies.
- Having multiple classes for the `Spawner` when you could have one class that takes a different actor to instantiate with each instance. I think this would create an over reliance on the Application class as well as making the code less extendable as you may want to adjust the spawn logic for different `Spawner` child classes in the future.

### Alternative Designs
- The