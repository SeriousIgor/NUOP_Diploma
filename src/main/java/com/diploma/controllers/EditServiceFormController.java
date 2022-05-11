package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditServiceFormController {

    @FXML
    private Pane servicePane;

    @FXML
    private Button deleteRecordButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField priceField;

    private FormHelper fh;

    private ServiceDaoImplementation sdi;

    private Service service;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        FormHelper.transferData = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(servicePane) && updateService()){
            FormHelper.transferData = null;
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
            if(sdi.deleteService(service.getServiceId())){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                fh.setPaneDeletedStyle(servicePane);
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
        this.service = (Service) FormHelper.transferData;
        this.sdi = new ServiceDaoImplementation(FormHelper.connection);
        populateServiceFields();
        if(service.isDeleted()){
            this.okButton.setDisable(service.isDeleted());
            this.deleteRecordButton.setDisable(service.isDeleted());
            this.cancelButton.setText("Close");
            fh.setPaneDeletedStyle(servicePane);
        }
    }

    private void populateServiceFields(){
        this.nameField.setText(service.getName());
        this.priceField.setText(service.getPrice().toString());
        this.descriptionField.setText(service.getDescription());
    }

    private boolean updateService(){
        service.setName(this.nameField.getText());
        service.setDescription(this.descriptionField.getText());
        try {
            if(fh.validateNumber(this.priceField.getText())){
                service.setPrice(Double.valueOf(this.priceField.getText()));
            } else {
                throw new Exception("Enter a valid number");
            }
            return sdi.updateService(service);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
