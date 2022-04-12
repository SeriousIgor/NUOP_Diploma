package com.diploma.helpers;

import com.diploma.nuop_diploma.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FormHelper {

    public static String errorMessage;

    private static Stage primaryStage = new Stage();

    public Stage getPrimaryStage(){

        return primaryStage;
    }

    public static void displayMessageBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/forms/message-box.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public String startPrimaryForm(String path, String title){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void getScene(String scene){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));
        try {
            loader.load();
            Parent root = loader.getRoot();

            Stage stage = getPrimaryStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateFields(Pane currentPane){
        ArrayList<TextField> emptyFields = new ArrayList<>();
        for (Node node : currentPane.getChildren()) {
            if (node instanceof TextField) {
                TextField field = (TextField) node;
                if(field.getText().isBlank()){
                    field.setStyle("-fx-background-color: red;");
                    emptyFields.add(field);
                } else {
                    field.setStyle("-fx-background-color: #FFFFFF;");
                }
            }
        }
        return emptyFields.isEmpty();
    }
}
