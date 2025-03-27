package com.example.shutdown.controllers;

import com.example.shutdown.services.SettingsService;
import com.example.shutdown.views.SettingsView;

public class SettingsController {


    private SettingsView settingsView;
    private SettingsService settingsService;

    public SettingsController(SettingsView settingsView) {
        this.settingsView = settingsView;
        this.settingsService = new SettingsService();

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        settingsView.getCloseButton().setOnAction(e -> settingsView.close());
        settingsView.getApplyButton().setOnAction(e -> settingsService.saveSettings());
    }


}
