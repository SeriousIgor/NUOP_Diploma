package com.diploma.helpers;

import com.diploma.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class FormHelper {

    public static User currentSession;

    public static Object transferData;

    public static Connection connection;

    private static Stage primaryStage = new Stage();

    public Stage getPrimaryStage(){

        return primaryStage;
    }

    public void setChildScene(Pane childPane, String path){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try{
            loader.load();
            Parent root = loader.getRoot();
            childPane.getChildren().addAll(root);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void showRecordForm(Object newValue, Stage currentStage, String objectName){
        String path = "/forms/edit-" + objectName + "-form.fxml";
        transferData = newValue;
        currentStage.close();
        getScene(path);
    }

    public String startPrimaryForm(String path){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            primaryStage.initStyle(StageStyle.UNDECORATED);
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
        ArrayList<Node> emptyFields = new ArrayList<>();
        for (Node node : currentPane.getChildren()) {
            if (node instanceof TextField) {
                TextField field = (TextField) node;
                if(field.getText().isBlank()){
                    field.setStyle("-fx-background-color: red;");
                    emptyFields.add(field);
                } else {
                    field.setStyle("-fx-background-color: #FFFFFF;");
                }
            } else if(node instanceof ComboBox){
                ComboBox comboBox = (ComboBox) node;
                if(comboBox.getValue() == null || comboBox.getValue().toString().isBlank()){
                    comboBox.setStyle("-fx-background-color: red;");
                    emptyFields.add(comboBox);
                } else {
                    comboBox.setStyle("-fx-background-color: #FFFFFF;");
                }
            }
        }
        return emptyFields.isEmpty();
    }

    public void setPaneDeletedStyle(Pane currentPane){
        currentPane.setStyle("-fx-background-color: #94989A;");
        for(Node node : currentPane.getChildren()){
            if(!(node instanceof Button)){
                node.setDisable(true);
            }
        }
    }

    public boolean validateNumber(String text)
    {
        return text.matches("[0-9]*.[0-9]*");
    }
}
