package markkasun_seanfloyd_a1;

import java.util.HashSet;
import java.util.Set;

import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class Grid {
    private int gridSize;
    private Set<Coordinate> obstaclePositions;
    private RobotState initialState;

    public Grid(int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        this.gridSize = gridSize;
        this.obstaclePositions = obstaclePositions;
        initialState = new RobotState(dirtPositions, robotPosition, robotOrientation);
    }

    public int getGridSize() {
        return gridSize;
    }

    public Set<Coordinate> getObstaclePositions() {
        return obstaclePositions;
    }
    
    public RobotState getInitialState() {
        return initialState;
    }

}
