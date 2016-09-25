package markkasun_seanfloyd_A1;

import java.util.List;

public class RobotApp {

    /*
     * ARGS
     * 1: Size of grid (one number)
     */

    private static int DEFAULT_SIZE = 4;

    public static void main(String[] args) {
        int gridSize;
        List<Tuple<Integer, Integer>> obstaclePositions;
        List<Tuple<Integer, Integer>> dirtPositions;
        Tuple<Integer, Integer> robotPosition;
        Orientation robotOrientation;

        if (args[0] == null) {
            gridSize = DEFAULT_SIZE;
        } else {
            gridSize = Integer.parseInt(args[0]);
        }
    }

    private Grid generateGrid(
            int gridSize,
            List<Tuple<Integer, Integer>> obstaclePositions,
            List<Tuple<Integer, Integer>> dirtPositions,
            Tuple<Integer, Integer> robotPosition,
            Orientation robotOrientation) {

        return null;
    }

    private List<String> search(int searchType, Grid grid) {
        return null;
    }

    private void printSolution(List<String> solution){
        for (String line : solution) {
            System.out.println(line);
        }
    }

    public class Grid {

        int gridSize;
        List<Tuple<Integer, Integer>> obstaclePositions;
        List<Tuple<Integer, Integer>> dirtPositions;
        Tuple<Integer, Integer> robotPosition;
        Orientation robotOrientation;

        public Grid(
                int gridSize,
                List<Tuple<Integer, Integer>> obstaclePositions,
                List<Tuple<Integer, Integer>> dirtPositions,
                Tuple<Integer, Integer> robotPosition,
                Orientation robotOrientation) {
            this.gridSize = gridSize;
            this.obstaclePositions = obstaclePositions;
            this.dirtPositions = dirtPositions;
            this.robotPosition = robotPosition;
            this.robotOrientation = robotOrientation;
        }
    }

    public class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }
}
