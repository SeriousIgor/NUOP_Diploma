package com.diploma.controllers;

import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.diploma.helpers.FormHelper;
import com.diploma.models.enumeration.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditOrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField clientField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea serviceField;

    @FXML
    private ComboBox statusComboBox;

    private FormHelper fh;

    @FXML
    void onCancelButtonClick(ActionEvent event) {

    }

    @FXML
    void onOKButtonClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        this.fh = new FormHelper();
        ObservableList<String> comboboxOptions = FXCollections.observableArrayList();
        comboboxOptions.addAll(EnumSet.allOf(OrderStatus.class).stream().map(OrderStatus::name).collect(Collectors.toList()));
        statusComboBox.setItems(comboboxOptions);
    }

}
