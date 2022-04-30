package com.diploma.controllers;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import com.diploma.nuop_diploma.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginWindowController {

    @FXML
    private Label testLabel;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button submitButton;

    private FormHelper fh;

    @FXML
    protected void onSubmitButtonClick(ActionEvent event) throws Exception {
        if(checkUser(userName.getText(), password.getText())){
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/main-menu-form.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Wrong Username or Password");
            alert.show();
        }
    }

    @FXML
    void initialize(){
        this.fh = new FormHelper();
    }

    private boolean checkUser(String userName, String password) throws Exception {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
            Statement stm = connection.createStatement();
            UserDaoImplementation udi = new UserDaoImplementation(connection, stm);
            User user = udi.getUser(userName, password);

            return user != null;
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}