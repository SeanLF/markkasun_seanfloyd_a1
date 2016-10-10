package markkasun_seanfloyd_a1;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node arg0, Node arg1) {
        if (arg0.getfCost() < arg1.getfCost()) {
            return -1;
        } else if (arg0.getfCost() > arg1.getfCost()) {
            return 1;
        }
        return 0;
    }
}
