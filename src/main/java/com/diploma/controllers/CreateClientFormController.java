package com.diploma.controllers;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateClientFormController {

    @FXML
    private Pane clientPane;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private FormHelper fh;

    @FXML
    protected void onOKButtonClick() throws Exception{
        if(fh.validateFields(clientPane) && createClient()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/main-menu-form.fxml");
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/main-menu-form.fxml");
    }

    @FXML
    void initialize() {
        fh = new FormHelper();
    }

    private boolean createClient() throws Exception {
        try{
            Client client = new Client(null, firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), false);
            ClientDaoImplementation cdi = new ClientDaoImplementation();
            return cdi.createCliend(client);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
