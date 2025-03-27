package com.example.shutdown.controllers;

import com.example.shutdown.services.SliderService;
import com.example.shutdown.services.SystemService;
import com.example.shutdown.services.TimerService;
import com.example.shutdown.views.HelpView;
import com.example.shutdown.views.MainView;
import com.example.shutdown.views.SettingsView;
import javafx.scene.control.Button;

public class MainController {
    private MainView view;
    private SliderService sliderService;
    private TimerService timerService;
    private SystemService systemService;
    private HelpView helpView;
    private SettingsView settingsView;

    private boolean isTimerRunning = false;

    public MainController(MainView view, HelpView helpView, SettingsView settingsView) {
        this.view = view;
        this.helpView = helpView;
        this.settingsView = settingsView;

        this.timerService = new TimerService(view.getTimerText());
        this.sliderService = new SliderService(view.getSlider(), timerService);
        timerService.setSliderUpdater(sliderService);
        systemService = new SystemService();

        timerService.setOnFinishCallback(() -> {
            systemService.shutdownComputer();
        });

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        view.getHelpItem().setOnAction(e -> helpView.show());
        view.getSettingsItem().setOnAction(e -> settingsView.show());

        view.getStartStopButton().setOnAction(e -> handleStartStopButton(view.getStartStopButton()));
        view.getStartStopButton().setOnMouseReleased(e -> handleStartStopButtonReleased());

        view.getSlider().getHandle().setOnMousePressed(e -> view.getSlider().play());
        view.getSlider().getHandle().setOnMouseReleased(e -> view.getSlider().stop());
    }

    private void handleStartStopButton(Button startStopButton) {
        if (isTimerRunning) {
            timerService.stopTimer();
//            sliderService.setDisabled(false);
            startStopButton.setText("Start");
        } else {
            timerService.startTimer();
//            sliderService.setDisabled(true);
            startStopButton.setText("Stop");
        }

        isTimerRunning = !isTimerRunning;
    }

    private void handleStartStopButtonReleased() {
        if (isTimerRunning) {
            timerService.startTimer();
        }
    }
}