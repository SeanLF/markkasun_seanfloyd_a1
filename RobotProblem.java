package markkasun_seanfloyd_a1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class RobotProblem implements Problem {

    private static String[] actions = {"SUCK", "MOVE", "RIGHT", "LEFT"};
    private Grid grid;

    public RobotProblem(Grid grid) {
        this.grid = grid;
    }

    @Override
    public boolean goalTest(State state) {
        if (state instanceof RobotState) {
            RobotState rState = (RobotState) state;
            return rState.getDirtPositions().isEmpty();
        }
        return false;
    }

    @Override
    public ArrayList<String> getActions(State state) {
        if (!(state instanceof RobotState)) {
            //TODO: Throw exception
            return null;
        }
        RobotState rState = (RobotState) state;
        ArrayList<String> resultArray = new ArrayList<String>();
        for (String action : actions) {
            if (isActionPossible(rState, action)) {
                resultArray.add(action);
            }
        }
        return resultArray;
    }

    private boolean isActionPossible(RobotState node, String action) {
        if (action == "SUCK") {
            return node.getDirtPositions().contains(node.getRobotPosition());
        } else if (action == "MOVE") {
            Coordinate targetNodeCoord = null;
            switch (node.getRobotOrientation()) {
            case NORTH: targetNodeCoord = new Coordinate(node.getRobotPosition().x, node.getRobotPosition().y - 1);break;
            case EAST: targetNodeCoord = new Coordinate(node.getRobotPosition().x + 1, node.getRobotPosition().y);break;
            case SOUTH: targetNodeCoord = new Coordinate(node.getRobotPosition().x, node.getRobotPosition().y + 1);break;
            case WEST: targetNodeCoord = new Coordinate(node.getRobotPosition().x - 1, node.getRobotPosition().y);break;
            }
            boolean isWithinBounds = targetNodeCoord.x > 0 && targetNodeCoord.x <= grid.getGridSize()
                    && targetNodeCoord.y > 0 && targetNodeCoord.y <= grid.getGridSize();
                    return isWithinBounds && !grid.getObstaclePositions().contains(targetNodeCoord);
        } else if (action == "RIGHT" || action == "LEFT"){
            return true;
        } else {
            //TODO: Throw exception?
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public Node generateChildNode(Node parent, String action) {
        if (!(parent.getState() instanceof RobotState)) {
            //TODO: Throw exception
            return null;
        }
        RobotState parentState = (RobotState) parent.getState();
        HashSet<Coordinate> dirtPositions = parentState.getDirtPositions();
        Coordinate robotPosition = parentState.getRobotPosition();
        Orientation robotOrientation = parentState.getRobotOrientation();
        int actionCost = 0;

        if (action == "SUCK") {
            dirtPositions = (HashSet<Coordinate>)parentState.getDirtPositions().clone();
            dirtPositions.remove(parentState.getRobotPosition());
            actionCost = 10;
        } else if (action == "MOVE") {
            actionCost = 50;
            switch (parentState.getRobotOrientation()) {
            case NORTH: robotPosition = new Coordinate(parentState.getRobotPosition().x, parentState.getRobotPosition().y - 1);break;
            case EAST: robotPosition = new Coordinate(parentState.getRobotPosition().x + 1, parentState.getRobotPosition().y);break;
            case SOUTH: robotPosition = new Coordinate(parentState.getRobotPosition().x, parentState.getRobotPosition().y + 1);break;
            case WEST: robotPosition = new Coordinate(parentState.getRobotPosition().x - 1, parentState.getRobotPosition().y);break;
            }
        }
        else if (action == "RIGHT" || action == "LEFT") {
            actionCost = 20;
            if ((robotOrientation == Orientation.WEST && action == "RIGHT")
                    || (robotOrientation == Orientation.EAST && action == "LEFT")) {
                robotOrientation = Orientation.NORTH;
            } else if ((robotOrientation == Orientation.NORTH && action == "RIGHT")
                    || (robotOrientation == Orientation.SOUTH && action == "LEFT")) {
                robotOrientation = Orientation.EAST;
            } else if ((robotOrientation == Orientation.EAST && action == "RIGHT")
                    || (robotOrientation == Orientation.WEST && action == "LEFT")) {
                robotOrientation = Orientation.SOUTH;
            } else if ((robotOrientation == Orientation.SOUTH && action == "RIGHT")
                    || (robotOrientation == Orientation.NORTH && action == "LEFT")) {
                robotOrientation = Orientation.WEST;
            }
        }
        RobotState newState = new RobotState(dirtPositions, robotPosition, robotOrientation);
        return new Node(newState, action, actionCost, parent);
    }
    
    public static LinkedList<String> generateSolutionString(Node solutionNode) {
        if (!(solutionNode.getState() instanceof RobotState)) {
            //TODO: Throw exception
            return null;
        }
        RobotState rState;
        LinkedList<String> solutionString = new LinkedList<String>();
        int cumulativeCost = 0, depth = 0;

        while(solutionNode != null) {
            rState = (RobotState)solutionNode.getState();
            cumulativeCost += solutionNode.getActionCost();
            solutionString.addFirst(
              String.format(
                "pos(%d, %d), %s, %s",
                rState.getRobotPosition().x,
                rState.getRobotPosition().y,
                rState.getRobotOrientation(),
                solutionNode.getAction()
              )
            );
            solutionNode = solutionNode.getPreviousNode();

          }

          if(solutionString.size() == 0) {
            solutionString.add("No solution found :(");
          } else {
            depth = solutionString.size();
            solutionString.addLast("");
            solutionString.addLast(String.format("total cost: %d", cumulativeCost));
            solutionString.addLast(String.format("Depth: %d", depth));
          }
          return solutionString;
      }

    @Override
    public State getInitialState() {
        return grid.getInitialState();
    }
}