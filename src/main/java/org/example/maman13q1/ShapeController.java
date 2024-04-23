package org.example.maman13q1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ShapeController {
    @FXML
    private Canvas conv;
    @FXML
    private RadioButton black;

    @FXML
    private RadioButton green;

    @FXML
    private RadioButton red;

    @FXML
    private RadioButton yellow;

    @FXML
    private RadioButton blue;

    @FXML
    private RadioButton pink;

    @FXML
    private RadioButton Full;

    @FXML
    private RadioButton blank;

    @FXML
    private RadioButton line;

    @FXML
    private RadioButton Rectangle;

    @FXML
    private RadioButton Circle;

    @FXML
    private ToggleGroup fillToggleGroup;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private ToggleGroup shapeToggleGroup;
    @FXML
    private GraphicsContext gc;
    private double startX, startY;

    @FXML
    void CirclePressed(ActionEvent event) {

    }

    @FXML
    void ClearPressed(ActionEvent event) {
        gc.clearRect(0, 0, conv.getWidth(), conv.getHeight());
    }

    @FXML
    void RectanglePressed(ActionEvent event) {

    }

    @FXML
    void blackPressed(ActionEvent event) {

    }

    @FXML
    void blankPressed(ActionEvent event) {

    }

    @FXML
    void bluePressed(ActionEvent event) {

    }

    @FXML
    void fullPressed(ActionEvent event) {

    }

    @FXML
    void greenPressed(ActionEvent event) {

    }

    @FXML
    void linePressed(ActionEvent event) {

    }

    @FXML
    void pinkPressed(ActionEvent event) {

    }

    @FXML
    void redPressed(ActionEvent event) {

    }

    @FXML
    void undoPressed(ActionEvent event) {

    }

    @FXML
    void yellowPressed(ActionEvent event) {

    }

    private void handleMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
    }


    private void handleMouseReleased(MouseEvent event) {
        double endX = event.getX();
        double endY = event.getY();
        double size = 50;
        if (line.isSelected()) {
            drawLine(startX, startY, endX, endY);
        } else if (Rectangle.isSelected()) {
            drawRectangle(startX, startY, endX, endY);
        } else if (Circle.isSelected()) {
            drawCircle(startX, startY, endX, endY);
        }
    }

    private void drawLine(double startX, double startY, double endX, double endY) {
        gc.setStroke(getSelectedColor());
        gc.setLineWidth(2);
        gc.strokeLine(startX, startY, endX, endY);

    }

    private void drawRectangle(double startX, double startY, double endX, double endY) {
        gc.setStroke(getSelectedColor());
        gc.setLineWidth(2);
        double rectWidth = Math.abs(endX - startX);
        double rectHeight = Math.abs(endY - startY);

        double rectX = Math.min(startX, endX);
        double rectY = Math.min(startY, endY);
        if (fillToggleGroup.getSelectedToggle() == Full) {
            gc.setFill(getSelectedColor());
            gc.fillRect(rectX, rectY, rectWidth, rectHeight);
        }
        gc.strokeRect(rectX, rectY, rectWidth, rectHeight);
    }

    private void drawCircle(double startX, double startY, double endX, double endY) {
        double centerX = (startX + endX) / 2;
        double centerY = (startY + endY) / 2;
        double topX = Math.max(startX, endX);
        double topY = Math.max(startY, endY);
        // Calculate the radius using the distance between the center and top points
        double radius = Math.sqrt(Math.pow(topX - centerX, 2) + Math.pow(topY - centerY, 2));
        drawCircle(centerX, centerY, radius);
    }

    private void drawCircle(double centerX, double centerY, double radius) {
        gc.setStroke(getSelectedColor());
        gc.setLineWidth(2);
        double X = centerX - radius;
        double Y = centerY - radius;
        if (fillToggleGroup.getSelectedToggle() == Full) {
            gc.setFill(getSelectedColor());
            gc.fillOval(X, Y, radius * 2, radius * 2);
        }
        gc.strokeOval(X, Y, radius * 2, radius * 2);
    }

    private Color getSelectedColor() {
        RadioButton selectedColorButton = (RadioButton) colorToggleGroup.getSelectedToggle();
        switch (selectedColorButton.getId()) {
            case "black":
                return Color.BLACK;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.BLUE;
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
            case "pink":
                return Color.PINK;
            default:
                return Color.BLACK;

        }
    }

    @FXML
    void initialize() {
        // Initialize GraphicsContext
        gc = conv.getGraphicsContext2D();
        conv.setOnMousePressed(this::handleMousePressed);
        conv.setOnMouseReleased(this::handleMouseReleased);
        // Set up ToggleGroups
        fillToggleGroup = new ToggleGroup();
        Full.setToggleGroup(fillToggleGroup);
        blank.setToggleGroup(fillToggleGroup);
        colorToggleGroup = new ToggleGroup();
        red.setToggleGroup(colorToggleGroup);
        blue.setToggleGroup(colorToggleGroup);
        black.setToggleGroup(colorToggleGroup);
        green.setToggleGroup(colorToggleGroup);
        pink.setToggleGroup(colorToggleGroup);
        yellow.setToggleGroup(colorToggleGroup);
        shapeToggleGroup = new ToggleGroup();
        Circle.setToggleGroup(shapeToggleGroup);
        Rectangle.setToggleGroup(shapeToggleGroup);
        line.setToggleGroup(shapeToggleGroup);
        // Select default toggles
        fillToggleGroup.selectToggle(Full);
        colorToggleGroup.selectToggle(black);
        shapeToggleGroup.selectToggle(line);
    }
}
