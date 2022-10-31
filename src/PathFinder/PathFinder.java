package PathFinder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    public final Field field;
    public final int size;
    public PathFinder(int w, int h, int size){
        this.size = size;
        this.field = new Field(w, h, size);
    }

    public List<Character> find(Point point, Point end){
        List<Character> result = new ArrayList<Character>();
        int i = point.i;
        int j = point.j;
        while (true) {
            double[] ranges = new double[4];

            if (!isCollision(i+1, j))
                ranges[0] = find2(new Point(i + 1, j, field.fieldMatrix[i + 1][j]), end);
            else
                ranges[0] = Double.MAX_VALUE;
            if (!isCollision(i-1, j))
                ranges[1] = find2(new Point(i - 1, j, field.fieldMatrix[i - 1][j]), end);
            else
                ranges[1] = Double.MAX_VALUE;
            if (!isCollision(i, j+1))
                ranges[2] = find2(new Point(i, j + 1, field.fieldMatrix[i][j + 1]), end);
            else
                ranges[2] = Double.MAX_VALUE;
            if (!isCollision(i, j-1))
                ranges[3] = find2(new Point(i, j - 1, field.fieldMatrix[i][j - 1]), end);
            else
                ranges[3] = Double.MAX_VALUE;

            double min = Double.MAX_VALUE;
            for(var it: ranges){
                if(it <= min)
                    min = it;
            }
            for(int k = 0; k < ranges.length; k++){
                if(min == ranges[k]){
                    switch (k){
                        case 0 -> {i = i+1;
                            result.add('r');
                        }
                        case 1 -> {i = i-1;
                            result.add('l');}
                        case 2 -> {j = j+1;
                            result.add('u');}
                        case 3 -> {j = j-1;
                            result.add('d');}
                    }
                }
            }
            field.fieldMatrix[i][j].setColor(Color.CYAN);
            int sum = 0;
            for(var it: ranges){
                sum += it;
            }
            if(sum == 4*size) {
                System.out.println("Found!");
                return result;
            }
        }
    }
    public double find2(Point point, Point end){
        double x = Math.abs(point.object.position.x - end.object.position.x);
        double y = Math.abs(point.object.position.y - end.object.position.y);
        return Math.sqrt(x * x + y * y);
    }
    public boolean isCollision(int i, int j){

        if(i < 0 || j < 0 || i >= field.fieldMatrix.length || j >= field.fieldMatrix[0].length)
            return true;
        else return field.fieldMatrix[i][j].id == 1;
    }
}
