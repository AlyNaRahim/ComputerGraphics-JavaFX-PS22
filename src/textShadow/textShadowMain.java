package textShadow;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class textShadowMain extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Text drop shadow");

        VBox container = new VBox();
        container.setStyle("-fx-background-color: black");
        container.setPadding(new Insets(50, 50, 50, 50));
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);


        Text text = new Text("JavaFX");
        text.setFill(Paint.valueOf("Red"));
        text.setFont(Font.font(null, FontWeight.BOLD, 70));
        text.setEffect(new DropShadow(10, 5.0,5.0,Color.WHITE));
        container.getChildren().add(text);

        Scene scene = new Scene(container, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
