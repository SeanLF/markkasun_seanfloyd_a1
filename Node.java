package markkasun_seanfloyd_a1;

public abstract class Node {
    protected State state;
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

    public abstract int heuristicCost();

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
