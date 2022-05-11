package com.diploma.controllers;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Button createButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button servicesButton;

    @FXML
    private Button usersButton;

    @FXML
    private Pane viewAllRecordsPane;

    @FXML
    private Pane recordsTablePane;

    @FXML
    private Button workLogsButton;

    private FormHelper fh;

    private User currentSession;

    private String createFormPath;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) recordsTablePane.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/main-menu-form.fxml");
    }

    @FXML
    void onCardsButton(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-cards-table.fxml");
        this.createFormPath = "/forms/create-card-form.fxml";
        this.createButton.setDisable(false);
        this.createButton.setText("Create Card");
    }

    @FXML
    void onClientsButton(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-clients-table.fxml");
        this.createFormPath = "/forms/create-client-form.fxml";
        this.createButton.setDisable(false);
        this.createButton.setText("Create Client");
    }

    @FXML
    void onCreateButtonClick(ActionEvent event) throws SQLException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        fh.getScene(this.createFormPath);
    }

    @FXML
    void onOrdersButton(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-orders-table.fxml");
        this.createFormPath = "/forms/create-order-form.fxml";
        this.createButton.setDisable(false);
        this.createButton.setText("Create Order");
    }

    @FXML
    void onServicesButtonClick(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-services-table.fxml");
        this.createFormPath = "/forms/create-service-form.fxml";
        this.createButton.setDisable(false);
        this.createButton.setText("Create Service");
    }

    @FXML
    void onUsersButtonClick(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-users-table.fxml");
        this.createFormPath = "/forms/create-user-form.fxml";
        this.createButton.setDisable(false);
        this.createButton.setText("Create User");
    }

    @FXML
    void onWorkLogsButtonClick(ActionEvent event) {
        fh.setChildScene(this.recordsTablePane, "/forms/view-all-worklogs-table.fxml");
        this.createButton.setDisable(true);
        this.createButton.setText("Create WorkLog");
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.recordsTablePane.getChildren().clear();
        this.currentSession = FormHelper.currentSession;
        this.usersButton.setVisible(this.currentSession.isAdmin());
        this.workLogsButton.setVisible(this.currentSession.isAdmin());
        this.createFormPath = "";
        this.createButton.setDisable(true);
    }

}
