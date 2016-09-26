package markkasun_seanfloyd_a1;

import java.util.ArrayList;

public abstract class Problem {
    
    private State initialState;
    
    public Problem (State initialState) {
        this.initialState = initialState;
    }

    public abstract boolean goalTest(State state);
    
    public abstract ArrayList<String> getActions(State state);
    
    public abstract Node generateChildNode(Node parent, String action);

    public State getInitialState() {
        return initialState;
    }

}
