package kaleidoscope;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class kaleidoscopeMain extends Application {
    @Override public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        stage.setTitle("Kaleidoscope");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}