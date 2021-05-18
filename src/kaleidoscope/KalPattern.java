package kaleidoscope;



import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.SecureRandom;


public class KalPattern extends Application {
    private SecureRandom random = new SecureRandom();


    @Override
    public void start(Stage stage) {

        stage.setTitle("kaleidoscope");
        Pane container = new Pane();
        container.setStyle("-fx-background-color: black;" );

        // repeats the pattern to fill the pane
        for (int j = 0; j < 16; j++) {

            // creates a row of pattern
            for (int i = 0; i < 16; i++) {

                Polygon p1 = DrawPolygon(300, 50, 250, 100, 350, 100, 100 * i, 40+(100*j));
                p1.setRotate(180);

                Polygon p2 = DrawPolygon(300, 50, 250, 100, 350, 100, 100 * i, 90+(100*j));


                Polygon p4 = DrawPolygon(300, 80, 280, 100, 320, 100, 100 * i, 30+(80*j));
                p4.setTranslateX(-250);
                p4.setRotate(180);

                Polygon p5 = DrawPolygon(300, 80, 280, 100, 320, 100, 100 * i, 75+(80*j));
                p5.setTranslateX(-250);
                p5.setRotate(180);

                Polygon p6 = DrawPolygon(300, 65, 265, 100, 335, 100, 100 * i, 60+(80*j));
                p6.setTranslateX(-250);
                p6.setRotate(180);

                RotateTransition(p1, -90);
                RotateTransition(p2, 90);
                RotateTransition(p4, 90);
                RotateTransition(p5, 90);
                RotateTransition(p6, -90);

                ScaleTransition(p1,-1.0f,-1.0f);
                ScaleTransition(p2,-1.0f,-1.0f);
                ScaleTransition(p4,0.8f,0.8f);
                ScaleTransition(p5,0.8f,0.8f);
                ScaleTransition(p6,-1.0f,-1.0f);


                // adds shapes to the pane
                container.getChildren().addAll(p1, p2, p4, p6, p5);
            }
        }



        Scene scene = new Scene(container, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    private Polygon DrawPolygon (double x1, double y1, double x2, double y2,double x3, double y3,
                                 double layoutX, double layoutY ){
        javafx.scene.shape.Polygon polygon2 = new Polygon();
        polygon2.getPoints().addAll(new Double[]{
                x1, y1,
                x2, y2,
                x3, y3 });
        polygon2.setLayoutX(layoutX);
        polygon2.setLayoutY(layoutY);
        polygon2.setFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        polygon2.setTranslateX(-300);
        polygon2.setTranslateY(-110);
        return polygon2;
    }

    private Transition RotateTransition(Polygon polygon, double angle){
        Duration duration = Duration.seconds(6);
        RotateTransition rotateTransition = new RotateTransition(duration, polygon);
        rotateTransition.setByAngle(angle);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
        return rotateTransition;
    }

    private Transition ScaleTransition (Shape polygon, double setX, double setY){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1),polygon);
        scaleTransition.setByX(setX);
        scaleTransition.setByY(setY);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Animation.INDEFINITE);
        scaleTransition.play();
        return scaleTransition;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

