package markkasun_seanfloyd_a1;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class RobotApp {

    /*
     * ARGS
     * 1: Size of grid (one number)
     */

    private static final int DEFAULT_GRID_SIZE = 4, DEFAULT_SEARCH_TYPE = 1;
    private static final HashSet<Coordinate> DEFAULT_OBSTACLE_POSITIONS = new HashSet<Coordinate>();
    private static final HashSet<Coordinate> DEFAULT_DIRT_POSITIONS = new HashSet<Coordinate>();
    private static final Coordinate DEFAULT_ROBOT_POSITION = new Coordinate(0, 0);
    private static final Orientation DEFAULT_ROBOT_ORIENTATION = Orientation.EAST;

    public static void main(String[] args) {
        int gridSize, searchType;
        Set<Coordinate> obstaclePositions;
        Set<Coordinate> dirtPositions;
        Coordinate robotPosition;
        Orientation robotOrientation;

        if (args[0] == null) {
            gridSize = DEFAULT_GRID_SIZE;
            searchType = DEFAULT_SEARCH_TYPE;
            obstaclePositions = DEFAULT_OBSTACLE_POSITIONS;
            dirtPositions = DEFAULT_DIRT_POSITIONS;
            robotPosition = DEFAULT_ROBOT_POSITION;
            robotOrientation = DEFAULT_ROBOT_ORIENTATION;
        } else {
            gridSize = Integer.parseInt(args[0]);
            searchType = Integer.parseInt(args[1]);
        }

        Grid grid = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        ArrayList<String> solution = search(searchType, grid);
        printSolution(solution);
    }

    private static Grid generateGrid(
            int gridSize,
            Set<Coordinate> obstaclePositions,
            Set<Coordinate> dirtPositions,
            Coordinate robotPosition,
            Orientation robotOrientation) {

        return new Grid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
    }

    private static ArrayList<String> search(int searchType, Grid grid) {
        ArrayList<String> solution;
        switch (searchType) {
          case 2:
            solution = breadthFirstSearch(grid);
            break;
          case 3:
            solution = aStarSearch(grid);
            break;
          default:
            solution = depthFirstSearch(grid);
        }
        return solution;
    }

    private static void printSolution(ArrayList<String> solution){
        for (String line : solution) {
            System.out.println(line);
        }
    }

    private static ArrayList<String> depthFirstSearch(Grid grid) {
      return new ArrayList<String>();
    }

    private static ArrayList<String> breadthFirstSearch(Grid grid) {
      return new ArrayList<String>();
    }

    private static ArrayList<String> aStarSearch(Grid grid) {
      return new ArrayList<String>();
    }

    public static class Grid {

        int gridSize;
        Set<Coordinate> obstaclePositions;
        Set<Coordinate> dirtPositions;
        Coordinate robotPosition;
        Orientation robotOrientation;

        public Grid(
                int gridSize,
                Set<Coordinate> obstaclePositions,
                Set<Coordinate> dirtPositions,
                Coordinate robotPosition,
                Orientation robotOrientation) {
            this.gridSize = gridSize;
            this.obstaclePositions = obstaclePositions;
            this.dirtPositions = dirtPositions;
            this.robotPosition = robotPosition;
            this.robotOrientation = robotOrientation;
        }
    }

    public static class Coordinate {
        public final int x;
        public final int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o instanceof Coordinate)
            {
              Coordinate coord = (Coordinate) o;
              if (this.x == coord.x && this.y == coord.y)
                 return true;
            }
            return false;
        }
    }

    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }
}
