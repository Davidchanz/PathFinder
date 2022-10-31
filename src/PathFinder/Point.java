package PathFinder;
import Engine2D.AbstractShape;

public class Point {
    int i;
    int j;
    AbstractShape object;
    public Point(int i, int j, AbstractShape object){
        this.i = i;
        this.j = j;
        this.object = object;
    }
}
