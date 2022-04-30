package com.diploma.controllers;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateUserFormController {

    @FXML
    private Pane userPane;

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

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
    protected void onOKButtonClick() throws Exception {
        if(fh.validateFields(userPane) && createUser()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        this.fh = new FormHelper();
    }

    private boolean createUser() throws Exception {
        try{
            UserDaoImplementation udi = new UserDaoImplementation();
            User newUser = new User(null, userNameField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText(), udi.getUsers().isEmpty(), false);
            return udi.createUser(newUser);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }


}
