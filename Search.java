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
        return aStarSearchRecursive(problem, initialNode, Integer.MAX_VALUE);
    }

    private static Node aStarSearchRecursive(Problem problem, Node node, int f_limit) {
        if (problem.goalTest(node.getState())) return node;
        PriorityQueue<Node> successors = new PriorityQueue<Node>(4, new NodeComparator());
        for (String action : problem.getActions(node.getState())) {
            Node child = problem.generateChildNode(node, action);
            child.setfCost(Integer.max(child.getPathCost() + child.heuristicCost(), node.getfCost()));
            successors.add(child);
        }
        while (!successors.isEmpty()) {
            Node best = successors.poll();
            if (best.getfCost() > f_limit) return best; //Supposed to return failure here..
            int alternative;
            if (!successors.isEmpty()) {
                alternative = successors.peek().getfCost();
            } else {
                alternative = best.getfCost();
            }
            Node result = aStarSearchRecursive(problem, best, Integer.min(f_limit, alternative));
            best.setfCost(result.getfCost());
            successors.add(best);
            if (problem.goalTest(result.getState())) return result;
        }
        return null;
    }
}
