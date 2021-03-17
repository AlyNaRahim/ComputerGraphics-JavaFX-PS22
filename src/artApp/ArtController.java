package artApp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.text.NumberFormat;
import java.util.Stack;

public class ArtController {
    // Defined variables
    @FXML
    private RadioButton ovalRadioButton;
    @FXML
    private RadioButton rectangleRadioButton;
    @FXML
    private RadioButton lineRadioButton;
    @FXML
    private RadioButton eraserRadioButton;
    @FXML
    private Slider eraserSlider;
    @FXML
    private TextField penEraserSizeTextField;
    @FXML
    private RadioButton strokeRadioButton;
    @FXML
    private RadioButton fillRadioButton;
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;
    @FXML
    private Rectangle colorShow;
    @FXML
    private Button undoButton;
    @FXML
    private Button clearButton;
    @FXML
    private Canvas canvasArea;


    private GraphicsContext graphicsContext;
    Line line = new Line();
    Rectangle rectangle = new Rectangle();
    Ellipse oval = new Ellipse();
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    Stack<Shape> drawingHistory = new Stack();

    public void initialize() {
        // Selecting shapes/ eraser
        canvasArea.setOnMousePressed(mouseEvent -> {
            if (ovalRadioButton.isSelected()) {
                oval.setCenterX(mouseEvent.getX());
                oval.setCenterY(mouseEvent.getY());
            } else if (rectangleRadioButton.isSelected()) {
                rectangle.setX(mouseEvent.getX());
                rectangle.setY(mouseEvent.getY());
            } else if (lineRadioButton.isSelected()) {
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
            } else if (eraserRadioButton.isSelected()) {
                eraseFunction(mouseEvent.getX(), mouseEvent.getY());
            }
        });
        canvasArea.setOnMouseDragged(mouseEvent -> {
            if (eraserRadioButton.isSelected()) eraseFunction(mouseEvent.getX(), mouseEvent.getY());
        });


        // Pen or Eraser size (selection and Number display)
        graphicsContext = canvasArea.getGraphicsContext2D();
        penEraserSizeTextField.textProperty().bindBidirectional(eraserSlider.valueProperty(), NumberFormat.getNumberInstance());
        penEraserSizeTextField.textProperty()
                .addListener((args, oldValue, newValue) -> {
                    int val = (int) eraserSlider.getValue();
                    penEraserSizeTextField.setText(String.valueOf(val));

                });

        // Color Slider
        redSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    red = newValue.intValue();
                    colorShow.setFill(Color.rgb(red, green, blue));
                }
        );
        greenSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    green = newValue.intValue();
                    colorShow.setFill(Color.rgb(red, green, blue));
                }
        );
        blueSlider.valueProperty().addListener(
                (ov, oldValue, newValue) -> {
                    blue = newValue.intValue();
                    colorShow.setFill(Color.rgb(red, green, blue));
                }
        );

        // Drawing shape
        canvasArea.setOnMouseReleased(mouseEvent -> {

            graphicsContext.setStroke(Color.rgb(red, green, blue));

            if (lineRadioButton.isSelected()) {
                line.setStrokeWidth(eraserSlider.getValue());
                line.setFill(Color.rgb(red, green, blue));
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
                graphicsContext.setLineWidth(eraserSlider.getValue());

                graphicsContext.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                Line tempLine = new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                tempLine.setStroke(Color.rgb(red, green, blue));
                tempLine.setStrokeWidth(line.getStrokeWidth());
                drawingHistory.push(tempLine);
            } else if (rectangleRadioButton.isSelected()) {
                lineRadioButton.disabledProperty();
                rectangle.setWidth(Math.abs((mouseEvent.getX() - rectangle.getX())));
                rectangle.setHeight(Math.abs((mouseEvent.getY() - rectangle.getY())));
                rectangle.setX(Math.min(rectangle.getX(), mouseEvent.getX()));
                rectangle.setY(Math.min(rectangle.getY(), mouseEvent.getY()));
                if (fillRadioButton.isSelected() == true) {
                    rectangle.setFill(Color.rgb(red, green, blue));
                } else {
                    rectangle.setFill(Color.rgb(225, 225, 225));
                    rectangle.setStroke(Color.rgb(red, green, blue));
                }
                graphicsContext.setFill(Color.rgb(red, green, blue));
                graphicsContext.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                graphicsContext.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                Rectangle tempRect = new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                tempRect.setFill(Color.rgb(red, green, blue));
                drawingHistory.push(tempRect);

            } else if (ovalRadioButton.isSelected()) {
                oval.setRadiusX(Math.abs(mouseEvent.getX() - oval.getCenterX()));
                oval.setRadiusY(Math.abs(mouseEvent.getY() - oval.getCenterY()));
                oval.setCenterX(Math.min(oval.getCenterX(), mouseEvent.getX()));
                oval.setCenterY(Math.min(oval.getCenterY(), mouseEvent.getY()));
                if (fillRadioButton.isSelected()) {
                    oval.setFill(Color.rgb(red, green, blue));
                } else {
                    oval.setFill(Color.rgb(225, 225, 225));
                    oval.setStroke(Color.rgb(red, green, blue));
                }
                graphicsContext.setFill(Color.rgb(red, green, blue));
                graphicsContext.fillOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                graphicsContext.strokeOval(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                Ellipse tempOval = new Ellipse(oval.getCenterX(), oval.getCenterY(), oval.getRadiusX(), oval.getRadiusY());
                tempOval.setFill(Color.rgb(red, green, blue));
                drawingHistory.push(tempOval);
            } else if (eraserRadioButton.isSelected()) eraseFunction(mouseEvent.getX(), mouseEvent.getY());

        });
    }

    @FXML
    void onClickClear(ActionEvent event) {
        graphicsContext.clearRect(0, 0, canvasArea.getWidth(), canvasArea.getHeight());
        drawingHistory.clear();
    }

    @FXML
    void onClickUndo(ActionEvent event) {
        if (!drawingHistory.isEmpty()) {
            drawingHistory.pop();
            graphicsContext.clearRect(0, 0, canvasArea.getWidth(), canvasArea.getHeight());
            drawShapes(drawingHistory);
        }
    }

    private void eraseFunction(double x, double y) {
        double size = eraserSlider.getValue();
        graphicsContext.clearRect(x - size / 2, y - size / 2, size, size);
    }

    private void drawShapes(Stack<Shape> shapes) {
        for (Shape s: shapes) {
            if(s.getClass() == Line.class) {
                Line temp = (Line) s;
                graphicsContext.setLineWidth(temp.getStrokeWidth());
                graphicsContext.setStroke(temp.getStroke());
                graphicsContext.strokeLine(temp.getStartX(), temp.getStartY(), temp.getEndX(), temp.getEndY());
            }
            else if(s.getClass() == Rectangle.class) {
                Rectangle temp = (Rectangle) s;
                graphicsContext.setLineWidth(temp.getStrokeWidth());
                if (fillRadioButton.isSelected()){
                    graphicsContext.setFill(temp.getFill());
                }
                graphicsContext.setStroke(temp.getStroke());
                graphicsContext.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
                graphicsContext.strokeRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
            }
            else if(s.getClass() == Ellipse.class) {
                Ellipse temp = (Ellipse) s;
                graphicsContext.setStroke(temp.getStroke());
                graphicsContext.setLineWidth(temp.getStrokeWidth());
                graphicsContext.setFill(temp.getFill());
                graphicsContext.fillOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
                graphicsContext.strokeOval(temp.getCenterX(), temp.getCenterY(), temp.getRadiusX(), temp.getRadiusY());
            }
        }
    }
    private boolean isInShapeArea(Shape s, double x, double y) {
        if(s.getClass() == Line.class) {
            line = (Line) s;
            if (Math.abs(Math.pow(Math.pow(line.getStartX() - x, 2) + Math.pow(line.getStartY() - y, 2), .5) +
                    Math.pow(Math.pow(line.getEndX() - x, 2) + Math.pow(line.getEndY() - y, 2), .5) -
                    Math.pow(Math.pow(line.getEndX() - line.getStartX(), 2) + Math.pow(line.getEndY() - line.getStartY(), 2), .5)) < 5)
                return true;
        } else if (s.getClass() == Rectangle.class) {
            rectangle = (Rectangle) s;
            if ((x >= rectangle.getX()) && (x <= (rectangle.getX() + rectangle.getWidth()))
                    && (y >= rectangle.getY()) && (y <= (rectangle.getY() + rectangle.getHeight())))
                return true;
        } else if (s.getClass() == Ellipse.class) {
            oval = (Ellipse) s;
            if (((Math.pow((x - oval.getCenterX() - oval.getRadiusX() / 2), 2) / Math.pow(oval.getRadiusX() / 2, 2)) +
                    (Math.pow((y - oval.getCenterY() - oval.getRadiusY() / 2), 2) / Math.pow(oval.getRadiusY() / 2, 2))) <= 1)
                return true;
        }
        return false;
    }

    @FXML
    void onClickExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
