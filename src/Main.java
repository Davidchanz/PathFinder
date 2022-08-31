import Engine2D.AbstractShape;
import Engine2D.Rectangle;
import Engine2D.Scene;
import Engine2D.ShapeObject;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static Field field;
    public static Point start;
    public static Point end;
    public static Scene scene;
    public static int size;
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
        size = 20;
        field = new Field(800, 600, size);
        field.setLabirint(200);
        scene.add(field);
        start = getStart();
        end = getEnd();

        frame.add(scene);
        frame.setVisible(true);
        //findpath();
        findpath2(start);
    }
    public static Point getStart(){
        int id = (new Random().nextInt(1000))%field.body.size();
        field.body.get(id).setColor(Color.BLUE);
        int ii=0;
        int jj=0;
        for(int i = 0; i < field.fieldMatrix.length; i++){
            for(int j = 0; j < field.fieldMatrix[i].length; j++){
                if(field.fieldMatrix[i][j] == field.body.get(id)) {
                    ii = i;
                    jj = j;
                }
            }
        }
        return new Point(ii, jj, field.body.get(id));
    }
    public static Point getEnd(){
        int id;
        if(start != null){
            do{
                id = (new Random().nextInt(1000))%field.body.size();
            }while (field.body.get(id) == start.object);
            field.body.get(id).setColor(Color.RED);
        }else {
            id = (new Random().nextInt(1000)) % field.body.size();
            field.body.get(id).setColor(Color.RED);
        }
        int ii=0;
        int jj=0;
        for(int i = 0; i < field.fieldMatrix.length; i++){
            for(int j = 0; j < field.fieldMatrix[i].length; j++){
                if(field.fieldMatrix[i][j] == field.body.get(id)) {
                    ii = i;
                    jj = j;
                }
            }
        }
        return new Point(ii, jj, field.body.get(id));
    }
    public static void findpath(){
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
    }
    public static void findpath2(Point point){
        int i = point.i;
        int j = point.j;
        while (1==1) {
            scene.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            double[] ranges = new double[4];

            if (!isCollision(i+1, j))
                ranges[0] = find2(new Point(i + 1, j, field.fieldMatrix[i + 1][j]));
            else
                ranges[0] = Double.MAX_VALUE;
            if (!isCollision(i-1, j))
                ranges[1] = find2(new Point(i - 1, j, field.fieldMatrix[i - 1][j]));
            else
                ranges[1] = Double.MAX_VALUE;
            if (!isCollision(i, j+1))
                ranges[2] = find2(new Point(i, j + 1, field.fieldMatrix[i][j + 1]));
            else
                ranges[2] = Double.MAX_VALUE;
            if (!isCollision(i, j-1))
                ranges[3] = find2(new Point(i, j - 1, field.fieldMatrix[i][j - 1]));
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
                        case 0 -> i = i+1;
                        case 1 -> i = i-1;
                        case 2 -> j = j+1;
                        case 3 -> j = j-1;
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
                return;
            }
        }
    }
    public static double find2(Point point){
        double x = Math.abs(point.object.position.x - end.object.position.x);
        double y = Math.abs(point.object.position.y - end.object.position.y);
        return Math.sqrt(x * x + y * y);
    }
    public static boolean isCollision(int i, int j){

        if(i < 0 || j < 0 || i >= field.fieldMatrix.length || j >= field.fieldMatrix[0].length)
            return true;
        else return field.fieldMatrix[i][j].id == 1;
    }
}