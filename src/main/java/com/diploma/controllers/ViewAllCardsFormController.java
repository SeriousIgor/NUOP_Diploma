package com.diploma.controllers;

import java.lang.constant.Constable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import com.diploma.models.Client;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewAllCardsFormController {

    @FXML
    private TableColumn<Card, Double> bonusesColumn;

    @FXML
    private TableColumn<Card, String> clientColumn;

    @FXML
    private Pane cardPane;

    @FXML
    private TableView<Card> cardTableView;

    @FXML
    private TableColumn<Card, Integer> discountPercentageColumn;

    @FXML
    private TableColumn<Card, Boolean> isDiscountColumn;

    private FormHelper fh;

    private CardDaoImplementation cdi;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cdi = new CardDaoImplementation(FormHelper.connection);
        populateCardTable();
        this.cardTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        fh.showRecordForm(newValue, (Stage) cardPane.getScene().getWindow(), "card"));
    }

    private void populateCardTable() throws SQLException {
        ObservableList<Card> wrappedCardRecords = FXCollections.observableArrayList();
        wrappedCardRecords.addAll(cdi.getCards());
        this.cardTableView.setItems(wrappedCardRecords);
        this.bonusesColumn.setCellValueFactory(new PropertyValueFactory<>("bonuses"));
        this.discountPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        this.isDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("isDiscount"));
        this.clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Card, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Card, String> cardStringCellDataFeatures) {
                Client client = cardStringCellDataFeatures.getValue().getClient();
                return new SimpleObjectProperty<>(client.getFirstName() + " " + client.getLastName());
            }
        });
    }
}
