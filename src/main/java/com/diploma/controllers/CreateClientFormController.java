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

import java.sql.SQLException;

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
    private TextField emailField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private FormHelper fh;

    private ClientDaoImplementation cldi;

    @FXML
    protected void onOKButtonClick() throws Exception{
        if(fh.validateFields(clientPane) && createClient()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/view-records-form.fxml");
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cldi = new ClientDaoImplementation(FormHelper.connection);
    }

    private boolean createClient() throws Exception {
        try{
            Client client = new Client(null, firstNameField.getText(), lastNameField.getText(), phoneNumberField.getText(), emailField.getText(), false);
            return cldi.createCliend(client);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setHeaderText(null);
            alert.show();
            return false;
        }
    }
}
