package com.diploma.nuop_diploma;

import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.dao.implementation.OrderServiceBundleDaoImplementation;
import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.dao.implementation.WorkLogDaoImplementation;
import com.diploma.models.Order;
import com.diploma.models.OrderServiceBundle;
import com.diploma.models.Service;
import com.diploma.models.WorkLog;
import javafx.application.Application;
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
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello world!!");
//        stage.setScene(scene);
//        stage.show();
/*        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("testPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();*/

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
            Statement stm = connection.createStatement();
            WorkLogDaoImplementation wdi = new WorkLogDaoImplementation(connection, stm);
            System.out.println(wdi.getWorkLogs(null, null).size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}