package markkasun_seanfloyd_a1;

public class RobotNode extends Node {

    public RobotNode(State state, String action, int pathCost, Node previousNode) {
        super(state, action, pathCost, previousNode);
    }

    @Override
    public int heuristicCost() {
        if (!(state instanceof RobotState))
        {
            return 0;
        }
        RobotState rState = (RobotState) state;
        if (rState.getDirtPositions().size() == 0) return 0;
        int lowestDistance = Integer.MAX_VALUE;
        for (Coordinate dirt : rState.getDirtPositions()) {
            int distance = (50+10)*(rState.getDirtPositions().size() - 1) + manhattanDistance(rState.getRobotPosition(), dirt)*50+10;
            if (distance < lowestDistance) lowestDistance = distance;
        }
        return lowestDistance;
    }

    public static int manhattanDistance(Coordinate arg0, Coordinate arg1) {
        return Math.abs(arg0.x - arg1.x) + Math.abs(arg0.y - arg1.y);
    }
}