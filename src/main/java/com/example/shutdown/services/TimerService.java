package com.example.shutdown.services;

import javafx.scene.text.Text;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerService {
    private Text timerText;
    private int remainingSeconds;
    private ScheduledExecutorService scheduler;
    private SliderUpdater sliderUpdater;
    private Runnable onFinishCallback;

    public TimerService(Text timerText) {
        this.timerText = timerText;
    }

    public void setSliderUpdater(SliderUpdater sliderUpdater) {
        this.sliderUpdater = sliderUpdater;
    }

    public void setOnFinishCallback(Runnable onFinishCallback) {
        this.onFinishCallback = onFinishCallback;
    }

    public void updateTimerText(int remainingSeconds) {
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        timerText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public void startTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if (remainingSeconds > 0) {
                remainingSeconds--;
                updateTimerText(remainingSeconds);

                if (sliderUpdater != null) {
                    sliderUpdater.updateSliderPosition(remainingSeconds);
                }
            } else {
                stopTimer();
                if (onFinishCallback != null) {
                    onFinishCallback.run(); // Выполняем коллбэк
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stopTimer() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    public void setTotalSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
        updateTimerText(remainingSeconds);

        if (sliderUpdater != null) {
            sliderUpdater.updateSliderPosition(remainingSeconds);
        }
    }
}