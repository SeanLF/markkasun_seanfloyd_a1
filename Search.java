package markkasun_seanfloyd_a1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Search {
    public static Node depthFirstSearch(Problem problem) {
        Node initialNode = problem.getInitialNode();
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

    public static Node breadthFirstSearch(Problem problem) {
        Node initialNode = problem.getInitialNode();
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

    public static Node aStarSearch(Problem problem) {
        Node initialNode = problem.getInitialNode();
        if (problem.goalTest(initialNode.getState())) return initialNode;
        initialNode.setfCost(0);
        Queue<Node> frontier = new PriorityQueue<Node>(4, new NodeComparator());
        frontier.add(initialNode);
        Node node;

        while (!frontier.isEmpty()) {
            node = frontier.poll();
            for (String action : problem.getActions(node.getState())) {
                Node child = problem.generateChildNode(node, action);
                if (problem.goalTest(child.getState())) return child;
                child.setfCost(child.getPathCost() + child.heuristicCost());
                frontier.add(child);
            }
        }

        // Solution not found
        return null;
      }
}
