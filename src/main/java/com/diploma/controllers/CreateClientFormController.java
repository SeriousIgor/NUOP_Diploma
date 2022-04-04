package com.diploma.controllers;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateClientFormController {

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

    @FXML
    protected void onOKButtonClick() throws Exception{
        if(createClient()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean createClient() throws Exception {
        try{
            Client client = new Client(null, firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), false);
            ClientDaoImplementation cdi = new ClientDaoImplementation();
            return cdi.createCliend(client);
        } catch (Exception ex){
            FormHelper.errorMessage = ex.getMessage();
            FormHelper.displayMessageBox();
            return false;
        }
    }
}
