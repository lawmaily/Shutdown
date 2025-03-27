package com.example.shutdown.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SettingsService {
    private static final String SETTINGS_FILE = "config/settings.json";
    private AppSettings settings;

    public SettingsService() {
        settings = loadSettings();
    }

    public AppSettings getSettings() {
        return settings;
    }

    public void setSettings(AppSettings settings) {
        this.settings = settings;
    }

    public void saveSettings() {
        ObjectMapper mapper = new ObjectMapper();
        File settingsFile = new File(SETTINGS_FILE);
        File parentDir = settingsFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            System.out.println("json created");
            if (!dirsCreated) {
                System.err.println("Failed to create directories: " + parentDir.getAbsolutePath());
                return;
            }
        }

        try {
            boolean fileCreated = settingsFile.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + settingsFile.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + settingsFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create file: " + settingsFile.getAbsolutePath());
            return;
        }

        try {
            mapper = new ObjectMapper();
            mapper.writeValue(settingsFile, settings);
            System.out.println("Settings saved to: " + settingsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save settings to: " + settingsFile.getAbsolutePath());
        }
    }

    private AppSettings loadSettings() {
//        ObjectMapper mapper = new ObjectMapper();
//        File settingsFile = new File(SETTINGS_FILE);
//
//        // Проверяем, существует ли файл и не пуст ли он
//        if (settingsFile.exists() && settingsFile.length() > 0) {
//            try {
//                return mapper.readValue(settingsFile, AppSettings.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.err.println("Failed to read settings file. Using default settings.");
//            }
//        } else {
//            System.out.println("Settings file not found or is empty. Using default settings.");
//        }
        return createDefaultSettings();
    }

    private AppSettings createDefaultSettings() {
        AppSettings defaultSettings = new AppSettings();
        defaultSettings.setTheme("Dark");
        defaultSettings.setResolution("Auto");
        defaultSettings.setMaxTimerValue(21600);
        return defaultSettings;
    }
}