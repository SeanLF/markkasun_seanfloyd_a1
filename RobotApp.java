package markkasun_seanfloyd_a1;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class RobotApp {

    public enum Orientation { NORTH, EAST, SOUTH, WEST }

    /* ----- BEGIN -------
    Modify these to modify the grid parameters */
    private static final int GRID_SIZE = 4,
                             SEARCH_TYPE = 2;
    private static final Coordinate INITIAL_ROBOT_POSITION = new Coordinate(4, 3);
    private static final Orientation INITIAL_ROBOT_ORIENTATION = Orientation.WEST;

    private static HashSet<Coordinate> generateObstaclePositions() {
        HashSet<Coordinate> result = new HashSet<Coordinate>();
        result.add(new Coordinate(2,2));
        result.add(new Coordinate(2,3));
        result.add(new Coordinate(3,2));

        return result;
    }

    private static HashSet<Coordinate> generateDirtPositions() {
        HashSet<Coordinate> result = new HashSet<Coordinate>();
        result.add(new Coordinate(2,1));
        result.add(new Coordinate(1,2));
        result.add(new Coordinate(2,4));
        result.add(new Coordinate(3,3));

        return result;
    }
    /*------ END ------*/

    public static void main(String[] args) {
        int gridSize = GRID_SIZE,
            searchType = SEARCH_TYPE;
        Set<Coordinate> obstaclePositions = generateObstaclePositions();
        HashSet<Coordinate> dirtPositions = generateDirtPositions();
        Coordinate robotPosition = INITIAL_ROBOT_POSITION;
        Orientation robotOrientation = INITIAL_ROBOT_ORIENTATION;

        Grid grid = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        RobotProblem problem = new RobotProblem(grid);
        long startTime = System.currentTimeMillis();
        Node solution = search(searchType, problem);
        long endTime = System.currentTimeMillis();
        printSolution(solution);
        System.out.println("Time : " + (endTime - startTime) + " ms");
    }

    private static Grid generateGrid(int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        return new Grid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
    }

    private static Node search(int searchType, Problem problem) {
        switch (searchType) {
          default:
          case 1: return Search.depthFirstSearch(problem);
          case 2: return Search.breadthFirstSearch(problem);
          case 3: return Search.aStarSearch(problem);
        }
    }

    private static void printSolution(Node solution){
        List<String> solutionString = RobotProblem.generateSolutionString(solution);

        for (String line : solutionString) {
            System.out.println(line);
        }
    }
}
