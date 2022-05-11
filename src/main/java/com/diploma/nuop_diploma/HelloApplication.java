package com.diploma.nuop_diploma;

import com.diploma.dao.implementation.*;
import com.diploma.helpers.FormHelper;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FormHelper fh = new FormHelper();
        FormHelper.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        UserDaoImplementation udi = new UserDaoImplementation(FormHelper.connection);
        String path;
        try{
            udi.getUsers();
            path = "/forms/login-form.fxml";
        } catch (Exception ex){
            path = "/forms/create-user-form.fxml";
        }
        fh.startPrimaryForm(path);
    }

    public static void main(String[] args) {
        launch();
    }
}