package com.diploma.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import com.diploma.models.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateCardFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField clientField;

    @FXML
    private Pane cardPane;

    @FXML
    private TextField discountPercentageField;

    @FXML
    private CheckBox isDiscountCheckbox;

    @FXML
    private Button okButton;

    private FormHelper fh;

    @FXML
    void onCheckboxClick(ActionEvent event) {

    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(cardPane) && createCard()){
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/view-records-form.fxml");
        }
    }

    @FXML
    void initialize() {
        this.fh = new FormHelper();
    }

    private boolean createCard(){
        try{
            ClientDaoImplementation cldi = new ClientDaoImplementation();
            CardDaoImplementation cdi = new CardDaoImplementation();
            Client client = new Client(null,null,null,null,false);
            Card card = new Card(null, client,isDiscountCheckbox.isSelected(),0,0d, false);

            return cdi.createCard(card);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
