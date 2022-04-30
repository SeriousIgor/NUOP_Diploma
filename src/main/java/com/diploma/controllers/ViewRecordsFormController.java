package com.diploma.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.diploma.helpers.FormHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewRecordsFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button cardsButton;

    @FXML
    private Button clientsButton;

    @FXML
    private Button createButtonClick;

    @FXML
    private Button ordersButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Pane viewAllRecordsPane;

    private FormHelper fh;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/main-menu-form.fxml");
    }

    @FXML
    void initialize() {
        fh = new FormHelper();
    }

}
