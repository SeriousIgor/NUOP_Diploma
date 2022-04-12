package com.diploma.controllers;

import com.diploma.models.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collection;

public class CreateOrderFormController {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox statusComboBox;

    @FXML
    private TextField priceField;

    @FXML
    private TextField clientField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private Collection<Service> services;

    @FXML
    protected void onOKButtonClick(){
//        if(createOrder()){
//            Stage stage = (Stage) okButton.getScene().getWindow();
//            stage.close();
//        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean createOrder(){
        return true;
    }
}
