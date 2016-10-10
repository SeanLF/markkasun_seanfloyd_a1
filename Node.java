package markkasun_seanfloyd_a1;

public class Node {
    private State state;
    private String action;
    private int pathCost;
    private Node previousNode;
    private int fCost;

    public Node(State state, String action, int pathCost, Node previousNode) {
        this.state = state;
        this.action = action;
        this.pathCost = pathCost;
        this.previousNode = previousNode;
    }

    public int heuristicCost() {
        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Node)
        {
          Node node = (Node) o;
          return this.state.equals(node.state);
        }

        return false;
    }
    
    public State getState() {
        return state;
    }

    public String getAction() {
        return action;
    }

    public int getPathCost() {
        return pathCost;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }
}
