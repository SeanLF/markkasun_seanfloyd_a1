package markkasun_seanfloyd_a1;

import java.util.Set;
import java.util.HashSet;
import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class State {
    final int gridSize;
    final Set<Coordinate> obstaclePositions;
    final HashSet<Coordinate> dirtPositions;
    final Coordinate robotPosition;
    final Orientation robotOrientation;

    public State(int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        this.gridSize = gridSize;
        this.obstaclePositions = obstaclePositions;
        this.dirtPositions = dirtPositions;
        this.robotPosition = robotPosition;
        this.robotOrientation = robotOrientation;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof State)
        {
          State state = (State) o;
          if (this.gridSize == state.gridSize
                  && this.obstaclePositions.equals(state.obstaclePositions)
                  && this.dirtPositions.equals(state.dirtPositions)
                  && this.robotPosition.equals(state.robotPosition)
                  && this.robotOrientation.equals(state.robotOrientation))
             return true;
        }
        return false;
    }
}
