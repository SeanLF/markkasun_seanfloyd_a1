Mark Kasun - 3805554
Sean Floyd - 6778524

# CSI 4106 - Assignment 1 Report

## General questions
1. How do you represent a state in this problem?

  A state in this problem contains the current dirt positions, the current robot orientation, and the current position of the robot.

2. What is the initial state, goal state and path cost in this problem?

  The initial state would have `n` dirty tiles and have the robot positioned and oriented on any tile (not occupied by an obstacle).
  The goal state is reached when there are no dirty tiles.
  The path cost is the cumulative step cost to move and orient the robot, and suck dirt, until we reach the goal state.

3. What is the branching factor in this problem?

  The branching factor is the maximum number of successors of any node, so the maximum number of possible actions from a node. Possible actions are suck, move, left and right.

  Therefore, the branching factor is 4.

4. What is the proposed heuristic? Justify your choice.

  We can relax the problem to the following: there are no obstacles and all dirty tiles are grouped together so that we can move to the next tile and vacuum the dirt. Additionally, the path to closest dirty tile is the manhattan distance to that tile from the robot position.

  We can formulate that as the sum of:
  - (cost of moving + cost of sucking) * (instances of dirt remaining - 1)
  - (manhattan distance from the robot to the closest dirt) * (cost of moving) + cost of sucking)

  This results in the following formula:
  ```java
  h = 60*(dirt_remaining - 1) + manhattan_distance(robot, closest_dirt) * 50 + 10
  ```

5. Describe the environment properties. Justify your answers.

  - Fully observable: we have access to the complete state of the environment at each point in time (dirty tiles, robot position and orientation).
  - Single agent: there is only one robot in the problem.
  - Deterministic: the next state is completely determined by the current state and action executed by the agent.
  - Sequential: the current decision affects all future decisions.
  - Static: the environment cannot change while the robot is deliberating on its next action.
  - Discrete: there are a finite number of distinct states, there is also a discrete set of percepts and actions.


## Implementation questions
1. How do you represent the room/grid?

  The grid is represented by the following:
    - an integer to represent the grid size (we are told the grid is square)
    - a (custom class) RobotState object which contains the positions of the robot and dirty tiles as well as the orientation of the robot.
    - a Set of Coordinate to represent the list of obstacle coordinates.

2. How do you represent the dirt and obstacles?

  We created a coordinate object which contains an (x,y) pair. The dirt and obstacles are both represented by distinct HashSets of Coordinate.

3. How do you represent a state in your implementation? (note that this is different from the Q1 in general questions)

  The RobotState class models the state. It contains:
    - a variable for the dirt positions (HashSet of Coordinate)
    - a variable of type Coordinate to represent the robot's coordinates.
    - an enum that represents the robot orientation.

## Code description
 > Provide a brief description of your implementation and the classes in your various packages.

Our implementation focuses heavily on being generalizable and following good object-oriented practices. The three algorithms only reference interface or abstract classes which can be implemented in different ways for different problems. The interfaces we created are State and Problem which are used in the algorithms; these interfaces implemented in RobotState and RobotProblem respectively. An abstract class was used for Node which is used in the algorithms and extended to RobotNode for the specific implementation.

We created other classes to facilitate with the implementation of this specific problem. The Grid object stores all the information about the grid. It uses a RobotState object to represent the information that would be stored in the initial state. We also created a Coordinate object to represent robot positions, obstacle positions, and dirt positions.

The algorithms are all implemented within the Search class and used within the RobotApp.

## Comparative analysis
> Make a comparative table describing, for each search method (BFS, DFS, A<sup>\*</sup>), the depth, path cost, the time taken, and the worst time and space complexity. What is your conclusion?

We used the example grid provided in the assignment instructions for our tests and analysis.

|Method|  Depth|   Path Cost|   Time|    Worst Time|  Worst Space|
|:--|:--|:--|:--|:--|:--|
|DFS| 20|  660| 4 ms|    O(b<sup>m</sup>)|  O(m*b)|
|BFS| 16|  520| 25 ms|   O(b<sup>d</sup>)|  O(b<sup>d</sup>)|
|A<sup>\*</sup>|  16|  520| 662 ms|  O(b<sup>d</sup>)|  O(b<sup>d</sup>)|

where `b` = breadth, `m` = max depth, `d` = depth of goal

For most robot problems, BFS would be the go to algorithm of the three. It produces a good solution and in good time, even when the grid size is set to be fairly large. DFS would only be useful if working on an extremely large robot problem where memory becomes an issue, but such a robot problem would have to be huge with available memory in an average computer. A<sup>\*</sup> takes a significant amount of time to run since it uses tree search and not graph search. It does, however, guarantee the optimal solution so it would be useful if such a guarantee was needed. BFS guarantees the shortest path but does not take into account action cost. A small example where it fails to get the best solution is {Grid Size: 3, No obstacles, Dirt Positions: {(3,2), (1,3)}, Robot Position: (2,2), Robot Orientation: West}. However, BFS is likely to get very close to the optimal solution and runs significantly faster than A<sup>\*</sup> for Robot Problems.
