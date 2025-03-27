package com.example.shutdown.services;

public class SystemService {

    public void shutdownComputer() {
        try {
            System.out.println("task complete");
            Runtime.getRuntime().exec("shutdown /s /t 0 /f");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
