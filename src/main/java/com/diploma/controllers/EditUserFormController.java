package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditUserFormController {

    @FXML
    private Pane userPane;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    private Button deleteRecordButton;

    private FormHelper fh;

    private UserDaoImplementation udi;

    private User user;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(userPane) && updateUser()){
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
            if(udi.deleteUser(user.getUserId())){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                fh.setPaneDeletedStyle(userPane);
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
        this.user = (User) FormHelper.transferData;
        this.udi = new UserDaoImplementation(FormHelper.connection);
        populateUserFields();
        if(user.isDeleted()){
            this.okButton.setDisable(user.isDeleted());
            this.deleteRecordButton.setDisable(user.isDeleted());
            this.cancelButton.setText("Close");
            fh.setPaneDeletedStyle(userPane);
        }
    }

    private void populateUserFields(){
        this.userNameField.setText(user.getUserName());
        this.passwordField.setText(user.getPassword());
        this.firstNameField.setText(user.getFirstName());
        this.lastNameField.setText(user.getLastName());
    }

    private Boolean updateUser(){
        user.setUserName(this.userNameField.getText());
        user.setPassword(this.passwordField.getText());
        user.setFirstName(this.firstNameField.getText());
        user.setLastName(this.lastNameField.getText());
        try{
            return udi.updateUser(user);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
