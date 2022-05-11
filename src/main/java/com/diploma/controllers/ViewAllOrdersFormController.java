package com.diploma.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import com.diploma.models.Client;
import com.diploma.models.Order;
import com.diploma.models.enumeration.OrderStatus;
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

public class ViewAllOrdersFormController {

    @FXML
    private TableColumn<Order, String> clientColumn;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private Pane orderPane;

    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, Double> priceColumn;

    @FXML
    private TableColumn<Order, OrderStatus> statusColumn;

    @FXML
    private TableColumn<Order, Timestamp> orderDateColumn;

    private FormHelper fh;

    private OrderDaoImplementation odi;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.odi = new OrderDaoImplementation();
        populateOrderFields();
        this.orderTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        fh.showRecordForm(newValue, (Stage) orderPane.getScene().getWindow(), "order"));
    }

    private void populateOrderFields() throws SQLException {
        ObservableList<Order> wrappedOrderRecords = FXCollections.observableArrayList();
        wrappedOrderRecords.addAll(odi.getOrders());
        this.orderTableView.setItems(wrappedOrderRecords);
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        this.clientColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> orderStringCellDataFeatures) {
                Client client = orderStringCellDataFeatures.getValue().getClient();
                return new SimpleObjectProperty<>(client.getFirstName() + " " + client.getLastName());
            }
        });
    }

}
