package com.diploma.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewAllClientsFormController {

    @FXML
    private Pane clientPane;

    @FXML
    private TableView<Client> clientTableView;

    @FXML
    private TableColumn<Client, String> firstNameColumn;

    @FXML
    private TableColumn<Client, String> lastNameColumn;

    @FXML
    private TableColumn<Client, String> phoneNumberColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    private FormHelper fh;

    private ClientDaoImplementation cdi;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cdi = new ClientDaoImplementation(FormHelper.connection);
        populateClientTable();
        this.clientTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        fh.showRecordForm(newValue, (Stage) clientPane.getScene().getWindow(), "client"));
    }

    private void populateClientTable() throws SQLException {
        ObservableList<Client> wrappedClientRecords = FXCollections.observableArrayList();
        wrappedClientRecords.addAll(cdi.getClients());
        this.clientTableView.setItems(wrappedClientRecords);
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
