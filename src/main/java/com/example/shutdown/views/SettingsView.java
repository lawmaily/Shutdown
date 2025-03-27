package com.example.shutdown.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsView {
    private double windowWidth;

    private double windowHeight;
    private Stage settingsStage;

    private AnchorPane settingsPane;
    private Scene settingsScene;
    private Button closeButton;
    private Button applyButton;
    private Button resetButton;
    private Label resolutionLabel;

    private ComboBox<String> resolutionComboBox;
    private Label sliderBorderLabel;

    private Spinner<Integer> spinner;
    private Label themeLabel;

    private ComboBox<String> themeComboBox;
    public SettingsView(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        settingsStage = new Stage();
        settingsPane = new AnchorPane();
        settingsScene = new Scene(settingsPane);

        closeButton = new Button("Close");
        applyButton = new Button("Apply");
        resetButton = new Button("Reset");

        resolutionLabel = new Label("Your resolution:");
        resolutionComboBox = new ComboBox<>();

        sliderBorderLabel = new Label("Max value:");
        spinner = new Spinner<>(0, 128, 0);

        themeLabel = new Label("Themes:");
        themeComboBox = new ComboBox<>();

        initializeUI();
    }

    private void initializeUI() {
        settingsStage.setTitle("Settings");
        settingsStage.setResizable(false);
        settingsStage.setWidth(windowWidth * 0.85);
        settingsStage.setHeight(windowHeight * 0.77);
        settingsStage.setScene(settingsScene);
        settingsScene.getStylesheets().add(getClass().getResource("/styles/darkTheme.css").toExternalForm());
        settingsPane.getStyleClass().add("pane");

        initializeCloseButton();
        initializeApplyButton();
        initializeResetButton();
        initializeResolution();
        initializeSliderBorder();
        initializeThemes();
    }

    private void initializeResolution() {
        VBox resolutionBox = new VBox();
        resolutionBox.getChildren().addAll(resolutionLabel, resolutionComboBox);
        resolutionBox.getStyleClass().add("settingsBox");
        AnchorPane.setLeftAnchor(resolutionBox, windowWidth * 0.08);
        AnchorPane.setTopAnchor(resolutionBox, windowHeight * 0.06);

        resolutionComboBox.getItems().addAll("Auto" ,"1920x1080", "1280x720", "800x600");

        settingsPane.getChildren().add(resolutionBox);
    }

    private void initializeThemes() {
        VBox themeBox = new VBox();
        themeBox.getChildren().addAll(themeLabel, themeComboBox);
        themeBox.getStyleClass().add("settingsBox");
        AnchorPane.setLeftAnchor(themeBox, windowWidth * 0.45);
        AnchorPane.setTopAnchor(themeBox, windowHeight * 0.06);

        themeComboBox.getItems().addAll("Dark", "Light", "Pink");

        settingsPane.getChildren().add(themeBox);
    }

    private void initializeSliderBorder() {
        VBox sliderBorderBox = new VBox();
        spinner.setEditable(true);
        sliderBorderBox.getStyleClass().add("settingsBox");

        sliderBorderBox.getChildren().addAll(sliderBorderLabel, spinner);
        AnchorPane.setLeftAnchor(sliderBorderBox, windowWidth * 0.08);
        AnchorPane.setTopAnchor(sliderBorderBox, windowHeight * 0.18);

        settingsPane.getChildren().add(sliderBorderBox);
    }

    private void initializeCloseButton() {
        closeButton.getStyleClass().add("smallButton");
        closeButton.setStyle(
                "-fx-pref-height:" + windowWidth * 0.052 + ";" +
                        "-fx-pref-width:" + windowWidth * 0.15 + ";" +
                        "-fx-font-size: " + (windowWidth * 0.029) + "px;" +
                        "-fx-padding: " + (windowWidth * 0.01) + " " + (windowWidth * 0.02) + ";" +
                        "-fx-border-radius: " + (windowWidth * 0.02) + ";" +
                        "-fx-background-radius: " + (windowWidth * 0.02) + ";"
        );
        AnchorPane.setRightAnchor(closeButton, windowWidth * 0.03);
        AnchorPane.setBottomAnchor(closeButton, windowHeight * 0.015);

        settingsPane.getChildren().add(closeButton);
    }

    private void initializeApplyButton() {
        applyButton.getStyleClass().add("smallButton");
        applyButton.setStyle(
                "-fx-pref-height:" + windowWidth * 0.052 + ";" +
                        "-fx-pref-width:" + windowWidth * 0.15 + ";" +
                        "-fx-font-size: " + (windowWidth * 0.029) + "px;" +
                        "-fx-padding: " + (windowWidth * 0.01) + " " + (windowWidth * 0.02) + ";" +
                        "-fx-border-radius: " + (windowWidth * 0.02) + ";" +
                        "-fx-background-radius: " + (windowWidth * 0.02) + ";"
        );
        AnchorPane.setRightAnchor(applyButton, windowWidth * 0.19);
        AnchorPane.setBottomAnchor(applyButton, windowHeight * 0.015);

        settingsPane.getChildren().add(applyButton);
    }

    private void initializeResetButton() {
        resetButton.getStyleClass().add("smallButton");
        resetButton.setStyle(
                "-fx-pref-height:" + windowWidth * 0.052 + ";" +
                        "-fx-pref-width:" + windowWidth * 0.15 + ";" +
                        "-fx-font-size: " + (windowWidth * 0.029) + "px;" +
                        "-fx-padding: " + (windowWidth * 0.01) + " " + (windowWidth * 0.02) + ";" +
                        "-fx-border-radius: " + (windowWidth * 0.02) + ";" +
                        "-fx-background-radius: " + (windowWidth * 0.02) + ";"
        );

        AnchorPane.setRightAnchor(resetButton, windowWidth * 0.35);
        AnchorPane.setBottomAnchor(resetButton, windowHeight * 0.015);

        settingsPane.getChildren().add(resetButton);
    }

    public Button getApplyButton() {
        return applyButton;
    }

    public void show() {
        settingsStage.show();
    }

    public void close() {
        settingsStage.close();
    }

    public Button getCloseButton() {
        return closeButton;
    }
}
