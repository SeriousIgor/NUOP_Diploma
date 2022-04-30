package com.diploma.controllers;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import com.diploma.models.Service;
import com.diploma.models.enumeration.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.EnumSet;
import java.util.stream.Collectors;

public class CreateOrderFormController {

    @FXML
    private Pane orderPane;

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

    private FormHelper fh;

    @FXML
    void initialize(){
        fh = new FormHelper();
        this.statusComboBox.setValue(OrderStatus.OPEN.toString());
    }

    @FXML
    protected void onOKButtonClick(){
        if(fh.validateFields(orderPane) && createOrder()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean createOrder(){
        try{
            //TODO
            return true;
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
