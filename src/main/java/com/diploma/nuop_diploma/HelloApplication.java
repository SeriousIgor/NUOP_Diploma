package com.diploma.nuop_diploma;

import com.diploma.dao.OrderDao;
import com.diploma.dao.implementation.*;
import com.diploma.helpers.FormHelper;
import com.diploma.models.*;
import com.diploma.models.enumeration.OrderStatus;
import com.diploma.models.enumeration.WorkLogAction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FormHelper helper = new FormHelper();
        helper.startPrimaryForm("/forms/main-menu-form.fxml", "Main Form");
    }

    public static void main(String[] args) {
        launch();
    }
}