package com.diploma.nuop_diploma;

import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.dao.implementation.OrderServiceBundleDaoImplementation;
import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.models.Order;
import com.diploma.models.OrderServiceBundle;
import com.diploma.models.Service;
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
            OrderDaoImplementation odi = new OrderDaoImplementation(connection, stm);
            ServiceDaoImplementation sdi = new ServiceDaoImplementation(connection, stm);

            BigInteger orderId = BigInteger.valueOf(14);
            Order order = odi.getOrder(orderId);
            for(Service s : order.getServices()){
                System.out.println(s.getName());
            }

            Service service2 = sdi.getService(BigInteger.valueOf(3));
            Service service = sdi.getService(BigInteger.valueOf(1));
            Collection<Service> serviceCollection = new ArrayList<Service>();
            serviceCollection.add(service);
            serviceCollection.add(service2);
            order.setServices(serviceCollection);
            for(Service s : order.getServices()){
                System.out.println(s.getName());
            }

            System.out.println(odi.updateOrder(order));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}