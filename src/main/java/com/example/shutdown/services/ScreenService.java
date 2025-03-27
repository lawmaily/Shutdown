package com.example.shutdown.services;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenService {

    public double windowWidth;
    public double windowHeight;

    public double screenWidth;
    public double screenHeight;

    public ScreenService() {
        initializeScreenBounds();
        initializeWindowBounds();
    }

    private void initializeScreenBounds() {
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D bounds = primaryScreen.getBounds();
        screenWidth = bounds.getWidth();
        screenHeight = bounds.getHeight();
    }

    private void initializeWindowBounds() {
        windowWidth = screenWidth * 0.25;
        windowHeight = screenHeight * 0.60;
    }

    private void getResolution() {
    }

}
