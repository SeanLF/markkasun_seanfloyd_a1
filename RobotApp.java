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

        State initialState = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        Node initialNode = new Node(initialState, null, 0, null);
        Node solution = search(searchType, initialNode);
        printSolution(null);
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

    private static Node search(int searchType, Node initialNode) {
        Node solution;
        switch (searchType) {
          case 2:
            solution = breadthFirstSearch(initialNode);
            break;
          case 3:
            solution = aStarSearch(initialNode);
            break;
          default:
            solution = depthFirstSearch(initialNode);
        }
        return solution;
    }

    private static void printSolution(ArrayList<String> solution){
        for (String line : solution) {
            System.out.println(line);
        }
    }

    private static Node depthFirstSearch(Node initialNode) {
      return null;
    }

    private static Node breadthFirstSearch(Node initialNode) {
        ArrayList<Node> explored = new ArrayList<Node>();
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(initialNode);
        Node node;

        while (true) {
            if (frontier.isEmpty()) break;
            node = frontier.poll();
            explored.add(node);
            for (Actions action : actionArray) {
                if (!isActionPossible(node.state, action)) continue;
                Node child = generateChildNode(node, action);
                if (!frontier.contains(child) && !explored.contains(child)) {
                    if (child.state.dirtPositions.isEmpty()) return null; //TODO: Must return valid solution shite
                    frontier.add(child);
                }
            }
        }

      return null;
    }

    private static Node aStarSearch(Node initialNode) {
      return null;
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
    private static Node generateChildNode(Node parent, Actions action) {
        State parentState = parent.state;
        HashSet<Coordinate> dirtPositions = parentState.dirtPositions;
        Coordinate robotPosition = parentState.robotPosition;
        Orientation robotOrientation = parentState.robotOrientation;
        int actionCost = 0;

        switch (action) {
        case SUCK:
            dirtPositions = (HashSet<Coordinate>)parentState.dirtPositions.clone();
            dirtPositions.remove(parentState.robotPosition);
            actionCost = 10;
            break;
        case MOVE:
            actionCost = 50;
            switch (parentState.robotOrientation) {
            case NORTH: robotPosition = new Coordinate(parentState.robotPosition.x, parentState.robotPosition.y - 1);break;
            case EAST: robotPosition = new Coordinate(parentState.robotPosition.x + 1, parentState.robotPosition.y);break;
            case SOUTH: robotPosition = new Coordinate(parentState.robotPosition.x, parentState.robotPosition.y + 1);break;
            case WEST: robotPosition = new Coordinate(parentState.robotPosition.x - 1, parentState.robotPosition.y);break;
            }
            break;
        case RIGHT:
        case LEFT:
            actionCost = 20;
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
        State newState = new State(parentState.gridSize, parentState.obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        return new Node(newState, action, actionCost, parent);
    }
}
