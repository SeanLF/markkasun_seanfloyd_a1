package markkasun_seanfloyd_a1;

import java.util.HashSet;

import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class RobotState implements State {
    private HashSet<Coordinate> dirtPositions;
    private Coordinate robotPosition;
    private Orientation robotOrientation;

    public RobotState(HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        this.dirtPositions = dirtPositions;
        this.robotPosition = robotPosition;
        this.robotOrientation = robotOrientation;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof RobotState)
        {
          RobotState state = (RobotState) o;
          if (this.dirtPositions.equals(state.dirtPositions)
                  && this.robotPosition.equals(state.robotPosition)
                  && this.robotOrientation.equals(state.robotOrientation))
             return true;
        }
        return false;
    }

    public HashSet<Coordinate> getDirtPositions() {
        return dirtPositions;
    }

    public Coordinate getRobotPosition() {
        return robotPosition;
    }

    public Orientation getRobotOrientation() {
        return robotOrientation;
    }
}
