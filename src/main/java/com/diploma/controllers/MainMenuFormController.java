package com.diploma.controllers;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import com.diploma.models.Service;
import com.diploma.nuop_diploma.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
public class MainMenuFormController {
    @FXML
    private Button createOrderButton;

    @FXML
    private Button createClientButton;

    private FormHelper fh;

    @FXML
    protected void onCreateClientButtonClick() throws IOException {
        fh.getScene("/forms/create-client-form.fxml");

    }

    @FXML
    protected void onCreateOrderButtonClick(){
        fh.getScene("/forms/create-order-form.fxml");
    }

    @FXML
    void initialize() {
        fh = new FormHelper();
    }
}
