package com.diploma.controllers;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserFormController {

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

    @FXML
    protected void onOKButtonClick() throws Exception {
        if(createUser()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean createUser() throws Exception {
        try{
            if(userNameField.getText().isBlank() || firstNameField.getText().isBlank() || lastNameField.getText().isBlank() || passwordField.getText().isBlank()){
                throw new Exception("Fill all User fields");
            }
            UserDaoImplementation udi = new UserDaoImplementation();
            User newUser = new User(null, userNameField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText(), udi.getUsers().isEmpty(), false);
            return udi.createUser(newUser);
        } catch (Exception ex){
            FormHelper.errorMessage = ex.getMessage();
            FormHelper.displayMessageBox();
            return false;
        }
    }

    private void closeStage(){

    }
}
