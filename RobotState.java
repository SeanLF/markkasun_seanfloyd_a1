package markkasun_seanfloyd_a1;

import java.util.HashSet;
import java.util.Set;

import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class RobotState extends State {
    private int gridSize;
    private Set<Coordinate> obstaclePositions;
    private HashSet<Coordinate> dirtPositions;
    private Coordinate robotPosition;
    private Orientation robotOrientation;

    public RobotState(int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        this.gridSize = gridSize;
        this.obstaclePositions = obstaclePositions;
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
          if (this.gridSize == state.gridSize
                  && this.obstaclePositions.equals(state.obstaclePositions)
                  && this.dirtPositions.equals(state.dirtPositions)
                  && this.robotPosition.equals(state.robotPosition)
                  && this.robotOrientation.equals(state.robotOrientation))
             return true;
        }
        return false;
    }

    public int getGridSize() {
        return gridSize;
    }

    public Set<Coordinate> getObstaclePositions() {
        return obstaclePositions;
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
