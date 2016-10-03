package markkasun_seanfloyd_a1;

import java.util.ArrayList;

public interface Problem {

    public abstract boolean goalTest(State state);
    
    public abstract ArrayList<String> getActions(State state);
    
    public abstract Node generateChildNode(Node parent, String action);

    public abstract State getInitialState();

}
