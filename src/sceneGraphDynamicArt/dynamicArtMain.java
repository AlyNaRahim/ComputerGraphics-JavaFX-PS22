package sceneGraphDynamicArt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.SecureRandom;

public class dynamicArtMain extends Application {
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

        for (int i = 0; i < num; i++) {
            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(random.nextInt(300) + 150);
            ellipse.setCenterY(random.nextInt(200) + 150);
            ellipse.setRadiusX(random.nextInt(100));
            ellipse.setRadiusY(random.nextInt(100));
            ellipse.setFill(randomColor());
            ellipse.setStrokeWidth(random.nextInt(10));
            ellipse.setStroke(randomColor());

            root.getChildren().add(ellipse);
            dx[i] = 1 + random.nextInt(5);
            dy[i] = 1 + random.nextInt(5);
            int temp = i;
            Timeline timelineAnimation = new Timeline(
                    new KeyFrame(Duration.millis(10), e -> moveCircles(ellipse, temp))
            );
            timelineAnimation.setCycleCount(Timeline.INDEFINITE);
            timelineAnimation.play();
        }


        stage.setTitle("2-D shape animation Scene Graph");
        stage.setScene(scene);
        stage.show();
    }

    private void  moveCircles (Ellipse ellipse, int i) {
        ellipse.setCenterX(ellipse.getCenterX() + dx[i]);
        ellipse.setCenterY(ellipse.getCenterY() + dy[i]);

        if (ellipse.getCenterX() + ellipse.getRadiusX() > 600 || ellipse.getCenterX() - ellipse.getRadiusX() < 0) dx[i] = -dx[i];
        if (ellipse.getCenterY() + ellipse.getRadiusY() > 600 || ellipse.getCenterY() - ellipse.getRadiusY() < 0) dy[i] = -dy[i];
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
