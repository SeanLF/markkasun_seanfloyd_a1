package markkasun_seanfloyd_a1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class RobotApp {

    public enum Orientation { NORTH, EAST, SOUTH, WEST }
    public enum Actions { SUCK, MOVE, RIGHT, LEFT }
    public static final Actions[] actionArray = {Actions.SUCK, Actions.MOVE, Actions.RIGHT, Actions.LEFT};

    /* ----- BEGIN -------
    Modify these to modify the grid parameters */
    private static final int GRID_SIZE = 4,
                             SEARCH_TYPE = 1;
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

        State grid = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        ArrayList<String> solution = search(searchType, grid);
        printSolution(solution);
    }

    private static State generateGrid(
            int gridSize,
            Set<Coordinate> obstaclePositions,
            HashSet<Coordinate> dirtPositions,
            Coordinate robotPosition,
            Orientation robotOrientation) {
        //TODO: Refactor Grid into state with parent and remove parent from grid
        return new State(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
    }

    private static ArrayList<String> search(int searchType, State initialState) {
        ArrayList<String> solution;
        switch (searchType) {
          case 2:
            solution = breadthFirstSearch(initialState);
            break;
          case 3:
            solution = aStarSearch(initialState);
            break;
          default:
            solution = depthFirstSearch(initialState);
        }
        return solution;
    }

    private static void printSolution(ArrayList<String> solution){
        for (String line : solution) {
            System.out.println(line);
        }
    }

    private static ArrayList<String> depthFirstSearch(State initialState) {
      return new ArrayList<String>();
    }

    private static ArrayList<String> breadthFirstSearch(State initialState) {
        ArrayList<State> explored = new ArrayList<State>();
        Queue<State> frontier = new LinkedList<State>();
        frontier.add(initialState);
        State node;

        while (true) {
            if (frontier.isEmpty()) break;
            node = frontier.poll();
            explored.add(node);
            for (Actions action : actionArray) {
                if (!isActionPossible(node, action)) continue;
                State child = generateChildNode(node, action);
                if (!frontier.contains(child) && !explored.contains(child)) {
                    if (child.dirtPositions.isEmpty()) return null; //TODO: Must return valid solution shite
                    frontier.add(child);
                }
            }
        }

      return new ArrayList<String>();
    }

    private static ArrayList<String> aStarSearch(State initialState) {
      return new ArrayList<String>();
    }

    private static boolean isActionPossible(State node, Actions action) {
        switch (action) {
        case SUCK:
            return node.dirtPositions.contains(node.robotPosition);
        case MOVE:
            Coordinate targetNodeCoord = null;
            switch (node.robotOrientation) {
            case NORTH: targetNodeCoord = new Coordinate(node.robotPosition.x, node.robotPosition.y - 1);break;
            case EAST: targetNodeCoord = new Coordinate(node.robotPosition.x + 1, node.robotPosition.y);break;
            case SOUTH: targetNodeCoord = new Coordinate(node.robotPosition.x, node.robotPosition.y + 1);break;
            case WEST: targetNodeCoord = new Coordinate(node.robotPosition.x - 1, node.robotPosition.y);break;
            }
            boolean isWithinBounds = targetNodeCoord.x > 0 && targetNodeCoord.x <= node.gridSize
                    && targetNodeCoord.y > 0 && targetNodeCoord.y <= node.gridSize;
            return isWithinBounds && !node.obstaclePositions.contains(targetNodeCoord);
        default:
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    private static State generateChildNode(State parent, Actions action) {
        HashSet<Coordinate> dirtPositions = parent.dirtPositions;
        Coordinate robotPosition = parent.robotPosition;
        Orientation robotOrientation = parent.robotOrientation;
        switch (action) {
        case SUCK:
            dirtPositions = (HashSet<Coordinate>)parent.dirtPositions.clone();
            dirtPositions.remove(parent.robotPosition);break;
        case MOVE:
            switch (parent.robotOrientation) {
            case NORTH: robotPosition = new Coordinate(parent.robotPosition.x, parent.robotPosition.y - 1);break;
            case EAST: robotPosition = new Coordinate(parent.robotPosition.x + 1, parent.robotPosition.y);break;
            case SOUTH: robotPosition = new Coordinate(parent.robotPosition.x, parent.robotPosition.y + 1);break;
            case WEST: robotPosition = new Coordinate(parent.robotPosition.x - 1, parent.robotPosition.y);break;
            }
            break;
        case RIGHT:
        case LEFT:
            if ((robotOrientation == Orientation.WEST && action == Actions.RIGHT)
                    || (robotOrientation == Orientation.EAST && action == Actions.LEFT)) {
                robotOrientation = Orientation.NORTH;
            } else if ((robotOrientation == Orientation.NORTH && action == Actions.RIGHT)
                    || (robotOrientation == Orientation.SOUTH && action == Actions.LEFT)) {
                robotOrientation = Orientation.EAST;
            } else if ((robotOrientation == Orientation.EAST && action == Actions.RIGHT)
                    || (robotOrientation == Orientation.WEST && action == Actions.LEFT)) {
                robotOrientation = Orientation.SOUTH;
            } else if ((robotOrientation == Orientation.SOUTH && action == Actions.RIGHT)
                    || (robotOrientation == Orientation.NORTH && action == Actions.LEFT)) {
                robotOrientation = Orientation.WEST;
            }
        }
        return new State(parent.gridSize, parent.obstaclePositions, dirtPositions, robotPosition, robotOrientation);
    }
}
