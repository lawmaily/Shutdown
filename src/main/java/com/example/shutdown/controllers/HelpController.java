package com.example.shutdown.controllers;

import com.example.shutdown.services.HelpService;
import com.example.shutdown.views.HelpView;

public class HelpController {
    private HelpView helpView;
    private HelpService helpService;

    public HelpController(HelpView view) {
        this.helpView = view;

        this.helpService = new HelpService(view.getText());

        setupEventHandlers();
    }

    private void setupEventHandlers() {
        helpView.getCloseButton().setOnAction(e -> helpView.close());
    }


}
