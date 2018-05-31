import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Math.*;


public class Main extends Application {
    private final int speed = 700;
    private Field field = new Field(10, 10,  20);
//    private Field field;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("6 Size Snake");


        Group elements = field.getGroup();
        nodePos(elements);

        VBox rootNode = new VBox(elements);
        rootNode.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);


        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(speed),
                it -> field.move()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }


    private void nodePos(final Node reporter) {
        reporter.setOnMouseMoved(event -> {
            Direction s;
            s = getDirection(field.getHead().getTranslateX(),field.getHead().getTranslateY() + field.getR(),event.getSceneX(),event.getSceneY());
            field.setDirection(s);
        });
    }

   /*
    nw N ne  !!!use only nw ne
    W  +  E  !!! w e
    sw S se  !!! sw se
     */

    // PI/3 - 1pdrt of hex
    public Direction getDirection(double startX, double startY, double endX, double endY) {
        double angle = angle(startX, startY, endX, endY);
        if (angle >= -PI / 6 && angle < PI / 6) return Direction.E;
        if (angle >= PI / 6 && angle < PI / 2) return Direction.NE;
        if (angle >= PI / 2 && angle < 5 * PI / 6) return Direction.NW;
        if (angle >= 5 * PI / 6 || angle <= -5 * PI / 6) return Direction.W;
        if (angle > -5 * PI / 6 && angle <= -PI / 2) return Direction.SW;
        return Direction.SE;
    }

    //return -PI <= angle <= PI
    public double angle(double startX, double startY, double endX, double endY) {
        double radius = sqrt(pow(endX - startX, 2) + pow(-endY + startY, 2));
        double projectionVectorOX = acos((endX - startX) / radius);
        if (-endY + startY >= 0) return projectionVectorOX;
        else return -projectionVectorOX;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
