package markkasun_seanfloyd_a1;

import java.util.Set;
import java.util.HashSet;
import markkasun_seanfloyd_a1.State;
import markkasun_seanfloyd_a1.RobotApp.Actions;

public class Node {
    final State state;
    final Actions action;
    final int actionCost;
    final Node previousNode;

    public Node(State state, Actions action, int actionCost, Node previousNode) {
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
}
