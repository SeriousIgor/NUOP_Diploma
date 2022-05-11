package com.diploma.controllers;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.helpers.FormHelper;
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

public class ViewAllUsersFromController {

    @FXML
    private Pane usersPane;

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> userNameColumn;

    private FormHelper fh;

    private UserDaoImplementation udi;

    @FXML
    void initialize() throws SQLException {
        fh = new FormHelper();
        udi = new UserDaoImplementation(FormHelper.connection);
        populateUserTable();
        this.userTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        fh.showRecordForm(newValue, (Stage) usersPane.getScene().getWindow(), "user"));
    }

    private void populateUserTable() throws SQLException {
        ObservableList<User> wrappedUserRecords = FXCollections.observableArrayList();
        wrappedUserRecords.addAll(udi.getUsers());
        this.userTableView.setItems(wrappedUserRecords);
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
}
