package sceneGraphDynamicArt;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.security.SecureRandom;


public class DynamicArtController {
    @FXML Pane pane;
    private final SecureRandom random = new SecureRandom();
    private int n;
    private int[] dxC;
    private int[] dyC;
    private int[] dyE;
    private int[] dxE;

    public void initialize() {
        n = random.nextInt(50) + 10;
        dxC = new int[n];
        dyC = new int[n];
        dxE = new int[n];
        dyE = new int[n];

        for (int i = 0; i < n; i++) {
            Circle circle = new Circle();
            circle.setCenterX(random.nextInt(500) + 201);
            circle.setCenterY(random.nextInt(300) + 201);
            circle.setRadius(random.nextInt(100));
            circle.setFill(randomColor());
            circle.setStrokeWidth(random.nextInt(20));
            circle.setStroke(randomColor());
            pane.getChildren().add(circle);
            dxC[i] = 1 + random.nextInt(5);
            dyC[i] = 1 + random.nextInt(5);


            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(random.nextInt(500) + 201);
            ellipse.setCenterY(random.nextInt(300) + 201);
            ellipse.setRadiusX(random.nextInt(100));
            ellipse.setRadiusY(random.nextInt(100));
            ellipse.setFill(randomColor());
            ellipse.setStrokeWidth(random.nextInt(20));
            ellipse.setStroke(randomColor());
            pane.getChildren().add(ellipse);
            dxC[i] = 1 + random.nextInt(5);
            dyC[i] = 1 + random.nextInt(5);
            int temp = i;
            Timeline timelineAnimation = new Timeline(
                    new KeyFrame(Duration.millis(10), e -> moveCircles(circle,ellipse, temp))
            );
            timelineAnimation.setCycleCount(Timeline.INDEFINITE);
            timelineAnimation.play();


        }
    }
    private void moveCircles(Circle e,Ellipse c, int i) {
        c.setCenterX(c.getCenterX() + dxC[i]);
        c.setCenterY(c.getCenterY() + dyC[i]);

        e.setCenterX(e.getCenterX()+dxE[i]);
        e.setCenterY(e.getCenterX()+dyE[i]);


        if (c.getCenterX() + c.getRadiusX() > pane.getWidth() || c.getCenterX() - c.getRadiusX() < 0) dxC[i] = -dxC[i];
        if (c.getCenterY() + c.getRadiusY() > pane.getHeight() || c.getCenterY() - c.getRadiusY() < 0) dyC[i] = -dyC[i];

        if (e.getCenterX() + e.getRadius() > pane.getWidth() || e.getCenterX() - e.getRadius() < 0) dxE[i] = -dxE[i];
        if (e.getCenterY() + e.getRadius() > pane.getHeight() || e.getCenterY() - e.getRadius() < 0) dyE[i] = -dyE[i];

    }


    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(101) / 100);
    }

}
