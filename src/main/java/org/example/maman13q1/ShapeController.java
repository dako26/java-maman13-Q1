package org.example.maman13q1;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class ShapeController {
    @FXML
    private AnchorPane canvas;
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
    private double startX, startY;

    @FXML
    void ClearPressed(ActionEvent event) {
        canvas.getChildren().clear();
    }
    @FXML
    void undoPressed(ActionEvent event) {
        int size = canvas.getChildren().size();
        if (size > 0)
            canvas.getChildren().remove(size - 1);
    }
    private void handleMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
    }


    private void handleMouseReleased(MouseEvent event) {

        double endX = event.getX();
        double endY = event.getY();
        if (endY >= canvas.getMinHeight()  && endX >= canvas.getMinWidth()&&endY <= canvas.getPrefHeight() && endX <= canvas.getPrefWidth()) {
            Shape shape = getShape(endX, endY);
            if (shape != null) {
            shape.setStroke(getSelectedColor());
            shape.setFill(fillToggleGroup.getSelectedToggle() == Full ? getSelectedColor() : Color.TRANSPARENT);
            canvas.getChildren().add(shape);
        }
    }
        }

    private Shape getShape(double endX, double endY) {
        Shape shape = null;
        if (line.isSelected()) {
            shape = new javafx.scene.shape.Line(startX, startY, endX, endY);
            } else if (Rectangle.isSelected()) {
            shape = new Rectangle(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
        }
          else if (Circle.isSelected()) {
            double centerX = (startX + endX)/2 ;
            double centerY = (startY + endY)/2 ;
        // Calculate the radius using the distance between the center and top points
            double radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2)) / 2;
            shape = new Circle(centerX, centerY, radius);
          }
        return shape;
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
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleMousePressed(event);
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleMouseReleased(event);
            }
        });
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
