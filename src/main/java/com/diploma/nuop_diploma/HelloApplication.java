package com.diploma.nuop_diploma;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.models.Card;
import com.diploma.models.Client;
import com.diploma.models.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
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
            CardDaoImplementation cdi = new CardDaoImplementation();
            BigInteger cardId = BigInteger.valueOf(1);
            Card card = cdi.getCard(cardId);
            Collection<Card> cardCollection = cdi.getCards();
            for(Card c : cardCollection){
                System.out.println(c.getCardId());
                System.out.println(c.getClient().getLastName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}