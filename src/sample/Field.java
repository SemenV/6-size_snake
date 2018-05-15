package sample;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Math.*;

public class Field {
    private final int radius;
    private final int rowCount;
    private final int columnCount;
    private final int indent;
    private Point2D r;


    private Group group = new Group();
    private List<Status> field;
    private Deque<Pair<Integer,Integer>> snakePsrts = new LinkedBlockingDeque();

    public Field (int column, int row, int indent, int radius) {
        if (column < 4 || row < 4) {
            column = 4;
            row = 4;
        }
        this.radius = radius;
        this.indent = indent;
        this.rowCount = row;
        this.columnCount = column;
        field = new ArrayList<>(Collections.nCopies(rowCount * columnCount, Status.VOID));
        createField();

        //start position
        creatSnakePart(1,1);
        creatSnakePart(1,2);

    }



    public enum Status {
        FEED, VOID, WALL
    }


    public void creatSnakePart(int column, int row) {
        snakePsrts.addFirst(new Pair(column, row));
        changeHexColor(column,row, Color.AZURE);
    }

    public void move(int column, int row, Status status) {



        if (field.get(column * row) == Status.WALL) {
            //break
        }
        creatSnakePart(column,row);
        if (status == Status.VOID) {
            changeHexColor(snakePsrts.getLast().getKey(),snakePsrts.getLast().getValue(),Color.CADETBLUE);
            snakePsrts.removeLast();
        }
    }

    public void createField() {
        for (int column = 0; column < columnCount; column++) {  //столбец высота
            for (int row = 0; row < rowCount; row++) { //ряд строка
                group.getChildren().add(createHex(column,row,Color.CADETBLUE));
            }
        }
    }

    public void changeHexColor(int column, int row, Color color){
        Polygon hexagon = createHex(column,row,color);
        group.getChildren().set(column * this.rowCount + row ,hexagon);
    }

    private Polygon createHex(int column, int row, Color color){
        Polygon hexagon = new Polygon();
        hexagon.setFill(color);

        for (int k = 0; k < 6; k++) {
            hexagon.getPoints().add(radius * sin(k * PI / 3));
            hexagon.getPoints().add(radius * cos(k * PI / 3));
        }

        hexagon.setTranslateY(column*(radius + radius /2 + indent));
        double r = sqrt(3) / 2 * radius;
        hexagon.setTranslateX(row * 2 * r - column % 2 * r + row * indent );
        return hexagon;
    }

    public Group getGroup() {
        return group;
    }
    public Node getHead() {
        return group.getChildren().get(snakePsrts.getFirst().getKey() * this.rowCount + snakePsrts.getFirst().getValue());
    }
}