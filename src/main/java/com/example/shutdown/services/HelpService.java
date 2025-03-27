package com.example.shutdown.services;

import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.Scanner;

public class HelpService {

    private Text text;
    public HelpService(Text text) {
        this.text = text;

        initializeText();
    }

    private void initializeText() {
//        String textFromFile = loadTextFromFile("/help.txt");
//        text.setText(textFromFile);
    }

//    private String loadTextFromFile(String filePath) {
//        InputStream inputStream = getClass().getResourceAsStream(filePath);
//        if (inputStream == null) {
//            throw new RuntimeException("Text help file from resources does not exist.");
//        }
//
//        try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
//            StringBuilder textFromFile = new StringBuilder();
//            while (scanner.hasNextLine()) {
//                textFromFile.append(scanner.nextLine()).append("\n");
//            }
//            return textFromFile.toString();
//        }
//    }

}
