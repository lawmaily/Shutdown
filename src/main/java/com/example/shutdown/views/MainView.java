package com.example.shutdown.views;

import com.example.shutdown.components.Slider;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainView {

    private double windowWidth;
    private double windowHeight;

    private AnchorPane pane;
    private Button startStopButton;
    private Text timerText;
    private MenuBar menuBar;
    private MenuItem settingsItem;
    private MenuItem helpItem;

    private Slider slider;


    public MainView(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        pane = new AnchorPane();
        startStopButton = new Button("Start");
        timerText = new Text();
        menuBar = new MenuBar();
        settingsItem = new MenuItem("Settings");
        helpItem = new MenuItem("Help");
        slider = new Slider(windowWidth * 0.5, windowHeight * 0.435, windowWidth * 0.37, 90, 360);

        initializeUI();
    }

    private void initializeUI() {
        pane.getStyleClass().add("pane");
        initializeMenuBar();
        initializeTimerText();
        initializeStartStopButton();

        pane.getChildren().add(slider);
    }

    private void initializeTimerText() {
        timerText.getStyleClass().add("timerText");
        timerText.setText("00:00:00");
        timerText.setStyle("-fx-font-size: " + windowWidth * 0.177 + ";" +
                "-fx-font-family: Courier New;");
        AnchorPane.setTopAnchor(timerText, windowHeight * 0.34);
        AnchorPane.setLeftAnchor(timerText, windowWidth * 0.175);

        pane.getChildren().add(timerText);
    }

    private void initializeMenuBar() {
        Menu menu = new Menu("Menu");
        menu.getItems().addAll(settingsItem, helpItem);
        menuBar.getMenus().add(menu);
        AnchorPane.setTopAnchor(menuBar, 0.0);
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);

        pane.getChildren().add(menuBar);
    }

    private void initializeStartStopButton() {
        startStopButton.getStyleClass().add("button");
        startStopButton.setStyle("-fx-pref-height:" + windowHeight * 0.08 + ";" +
                        "-fx-pref-width:" + windowWidth * 0.4 + ";" +
                        "-fx-font-size: " + windowWidth * 0.042 + ";" +
                        "-fx-font-family: 'Courier New';" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: " + windowWidth * 0.02 + " " + windowWidth * 0.04 + ";" +
                        "-fx-border-radius: " + windowWidth * 0.04 + ";" +
                        "-fx-background-radius: " + windowWidth * 0.04 + ";");
        AnchorPane.setBottomAnchor(startStopButton, windowHeight * 0.1);
        AnchorPane.setLeftAnchor(startStopButton, windowWidth * 0.30);

        pane.getChildren().add(startStopButton);
    }

    public AnchorPane getPane() {
        return pane;
    }

    public Text getTimerText() {
        return timerText;
    }

    public MenuItem getHelpItem() {
        return helpItem;
    }

    public MenuItem getSettingsItem() {
        return settingsItem;
    }

    public Slider getSlider() {
        return slider;
    }

    public Button getStartStopButton() {
        return startStopButton;
    }
}