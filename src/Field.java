import Engine2D.*;
import Engine2D.Rectangle;
import UnityMath.Vector2;

import java.awt.*;
import java.util.Arrays;

public class Field extends ShapeObject {
    public int width;
    public int height;
    public int sizeX;
    public int sizeY;
    public AbstractShape[][] fieldMatrix;
    Field(int w, int h, int size){
        super("Field", 1);
        this.width = w;
        this.height = h;
        this.sizeX = width/size;
        this.sizeY = height/size;
        this.fieldMatrix = new AbstractShape[sizeX][sizeY];
        for (var i: fieldMatrix){
            Arrays.fill(i, null);
        }
        int ii = 0;
        for(int i = -width/2+size/2; i < width/2; i+=size){
            int jj = 0;
            for(int j = height/2-size/2; j > -height/2; j-=size) {
                Rectangle rect = new Rectangle(size/2, new Vector2(i,j), null);
                this.fieldMatrix[ii][jj++] = rect;
                this.add(rect);
            }
            ii++;
        }
    }
}
