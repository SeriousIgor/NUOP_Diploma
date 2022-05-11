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

import java.sql.SQLException;

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

    private UserDaoImplementation udi;

    private Boolean firstUser;

    @FXML
    protected void onOKButtonClick() throws Exception {
        if(this.fh.validateFields(userPane) && createUser()){
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
            String path = this.firstUser ? "/forms/main-menu-form.fxml" : "/forms/view-records-form.fxml";
            this.fh.getScene(path);
        }
    }

    @FXML
    protected void onCancelButtonClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        if(FormHelper.currentSession != null){
            this.fh.getScene("/forms/view-records-form.fxml");
        }
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.udi = new UserDaoImplementation(FormHelper.connection);
    }

    private boolean createUser() throws Exception {
        this.firstUser = FormHelper.currentSession == null;
        try{
            User newUser = new User(null, userNameField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText(), firstUser, false);
            if(this.firstUser){
                FormHelper.currentSession = newUser;
            }
            return udi.createUser(newUser);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.setHeaderText(null);
            alert.show();
            return false;
        }
    }


}
