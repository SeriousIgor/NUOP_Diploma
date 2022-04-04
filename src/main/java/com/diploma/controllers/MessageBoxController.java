package com.diploma.controllers;

import com.diploma.helpers.FormHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class MessageBoxController {

    @FXML
    private Label messageField;

    @FXML
    private javafx.scene.control.Button okButton;

    public void initialize(){
        this.messageField.setText(FormHelper.errorMessage);
    }

    @FXML
    protected void onOKButtonClick() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}