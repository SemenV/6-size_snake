package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{




        Field field = new Field(10,10,1,20);

        Group elements = field.getGroup();
        VBox rootNode = new VBox(elements);
        rootNode.setAlignment(Pos.CENTER);

        Scene scene = new Scene(rootNode, 1280, 640);
        primaryStage.setScene(scene);


        //move
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                it -> {
                    field.move(1, 3, Field.Status.VOID);


                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        //end

        primaryStage.show();
    }

    /*
    nw N ne  !!!use only nw ne
    W  +  E  !!!w e
    sw S se  !!! sw se
     */

    public static void main(String[] args) {
        launch(args);
    }

}

