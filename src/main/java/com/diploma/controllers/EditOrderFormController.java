package com.diploma.controllers;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Client;
import com.diploma.models.Order;
import com.diploma.models.Service;
import com.diploma.models.User;
import com.diploma.models.enumeration.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditOrderFormController {

    @FXML
    private Pane orderPane;

    @FXML
    private Button cancelButton;

    @FXML
    private Button clearButton;

    @FXML
    private ComboBox clientComboBox;

    @FXML
    private TextField createdDateField;

    @FXML
    private Button deleteRecordButton;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox serviceComboBox;

    @FXML
    private TextArea serviceField;

    @FXML
    private ComboBox statusComboBox;

    @FXML
    private Label userLabel;

    private FormHelper fh;

    private Order order;

    private Collection<Service> services;

    private Map<String, Client> clientMap;

    private Map<String, Service> serviceMap;

    private OrderDaoImplementation odi;

    private ServiceDaoImplementation sdi;

    private ClientDaoImplementation cldi;

    private CardDaoImplementation cdi;

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        FormHelper.transferData = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onClearButtonClick(ActionEvent event) {
        this.serviceField.setText("");
        this.priceField.setText("0.0");
        this.services.clear();
    }

    @FXML
    void onDeleteRecordButtonClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        try{
            if(odi.deleteOrder(order.getOrderId())){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                fh.setPaneDeletedStyle(orderPane);
                cancelButton.setText("Close");
                okButton.setDisable(true);
                deleteRecordButton.setDisable(true);
                clearButton.setDisable(true);
            }
        } catch (Exception ex){
            alert.setContentText(ex.getMessage());
        }
        alert.show();
    }

    @FXML
    void onOKButtonClick(ActionEvent event) {
        if(fh.validateFields(orderPane) && updateOrder()){
            FormHelper.transferData = null;
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
            fh.getScene("/forms/view-records-form.fxml");
        }
    }

    @FXML
    void onServiceComboboxItemChoose(ActionEvent event) {
        Object comboBoxValue = serviceComboBox.getValue();
        this.services.add(serviceMap.get(comboBoxValue.toString()));
        StringBuilder servicesList = new StringBuilder();
        Double price = 0.0;
        for(Service s : services){
            servicesList.append(s.getName()).append("\n");
            price += s.getPrice();
        }
        this.serviceField.setText(servicesList.toString());
        this.priceField.setText(price.toString());
    }

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.order = (Order) FormHelper.transferData;
        this.services = order.getServices();

        this.cldi = new ClientDaoImplementation(FormHelper.connection);
        this.cdi = new CardDaoImplementation(FormHelper.connection);
        this.sdi = new ServiceDaoImplementation(FormHelper.connection);
        this.odi = new OrderDaoImplementation(FormHelper.connection);

        populateOrderFields();

        if(!order.isDeleted()) {
            this.clientMap = new HashMap<>();
            for(Client c : cldi.getClients()){
                clientMap.put(c.getFirstName() + " " + c.getLastName(), c);
            }
            ObservableList<String> clientComboboxOptions = FXCollections.observableArrayList();
            clientComboboxOptions.addAll(clientMap.keySet());
            this.serviceMap = new HashMap<>();
            for(Service s : sdi.getServices()){
                serviceMap.put(s.getName(), s);
            }
            ObservableList<String> serviceComboboxOptions = FXCollections.observableArrayList();
            serviceComboboxOptions.addAll(serviceMap.keySet());
            this.clientComboBox.setItems(clientComboboxOptions);
            this.serviceComboBox.setItems(serviceComboboxOptions);
            ObservableList<String> comboboxOptions = FXCollections.observableArrayList();
            comboboxOptions.addAll(EnumSet.allOf(OrderStatus.class).stream().map(OrderStatus::name).collect(Collectors.toList()));
            statusComboBox.setItems(comboboxOptions);
        } else {
            this.okButton.setDisable(order.isDeleted());
            this.deleteRecordButton.setDisable(order.isDeleted());
            this.clearButton.setDisable(order.isDeleted());
            this.cancelButton.setText("Close");
            fh.setPaneDeletedStyle(orderPane);
        }
    }

    private void populateOrderFields() {
        this.nameField.setText(order.getName());
        this.priceField.setText(order.getPrice().toString());
        this.descriptionField.setText(order.getDescription());
        this.createdDateField.setText(order.getOrderDate().toString());
        this.statusComboBox.setValue(order.getStatus().toString());
        Client client = order.getClient();
        this.clientComboBox.setValue(client.getFirstName() + " " + client.getLastName());
        StringBuilder servicesList = new StringBuilder();
        for(Service s : this.services){
            servicesList.append(s.getName()).append("\n");
        }
        this.serviceField.setText(servicesList.toString());
        this.serviceComboBox.setValue(this.services.iterator().next().getName());
        User user = order.getUser();
        this.userLabel.setText(user.getFirstName() + " " + user.getLastName());
    }

    private boolean updateOrder() {
        try{
            order.setName(this.nameField.getText());
            order.setDescription(this.descriptionField.getText());
            order.setPrice(Double.valueOf(this.priceField.getText()));
            order.setClient(clientMap.get(this.clientComboBox.getValue()));
            order.setStatus(OrderStatus.valueOf(this.statusComboBox.getValue().toString()));
            return odi.updateOrder(order);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
    }
}
