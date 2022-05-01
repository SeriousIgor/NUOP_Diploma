package com.diploma.controllers;

import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serial;

public class CreateServiceFormController {

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

    @FXML
    protected void onOKButtonClick() throws Exception{
        if(createService()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean createService() throws Exception{
        try{
            if(!fh.validateNumber(priceField.getText())){
                throw new Exception("Only numbers allowed");
            }
            Service service = new Service(null, nameField.getText(), Double.valueOf(priceField.getText()), descriptionField.getText(), false);
            ServiceDaoImplementation sdi = new ServiceDaoImplementation();
            return sdi.createService(service);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }


}
