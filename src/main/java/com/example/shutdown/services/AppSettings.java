package com.example.shutdown.services;

public class AppSettings {
    private String theme;
    private String resolution;
    private double maxTimerValue;

    public double getMaxTimerValue() {
        return maxTimerValue;
    }

    public void setMaxTimerValue(double maxTimerValue) {
        this.maxTimerValue = maxTimerValue;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }



    @Override
    public String toString() {
        return "AppSettings{" +
                "theme='" + theme + '\'' +
                ", resolution='" + resolution + '\'' +
                ", maxTimerValue=" + maxTimerValue +
                '}';
    }
}