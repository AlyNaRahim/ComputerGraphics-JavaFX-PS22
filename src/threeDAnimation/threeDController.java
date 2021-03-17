package threeDAnimation;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.security.SecureRandom;


public class threeDController {
    @FXML Pane pane;

    private SecureRandom random = new SecureRandom();

    private int num;
    private int[] dx;
    private int[] dy;

    public void initialize() {
        num = random.nextInt(50) + 12;

        dx = new int[num];
        dy = new int[num];

        for (int i = 0; i < num; i++) {
            Sphere sphere = new Sphere();
            sphere.setTranslateX(random.nextInt(300) + 150);
            sphere.setTranslateY(random.nextInt(200) + 150);
            sphere.setRadius(random.nextInt(100));


            pane.getChildren().add(sphere);
            dx[i] = 1 + random.nextInt(5);
            dy[i] = 1 + random.nextInt(5);
        }
        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(10), event -> moveCircles())
        );
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }

    private void moveCircles() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Sphere sphere = (Sphere) pane.getChildren().get(i);
            sphere.setTranslateX(sphere.getLayoutX() + dx[i]);
            sphere.setTranslateY(sphere.getLayoutY() + dy[i]);
            if (sphere.getTranslateX() + sphere.getRadius() > pane.getWidth() || sphere.getTranslateX() - sphere.getRadius() < 0) dx[i] = -dx[i];
            if (sphere.getTranslateY() + sphere.getRadius() > pane.getHeight() || sphere.getTranslateY() - sphere.getRadius() < 0) dy[i] = -dy[i];
        }
    }


    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(70) / 100);
    }
}

