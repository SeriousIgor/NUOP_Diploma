package com.diploma.nuop_diploma;

import com.diploma.dao.OrderDao;
import com.diploma.dao.implementation.*;
import com.diploma.helpers.FormHelper;
import com.diploma.models.*;
import com.diploma.models.enumeration.WorkLogAction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FormHelper helper = new FormHelper();
        helper.startPrimaryForm("/forms/create-client-form.fxml", "Main Form");
    }

    public static void main(String[] args) {
        launch();
    }
}