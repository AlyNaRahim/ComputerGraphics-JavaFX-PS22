package kaleidoscopeNew;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.SecureRandom;

public class kalMain extends Application {
    private SecureRandom random = new SecureRandom();

    private int num;
    private int[] dx;
    private int[] dy;

    public void start(Stage stage) {
        Group root = new Group();

        Scene scene = new Scene(root, 500, 500);
        num = random.nextInt(50) + 12;

        dx = new int[num];
        dy = new int[num];

        double a = -10;
        double b = -10;
        double t = 0;

            Timeline timelineAnimation = new Timeline(
                    new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            Circle circle = new Circle();
                            circle.setRadius(a * (Math.acos(b * t)));
                            circle.setCenterX(random.nextInt(300) + 150);
                            circle.setCenterY(random.nextInt(200) + 150);
                            circle.setFill(randomColor());
                            circle.setStrokeWidth(random.nextInt(10));
                            a = a + 5;
                            b = b + 5;
                            t = t + 20;
                        }
                    }

            root.getChildren().add(circle);
            //dx[i] = 1 + random.nextInt(5);
            //dy[i] = 1 + random.nextInt(5);
//            int temp = i;
//            Timeline timelineAnimation = new Timeline(
//                    new KeyFrame(Duration.millis(10), e -> moveCircles(circle, temp))
//            );
//            timelineAnimation.setCycleCount(Timeline.INDEFINITE);
//            timelineAnimation.play();
        }


        stage.setTitle("2-D shape animation Scene Graph");
        stage.setScene(scene);
        stage.show();
    }

    private void  moveCircles (Circle ellipse, int i) {
        ellipse.setCenterX(ellipse.getCenterX() + dx[i]);
        ellipse.setCenterY(ellipse.getCenterY() + dy[i]);

        if (ellipse.getCenterX() + ellipse.getRadius() > 600 || ellipse.getCenterX() - ellipse.getRadius() < 0) dx[i] = -dx[i];

    }


    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(50) / 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}