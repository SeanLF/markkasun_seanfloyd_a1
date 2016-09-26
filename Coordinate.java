package markkasun_seanfloyd_a1;

public class Coordinate {
    public final int x;
    public final int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Coordinate)
        {
          Coordinate coord = (Coordinate) o;
          if (this.x == coord.x && this.y == coord.y)
             return true;
        }
        return false;
    }
}
