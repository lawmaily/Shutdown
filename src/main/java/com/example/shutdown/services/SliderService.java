package com.example.shutdown.services;

import com.example.shutdown.components.Slider;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class SliderService implements SliderUpdater {
    private TimerService timerService;
    private Arc arc;
    private Arc filledArc;
    private Circle handle;

    private final double MIN_VALUE = 0;
    private final double MAX_VALUE = 21600;

    public double degrees = 0;

    public SliderService(Slider slider, TimerService timerService) {
        this.arc = slider.getArc();
        this.filledArc = slider.getFilledArc();
        this.handle = slider.getHandle();
        this.timerService = timerService;

        initializeHandlers();
    }

    private void initializeHandlers() {
        handle.setOnMouseDragged(event -> {
            double x = event.getX();
            double y = event.getY();

            double angle = Math.atan2(y - arc.getCenterY(), x - arc.getCenterX());

            double newDegrees = Math.toDegrees(angle + Math.PI / 2);
            if (newDegrees < 0) {
                newDegrees += 360;
            }

            if (this.degrees == 0 && newDegrees != 0) {
                if (newDegrees > this.degrees && newDegrees < 10) {
                    this.degrees = newDegrees;
                }
            } else if (this.degrees == 359.9 && newDegrees != 359.9) {
                if (newDegrees < this.degrees && newDegrees > 358) {
                    this.degrees = newDegrees;
                }
            } else {
                if (this.degrees < 90 && newDegrees > 270) {
                    this.degrees = 0;
                    return;
                } else if (this.degrees > 270 && newDegrees < 90) {
                    this.degrees = 359.9;
                    return;
                }
                this.degrees = newDegrees;
            }

            updateHandlePosition(this.degrees);
            timerService.setTotalSeconds(getValue());
        });
    }

    private void updateHandlePosition(double degrees) {
        double radians = Math.toRadians(degrees - 90);

        double handleX = arc.getCenterX() + arc.getRadiusX() * Math.cos(radians);
        double handleY = arc.getCenterY() + arc.getRadiusY() * Math.sin(radians);

        handle.setCenterX(handleX);
        handle.setCenterY(handleY);

        updateFilledArc(this.degrees);
    }

    private void updateFilledArc(double degrees) {
        filledArc.setLength(-degrees);
    }

    @Override
    public void updateSliderPosition(int remainingSeconds) {
        this.degrees = (remainingSeconds - MIN_VALUE) / (MAX_VALUE - MIN_VALUE) * 360;
        updateHandlePosition(this.degrees);
    }

    public void setDisabled(boolean disabled) {
        handle.setDisable(disabled);
    }

    public int getValue() {
        double value = (this.degrees / 360) * (MAX_VALUE - MIN_VALUE) + MIN_VALUE;
        if (this.degrees > 359.5) {
            value = MAX_VALUE;
        }
        return (int) value;
    }
}