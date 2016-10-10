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
        int lowestDistance = Integer.MAX_VALUE;
        for (Coordinate i : rState.getDirtPositions()) {
            int distance = Math.abs(rState.getRobotPosition().x - i.x) + Math.abs(rState.getRobotPosition().y - i.y);
            if (distance < lowestDistance) lowestDistance = distance;
        }
        return lowestDistance*50;
    }

}
