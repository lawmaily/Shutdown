package com.example.shutdown;

import com.example.shutdown.controllers.HelpController;
import com.example.shutdown.controllers.MainController;
import com.example.shutdown.controllers.SettingsController;
import com.example.shutdown.services.ScreenService;
import com.example.shutdown.views.HelpView;
import com.example.shutdown.views.MainView;
import com.example.shutdown.views.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        ScreenService screenService = new ScreenService();
        System.out.println(screenService.windowWidth + " " + screenService.windowHeight);

        MainView mainView = new MainView(screenService.windowWidth, screenService.windowHeight);
        HelpView helpView = new HelpView(screenService.windowWidth, screenService.windowHeight);
        SettingsView settingsView = new SettingsView(screenService.windowWidth, screenService.windowHeight);

        MainController mainController = new MainController(mainView, helpView, settingsView);
        HelpController helpController = new HelpController(helpView);
        SettingsController settingsController = new SettingsController(settingsView);


        Image icon = new Image(getClass().getResourceAsStream("/img/shkiper.png"));
        Scene scene = new Scene(mainView.getPane(), screenService.windowWidth, screenService.windowHeight);
        scene.getStylesheets().add(getClass().getResource("/styles/darkTheme.css").toExternalForm());
        stage.setTitle("Shkiper");
        stage.setResizable(false);
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
