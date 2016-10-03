package markkasun_seanfloyd_a1;

import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class RobotApp {

    public enum Orientation { NORTH, EAST, SOUTH, WEST }

    /* ----- BEGIN -------
    Modify these to modify the grid parameters */
    private static final int GRID_SIZE = 4,
                             SEARCH_TYPE = 2;
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

        //State initialState = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        Grid grid = generateGrid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
        RobotProblem problem = new RobotProblem(grid);
        Node solution = search(searchType, problem);
        printSolution(solution);
    }

    private static Grid generateGrid(int gridSize, Set<Coordinate> obstaclePositions, HashSet<Coordinate> dirtPositions, Coordinate robotPosition, Orientation robotOrientation) {
        return new Grid(gridSize, obstaclePositions, dirtPositions, robotPosition, robotOrientation);
    }

    private static Node search(int searchType, Problem problem) {
        switch (searchType) {
          default:
          case 1: return depthFirstSearch(problem);
          case 2: return breadthFirstSearch(problem);
          case 3: return aStarSearch(problem);
        }
    }

    private static void printSolution(Node solution){
        List<String> solutionString = RobotProblem.generateSolutionString(solution);

        for (String line : solutionString) {
            System.out.println(line);
        }
    }

    private static Node depthFirstSearch(Problem problem) {
        Node initialNode = new Node(problem.getInitialState(), "START", 0, null);
        if (problem.goalTest(initialNode.getState())) return initialNode;
        ArrayList<Node> explored = new ArrayList<Node>();
        Stack<Node> frontier = new Stack<Node>();
        frontier.add(initialNode);
        Node node;

        while (!frontier.isEmpty()) {
            node = frontier.pop();
            explored.add(node);

            // reverse action order since we're using a stack to store them
            ArrayList<String> actions = problem.getActions(node.getState());
            Collections.reverse(actions);

            for (String action : actions) {
                Node child = problem.generateChildNode(node, action);
                if (!frontier.contains(child) && !explored.contains(child)) {
                    if (problem.goalTest(child.getState())) return child;
                    frontier.add(child);
                }
            }
        }

        // Solution not found
        return null;
    }

    private static Node breadthFirstSearch(Problem problem) {
        Node initialNode = new Node(problem.getInitialState(), "START", 0, null);
        if (problem.goalTest(initialNode.getState())) return initialNode;
        ArrayList<Node> explored = new ArrayList<Node>();
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(initialNode);
        Node node;

        while (!frontier.isEmpty()) {
            node = frontier.poll();
            explored.add(node);
            for (String action : problem.getActions(node.getState())) {
                Node child = problem.generateChildNode(node, action);
                if (!frontier.contains(child) && !explored.contains(child)) {
                    if (problem.goalTest(child.getState())) return child;
                    frontier.add(child);
                }
            }
        }

        // Solution not found
        return null;
    }

    private static Node aStarSearch(Problem problem) {
      return null;
    }
}
