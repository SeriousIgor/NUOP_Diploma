package com.diploma.controllers;

import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.Serial;
import java.sql.SQLException;

public class CreateServiceFormController {

    @FXML
    private Pane servicePane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private FormHelper fh;

    private ServiceDaoImplementation sdi;

    @FXML
    protected void onOKButtonClick(){
        if(fh.validateFields(servicePane) && createService()){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
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
        this.sdi = new ServiceDaoImplementation(FormHelper.connection);
    }

    private boolean createService(){
        try{
            if(!fh.validateNumber(priceField.getText())){
                throw new Exception("Only numbers allowed");
            }
            Service service = new Service(null, nameField.getText(), Double.valueOf(priceField.getText()), descriptionField.getText(), false);
            return sdi.createService(service);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }


}
