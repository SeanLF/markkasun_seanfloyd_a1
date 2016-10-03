package markkasun_seanfloyd_a1;

public class Node {
    private State state;
    private String action;
    private int actionCost;
    private Node previousNode;

    public Node(State state, String action, int actionCost, Node previousNode) {
        this.state = state;
        this.action = action;
        this.actionCost = actionCost;
        this.previousNode = previousNode;
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

    public int getActionCost() {
        return actionCost;
    }

    public Node getPreviousNode() {
        return previousNode;
    }
}
