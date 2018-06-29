
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import static java.lang.Math.*;

public class Field {
    private final int indent = 1;
    private final int radius;
    private final int rowCount;
    private final int columnCount;
    private Direction direction;

    private Group group = new Group();
    private List<Status> field;
    private Deque<Pair<Integer,Integer>> snakeParts = new LinkedBlockingDeque();

    public Field (int column, int row, int radius) {
        if (column < 4 || row < 4) {
            column = 4;
            row = 4;
        }
        this.direction = Direction.E;
        this.radius = radius;
        this.rowCount = row;
        this.columnCount = column;
        this.field = new ArrayList<>(Collections.nCopies(rowCount * columnCount, Status.VOID));

        //start
        createVoidField();
        setWall();
        creatSnakePart(0,1);
        creatSnakePart(0,2);
        creatSnakePart(0,3);
        setFeed();
    }

    public enum Status {
        FEED(Color.GOLD),
        VOID(Color.CADETBLUE),
        WALL(Color.ROYALBLUE),
        SNAKE(Color.WHEAT);

        private Color color;
        Status(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    public void creatSnakePart(int column, int row) {
        snakeParts.addFirst(new Pair(column, row));
        changeHex(column,row, Status.SNAKE);
    }


    public void move() {
        int column = direction.getStep(snakeParts.getFirst()).getKey() % columnCount;
        int row = direction.getStep(snakeParts.getFirst()).getValue() % rowCount;
        if (row < 0) row = 9;
        if (column < 0) column = 9;

        int position = column * rowCount + row;

        Status status = field.get(position);

        //loss
        if (field.get(position) == Status.WALL || field.get(position) == Status.SNAKE) {
            System.exit(2);
        }
        creatSnakePart(column,row);

        //feed
        if (status == Status.VOID) {
            changeHex(snakeParts.getLast().getKey(), snakeParts.getLast().getValue(),Status.VOID);
            snakeParts.removeLast();
        } else {
            setFeed();
        }

    }

    public void createVoidField() {
        for (int column = 0; column < columnCount; column++) {  //столбец высота
            for (int row = 0; row < rowCount; row++) { //ряд строка
                group.getChildren().add(createHex(column,row,Status.VOID));
            }
        }
    }

    public void setWall(){
//        changeHex(2,3,Status.WALL);
//        changeHex(2,4,Status.WALL);
//        changeHex(3,3,Status.WALL);
    }

    public void changeHex(int column, int row, Status status){
        Polygon hexagon = createHex(column,row,status);
        int position = column * this.rowCount + row;
        group.getChildren().set(position ,hexagon);
        field.set(position,status);
    }

    private Polygon createHex(int column, int row, Status status){
        Polygon hexagon = new Polygon();
        hexagon.setFill(status.getColor());

        for (int k = 0; k < 6; k++) {
            hexagon.getPoints().add(radius * sin(k * PI / 3));
            hexagon.getPoints().add(radius * cos(k * PI / 3));
        }

        hexagon.setTranslateY(column*(radius + radius /2 + indent));
        double r = sqrt(3) / 2 * radius;
        hexagon.setTranslateX(row * 2 * r - column % 2 * r + row * indent );
        return hexagon;
    }

    public void setFeed() {
        List<Pair<Integer,Integer>> voidHex = new ArrayList<>();
        for (int i = 0; i < field.size(); i++) {
            Status actual = field.get(i);
            if ( actual != Status.SNAKE && actual != Status.WALL ) {
                Integer k = (int) floor(i / rowCount) ;
                voidHex.add(new Pair<>(k,i - k * rowCount ));
            }
        }
        Pair<Integer,Integer> place = voidHex.get(new Random().nextInt(voidHex.size()));
        field.set(place.getKey() * rowCount + place.getValue(),Status.FEED);
        changeHex(place.getKey(),place.getValue(),Status.FEED);
    }

    public double getR() {
        return sqrt(3) / 2 * radius;
    }

    public Group getGroup() {
        return group;
    }

    public Node getHead() {
        return group.getChildren().get(snakeParts.getFirst().getKey() * this.rowCount + snakeParts.getFirst().getValue() + 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}