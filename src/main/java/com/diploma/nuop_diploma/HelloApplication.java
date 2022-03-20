package com.diploma.nuop_diploma;

import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.models.Order;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

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
            OrderDaoImplementation odi = new OrderDaoImplementation();
            BigInteger orderId = BigInteger.valueOf(1);
            Order o = odi.getOrder(orderId);
            o.setOrderId(BigInteger.valueOf(10));
            o.setName("Test3");
            o.setDescription("TestTest3");
            odi.createOrder(o);
            Collection<Order> orders = odi.getOrders(BigInteger.valueOf(1));
            for(Order order : orders){
                System.out.println(order.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}