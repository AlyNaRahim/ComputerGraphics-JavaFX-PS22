package rectangleShadowSlider;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class rectangleShadowSliderMain extends Application {
    @Override
    public void start(Stage stage) {

        stage.setTitle("Text drop shadow");

        VBox container = new VBox();
        container.setStyle("-fx-background-color: black;" +
                "-fx-spacing: 20; " +
                "-fx-padding: 50,50,50,50; " +
                "-fx-alignment: center");

        Slider slider = new Slider();

        Rectangle r = new Rectangle(150,75);
        r.setStyle("-fx-effect: dropshadow(gaussian,white,5.0,0,3.0,3.0);" +
                "-fx-fill: blueviolet");
        //text.setEffect(new DropShadow(10, 5.0,5.0,Color.WHITE));
        container.getChildren().addAll(r, slider);

        Scene scene = new Scene(container, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

