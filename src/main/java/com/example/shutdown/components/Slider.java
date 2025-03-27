package com.example.shutdown.components;

import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Slider extends Group {
    private Arc arc;
    private Arc filledArc;
    private Circle handle;

    private ScaleTransition scaleUp;
    private ScaleTransition scaleDown;

    public Slider(double centerX, double centerY, double radius, double startAngle, double length) {
        arc = new Arc(centerX, centerY, radius, radius, startAngle, length);
        arc.setType(ArcType.OPEN);
        arc.setStrokeWidth(10);
        arc.setFill(null);
        arc.getStyleClass().add("arc");

        filledArc = new Arc(centerX, centerY, radius, radius, startAngle, 0);
        filledArc.setStrokeWidth(10);
        filledArc.setType(ArcType.OPEN);
        filledArc.setFill(null);
        filledArc.getStyleClass().add("filledArc");

        handle = new Circle(centerX, centerY - radius, 15);
        handle.getStyleClass().add("handle");

        getChildren().addAll(arc, filledArc, handle);

        addHandleAnimation();
    }

    private void addHandleAnimation() {
        scaleUp = new ScaleTransition(Duration.millis(200), handle);
        scaleUp.setFromX(1.0);
        scaleUp.setFromY(1.0);
        scaleUp.setToX(1.2);
        scaleUp.setToY(1.2);

        scaleDown = new ScaleTransition(Duration.millis(200), handle);
        scaleDown.setFromX(1.2);
        scaleDown.setFromY(1.2);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);
    }

    public void play() {
        scaleUp.play();
    }

    public void stop() {
        scaleDown.play();
    }

    public Arc getArc() {
        return arc;
    }

    public Circle getHandle() {
        return handle;
    }

    public Arc getFilledArc() {
        return filledArc;
    }

}

