package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditClientFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteRecordButton;

    @FXML
    private Pane clientPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField phoneNumberField;

    private FormHelper fh;

    private ClientDaoImplementation cldi;

    private Client client;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(clientPane) && updateClient()){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/view-records-form.fxml");
        }
    }

    @FXML
    void onDeleteRecordButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        try{
            if(cldi.deleteClient(client.getClientId())){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                fh.setPaneDeletedStyle(clientPane);
                cancelButton.setText("Close");
                okButton.setDisable(true);
                deleteRecordButton.setDisable(true);
            }
        } catch (Exception ex){
            alert.setContentText(ex.getMessage());
        }
        alert.show();
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cldi = new ClientDaoImplementation(FormHelper.connection);
        this.client = (Client) FormHelper.transferData;
        populateClientFields();
        if(client.isDeleted()){
            this.okButton.setDisable(client.isDeleted());
            this.deleteRecordButton.setDisable(client.isDeleted());
            this.cancelButton.setText("Close");
            fh.setPaneDeletedStyle(clientPane);
        }
    }

    private void populateClientFields(){
        this.firstNameField.setText(client.getFirstName());
        this.lastNameField.setText(client.getLastName());
        this.phoneNumberField.setText(client.getPhoneNumber());
    }

    private boolean updateClient(){
        this.client.setFirstName(firstNameField.getText());
        this.client.setLastName(lastNameField.getText());
        this.client.setPhoneNumber(phoneNumberField.getText());
        try{
            return cldi.updateClient(client);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
