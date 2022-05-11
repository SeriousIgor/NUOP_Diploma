package com.diploma.controllers;

import java.sql.SQLException;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditCardFormController {

    @FXML
    private TextField bonusesField;

    @FXML
    private Label bonusesLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Pane cardPane;

    @FXML
    private TextField clientField;

    @FXML
    private TextField discountPercentageField;

    @FXML
    private Label discountPercentageLabel;

    @FXML
    private CheckBox isDiscountCheckbox;

    @FXML
    private Button okButton;

    @FXML
    private Button deleteRecordButton;

    private FormHelper fh;

    private Card card;

    private CardDaoImplementation cdi;

    @FXML
    void isDiscountCheckBoxClicked(ActionEvent event) {
        if(isDiscountCheckbox.isSelected()){
            discountPercentageLabel.setVisible(true);
            discountPercentageField.setVisible(true);
            bonusesLabel.setVisible(false);
            bonusesField.setVisible(false);
        } else{
            discountPercentageLabel.setVisible(false);
            discountPercentageField.setVisible(false);
            bonusesField.setVisible(true);
            bonusesLabel.setVisible(true);
        }
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        FormHelper.transferData = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(cardPane) && updateCard()){
            FormHelper.transferData = null;
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
            if(cdi.deleteCard(card.getCardId())){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                fh.setPaneDeletedStyle(cardPane);
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
        this.cdi = new CardDaoImplementation(FormHelper.connection);
        this.card = (Card) FormHelper.transferData;
        populateCardFields();
        if(card.isDeleted()){
            this.okButton.setDisable(card.isDeleted());
            this.cancelButton.setText("Close");
            fh.setPaneDeletedStyle(cardPane);
        }
    }

    private void populateCardFields() {
        this.isDiscountCheckbox.setSelected(card.isDiscount());
        this.discountPercentageField.setText(card.getDiscountPercentage().toString());
        this.discountPercentageField.setVisible(card.isDiscount());
        this.discountPercentageLabel.setVisible(card.isDiscount());
        this.bonusesField.setText(card.getBonuses().toString());
        this.bonusesField.setVisible(!card.isDiscount());
        this.bonusesLabel.setVisible(!card.isDiscount());
        this.clientField.setText(card.getClient().getFirstName() + " " + card.getClient().getLastName());
    }

    private boolean updateCard(){
        try{
            Boolean isDiscount = isDiscountCheckbox.isSelected();
            card.setDiscount(isDiscount);

            String discountPercentage;
            String bonuses;
            if(isDiscount){
                discountPercentage = discountPercentageField.getText();
                bonuses = "0";
            } else {
                bonuses = bonusesField.getText();
                discountPercentage = "0";
            }
            if(fh.validateNumber(discountPercentage) && fh.validateNumber(bonuses)){
                card.setBonuses(Double.valueOf(bonuses));
                card.setDiscountPercentage(Integer.valueOf(discountPercentage));
            } else {
                throw new Exception("Enter a valid number");
            }
            return cdi.updateCard(card);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
