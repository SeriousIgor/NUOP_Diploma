package com.diploma.controllers;

import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Service;
import com.diploma.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ViewAllServicesFormController {

    @FXML
    private TableColumn<Service, String> descriptionColumn;

    @FXML
    private TableColumn<Service, String> nameColumn;

    @FXML
    private TableColumn<Service, String> priceColumn;

    @FXML
    private Pane servicePane;

    @FXML
    private TableView<Service> serviceTableView;

    private FormHelper fh;

    private ServiceDaoImplementation sdi;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.sdi = new ServiceDaoImplementation(FormHelper.connection);
        populateServiceTable();
        this.serviceTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldService, newService) -> fh.showRecordForm(newService, (Stage) servicePane.getScene().getWindow(), "service"));
    }

    private void populateServiceTable() throws SQLException {
        ObservableList<Service> wrappedServiceRecords = FXCollections.observableArrayList();
        wrappedServiceRecords.addAll(sdi.getServices());
        this.serviceTableView.setItems(wrappedServiceRecords);
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
}
