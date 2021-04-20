package threeDAnimation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import java.security.SecureRandom;

public class threeDController {
    @FXML private Sphere sphere;
    @FXML private Pane pane;
    private int counter = 0;

    // set the material for each 3D shape
    public void initialize() {
        SecureRandom random = new SecureRandom();

        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int randShape = random.nextInt(3);
                        double randWidth = random.nextDouble() * 100;
                        double randHeight = random.nextDouble() * 100;
                        double randDepth = random.nextDouble() * 100;

                        Bounds bounds = pane.getBoundsInLocal();

                        double randX = randWidth + random.nextInt((int) bounds.getWidth()/2);
                        double randY = randHeight + random.nextInt((int)(bounds.getHeight() / 2));
                        double randZ = randDepth + random.nextInt((int) (400 - randDepth));

                        double randRotate = random.nextDouble() * 360;

                        PhongMaterial phongMaterial = new PhongMaterial();
                        phongMaterial.setDiffuseColor(
                                Color.rgb(
                                        random.nextInt(256),
                                        random.nextInt(256),
                                        random.nextInt(256),
                                        (double) random.nextInt(70) / 100));
                                //new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(),1));
                        MyCylinder cylinder = new MyCylinder(randWidth, randHeight);
                        cylinder.setTranslateX(randX);
                        cylinder.setTranslateY(randY);
                        cylinder.setTranslateZ(randZ);
                        cylinder.setRotationAxis(new Point3D(1,1,1));
                        cylinder.setRotate(randRotate);
                        cylinder.setMaterial(phongMaterial);
                        pane.getChildren().add(cylinder);
                        cylinder.animate(pane);
                    }
                })
        );

        timelineAnimation.setCycleCount(25);
        timelineAnimation.play();
    }
}