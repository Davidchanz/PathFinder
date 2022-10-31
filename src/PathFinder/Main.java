package PathFinder;
import Engine2D.Scene;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static PathFinder pf;
    public static Point start;
    public static Point end;
    public static Scene scene;
    public static void main(String[] args) {
        System.out.println("Hello world!");

        JFrame frame = new JFrame("PathFinder");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        scene = new Scene(800, 600);
        scene.setCenterVisible(true);
        scene.setCoordVisible(true);
        scene.setBorder(2, Color.GRAY);
        scene.setBorderVisible(true);
        //size = 20;
        //field = new Field(800, 600, size);
        //field.setLabirint(200);
        //scene.add(field);
        pf = new PathFinder(800, 600, 20);
        start = getStart();
        end = getEnd();

        frame.add(scene);
        frame.setVisible(true);
        //findpath();
        System.out.println(pf.find(start, end));
    }
    public static Point getStart(){
        int id = (new Random().nextInt(1000))%pf.field.body.size();
        pf.field.body.get(id).setColor(Color.BLUE);
        int ii=0;
        int jj=0;
        for(int i = 0; i < pf.field.fieldMatrix.length; i++){
            for(int j = 0; j < pf.field.fieldMatrix[i].length; j++){
                if(pf.field.fieldMatrix[i][j] == pf.field.body.get(id)) {
                    ii = i;
                    jj = j;
                }
            }
        }
        return new Point(ii, jj, pf.field.body.get(id));
    }
    public static Point getEnd(){
        int id;
        if(start != null){
            do{
                id = (new Random().nextInt(1000))%pf.field.body.size();
            }while (pf.field.body.get(id) == start.object);
            pf.field.body.get(id).setColor(Color.RED);
        }else {
            id = (new Random().nextInt(1000)) % pf.field.body.size();
            pf.field.body.get(id).setColor(Color.RED);
        }
        int ii=0;
        int jj=0;
        for(int i = 0; i < pf.field.fieldMatrix.length; i++){
            for(int j = 0; j < pf.field.fieldMatrix[i].length; j++){
                if(pf.field.fieldMatrix[i][j] == pf.field.body.get(id)) {
                    ii = i;
                    jj = j;
                }
            }
        }
        return new Point(ii, jj, pf.field.body.get(id));
    }
    /*public static void findpath(){
        System.out.println(start.object.position);
        System.out.println(end.object.position);

        find(start, 1000000);
        System.out.println("Found!");
    }
    public static double find(Point point, double old_range){
        scene.repaint();
        if(old_range == 0.0)
            return 0.0;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(point.object.position.x == end.object.position.x && point.object.position.y == end.object.position.y){
            return 0.0;
        }else {
            field.fieldMatrix[point.i][point.j].setColor(Color.CYAN);
            double x = Math.abs(point.object.position.x - end.object.position.x);
            double y = Math.abs(point.object.position.y - end.object.position.y);
            double range = Math.sqrt(x*x + y*y);
            double new_range = range;
            if(old_range > range) {
                int i = point.i;
                int j = point.j;

                if (i + 1 < field.fieldMatrix.length)
                    new_range = find(new Point(i + 1, j, field.fieldMatrix[i + 1][j]), range);
                if (i - 1 >= 0)
                    new_range = find(new Point(i - 1, j, field.fieldMatrix[i - 1][j]), range);
                if (j + 1 < field.fieldMatrix[i].length)
                    new_range = find(new Point(i, j + 1, field.fieldMatrix[i][j + 1]), range);
                if (j - 1 >= 0)
                    new_range = find(new Point(i, j - 1, field.fieldMatrix[i][j - 1]), range);
                return new_range;
            }else
                return -1.0;
        }
    }*/
}