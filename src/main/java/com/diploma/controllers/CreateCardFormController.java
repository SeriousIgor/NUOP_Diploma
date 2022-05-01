package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import com.diploma.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateCardFormController {

    @FXML
    private ComboBox clientCombobox;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField discountPercentageField;

    @FXML
    private Pane cardPane;

    @FXML
    private Label discountPercentageLabel;

    @FXML
    private CheckBox isDiscountCheckbox;

    @FXML
    private Button okButton;

    private FormHelper fh;

    private CardDaoImplementation cdi;

    private ClientDaoImplementation cldi;

    private Map<String, Client> clientMap;

    @FXML
    void onCheckboxClick(ActionEvent event) {
        if(isDiscountCheckbox.isSelected()){
            discountPercentageField.setVisible(true);
            discountPercentageLabel.setVisible(true);

        } else{
            discountPercentageField.setVisible(false);
            discountPercentageLabel.setVisible(false);
        }
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
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cdi = new CardDaoImplementation(FormHelper.connection);
        this.cldi = new ClientDaoImplementation(FormHelper.connection);
        this.discountPercentageLabel.setVisible(false);
        this.discountPercentageField.setVisible(false);
        Collection<Client> availableClients = cldi.getClients();
        clientMap = new HashMap<>();
        for(Client c : availableClients){
            clientMap.put(c.getFirstName() + " " + c.getLastName(), c);
        }
        ObservableList<String> comboboxOptions = FXCollections.observableArrayList();
        comboboxOptions.addAll(clientMap.keySet());
        this.clientCombobox.setItems(comboboxOptions);
    }

    private boolean createCard(){
        try{
            String clientName = clientCombobox.getValue().toString();
            Client client = clientMap.get(clientName);
            Boolean isDiscount = isDiscountCheckbox.isSelected();
            String discountPercentage = discountPercentageField.getText();
            Integer discount;
            if(isDiscount){
                if(fh.validateNumber(discountPercentage)){
                    discount = Integer.valueOf(discountPercentage);
                } else {
                    throw new Exception("Enter valid number");
                }
            } else {
                discount = 0;
            }
            Card card = new Card(null, client, isDiscountCheckbox.isSelected(),discount, 0d, false);

            return cdi.createCard(card);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
