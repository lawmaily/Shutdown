package com.example.shutdown.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class HelpView {

    private double windowWidth;
    private double windowHeight;

    private Stage helpStage;
    private AnchorPane helpPane;
    private Scene helpScene;
    private Button closeButton;
    private Text text;


    public HelpView(double windowWidth, double windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        helpStage = new Stage();
        helpPane = new AnchorPane();
        helpScene = new Scene(helpPane);
        closeButton = new Button("Close");
        text = new Text();

        initializeUI();
    }

    private void initializeUI() {
        helpPane.getStyleClass().add("pane");
        helpStage.setTitle("Help");
        helpStage.setResizable(false);
        helpStage.setWidth(windowWidth * 0.85);
        helpStage.setHeight(windowHeight * 0.77);
        helpStage.setScene(helpScene);
        helpScene.getStylesheets().add(getClass().getResource("/styles/darkTheme.css").toExternalForm());

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


        text.setWrappingWidth(windowWidth * 0.79);
        text.getStyleClass().add("helpText");
        AnchorPane.setTopAnchor(text, windowHeight * 0.007);
        AnchorPane.setLeftAnchor(text, windowWidth * 0.01);
        AnchorPane.setRightAnchor(text, windowWidth * 0.01);
        AnchorPane.setBottomAnchor(text, windowHeight * 0.07);

        helpPane.getChildren().addAll(closeButton, text);
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public void show() {
        helpStage.show();
    }

    public void close() {
        helpStage.close();
    }

    public Text getText() {
        return text;
    }
}
