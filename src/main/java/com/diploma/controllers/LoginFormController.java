package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFormController {

    @FXML
    private Button cancelButton;

    @FXML
    private Pane loginPane;

    @FXML
    private Button okButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;

    private FormHelper fh;

    private UserDaoImplementation udi;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        try{
            String userName = this.userNameField.getText();
            String password = this.passwordField.getText();
            FormHelper.currentSession = udi.getUser(userName, password);
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/main-menu-form.fxml");
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("User not found:\nWrong username or password");
            alert.show();
        }
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.udi = new UserDaoImplementation(FormHelper.connection);
    }

}
