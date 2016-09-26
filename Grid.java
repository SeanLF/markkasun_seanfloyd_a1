package markkasun_seanfloyd_a1;

import java.util.Set;
import java.util.HashSet;
import markkasun_seanfloyd_a1.RobotApp.Orientation;

public class Grid {

    final Grid parent;
    final int gridSize;
    final Set<Coordinate> obstaclePositions;
    final HashSet<Coordinate> dirtPositions;
    final Coordinate robotPosition;
    final Orientation robotOrientation;

    public Grid(Grid parent, int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        this.parent = parent;
        this.gridSize = gridSize;
        this.obstaclePositions = obstaclePositions;
        this.dirtPositions = dirtPositions;
        this.robotPosition = robotPosition;
        this.robotOrientation = robotOrientation;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Grid)
        {
          Grid grid = (Grid) o;
          if (this.gridSize == grid.gridSize
                  && this.obstaclePositions.equals(grid.obstaclePositions)
                  && this.dirtPositions.equals(grid.dirtPositions)
                  && this.robotPosition.equals(grid.robotPosition)
                  && this.robotOrientation.equals(grid.robotOrientation))
             return true;
        }
        return false;
    }
}
