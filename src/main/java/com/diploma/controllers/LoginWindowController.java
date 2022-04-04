package com.diploma.controllers;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import com.diploma.nuop_diploma.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    protected void onSubmitButtonClick(ActionEvent event) throws Exception {
        if(checkUser(userName.getText(), password.getText())){
            testLabel.setText("OK");
        } else {
            testLabel.setText("Not OK");
        }
    }

    private boolean checkUser(String userName, String password) throws Exception {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
            Statement stm = connection.createStatement();
            UserDaoImplementation udi = new UserDaoImplementation(connection, stm);
            User user = udi.getUser(userName, password);

            return user != null;
        } catch (Exception e) {
            FormHelper.errorMessage = e.getMessage();
            FormHelper.displayMessageBox();
            return false;
        }
    }
}