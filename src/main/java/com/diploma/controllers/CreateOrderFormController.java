package com.diploma.controllers;

import com.diploma.dao.implementation.CardDaoImplementation;
import com.diploma.dao.implementation.ClientDaoImplementation;
import com.diploma.dao.implementation.OrderDaoImplementation;
import com.diploma.dao.implementation.ServiceDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.models.Card;
import com.diploma.models.Client;
import com.diploma.models.Order;
import com.diploma.models.Service;
import com.diploma.models.enumeration.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class CreateOrderFormController {

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox clientComboBox;

    @FXML
    private Button clearButton;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private Pane orderPane;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox serviceComboBox;

    @FXML
    private TextArea serviceField;

    @FXML
    private ComboBox statusComboBox;

    @FXML
    private CheckBox useCardCheckBox;

    @FXML
    private CheckBox useBonusesCheckBox;

    private Collection<Service> services;

    private Map<String, Service> serviceMap;

    private Map<String, Client> clientMap;

    private FormHelper fh;

    private ClientDaoImplementation cldi;

    private CardDaoImplementation cdi;

    private ServiceDaoImplementation sdi;

    private OrderDaoImplementation odi;

    private Card card;

    @FXML
    void initialize() throws SQLException {
        this.fh = new FormHelper();
        this.cldi = new ClientDaoImplementation(FormHelper.connection);
        this.cdi = new CardDaoImplementation(FormHelper.connection);
        this.sdi = new ServiceDaoImplementation(FormHelper.connection);
        this.odi = new OrderDaoImplementation(FormHelper.connection);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        String name = cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + " Order #";
        try{
            int numOfOrders = 1;
            for(Order o : odi.getAllOrders()){
                Timestamp orderDate = o.getOrderDate();
                Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
                if(orderDate.getYear() == currentDate.getYear() && orderDate.getMonth() == currentDate.getMonth()){
                    numOfOrders++;
                }
            }
            name += numOfOrders;
        } catch (Exception ex){
            name += 1;
        }
        this.nameField.setText(name);
        this.services = new ArrayList<>();
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
        this.statusComboBox.setValue(OrderStatus.OPEN.toString());
    }

    @FXML
    void onOKButtonClick(ActionEvent event){
        try{
            if(fh.validateFields(orderPane) && updatePrice() && createOrder()){
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
                fh.getScene("/forms/view-records-form.fxml");
            }
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        fh.getScene("/forms/view-records-form.fxml");
    }

    @FXML
    void onClientComboBoxItemChoose(ActionEvent event) {
        try{
            this.useCardCheckBox.setVisible(true);
            Client client = clientMap.get(clientComboBox.getValue().toString());
            this.card = cdi.getCards(client.getClientId()).iterator().next();
        } catch (Exception ex){
            this.useCardCheckBox.setVisible(false);
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void onClearButtonClick(ActionEvent event) {
        this.services.clear();
        this.serviceField.setText("");
        this.priceField.setText("0");
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
    void onUseCardCheckBoxCheck(ActionEvent event) {
        Boolean bonusesUsage = (this.useCardCheckBox.isSelected() && (!this.card.isDiscount()));
        this.useBonusesCheckBox.setVisible(bonusesUsage);
        this.useBonusesCheckBox.setSelected(bonusesUsage);
    }

    private boolean createOrder(){
        Boolean result;
        try{
            String name = this.nameField.getText();
            Double price = Double.valueOf(this.priceField.getText());
            String description = this.descriptionField.getText();
            Client client = clientMap.get(this.clientComboBox.getValue().toString());
            OrderStatus status = OrderStatus.valueOf(this.statusComboBox.getValue().toString());
            Boolean useBonuses = this.useBonusesCheckBox.isSelected() && this.useCardCheckBox.isSelected();
            Order order = new Order(null, name, status, price, description, null, null, FormHelper.currentSession, client, useBonuses, services, false);
            result = odi.createOrder(order);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
        return result;
    }

    private boolean updatePrice(){
        Boolean result;
        try{
            Double price = Double.valueOf(priceField.getText());
            Double bonuses = card.getBonuses();
            Double additionalBonuses = price / 100.0;
            if(this.useCardCheckBox.isSelected() && this.useBonusesCheckBox.isSelected()){
                if(price >= bonuses){
                    price = price - bonuses;
                    bonuses = additionalBonuses;
                } else {
                    bonuses = bonuses - price + additionalBonuses;
                    price = 0.0;
                }
                card.setBonuses(bonuses);
                priceField.setText(price.toString());
                result = cdi.updateCard(card);
            } else if(this.useCardCheckBox.isSelected()){
                if(!card.isDiscount()){
                    bonuses += additionalBonuses;
                    card.setBonuses(bonuses);
                } else {
                    price = price - (price * (card.getDiscountPercentage()/100));
                    priceField.setText(price.toString());
                }
                result = cdi.updateCard(card);
            } else {
                return true;
            }
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
            return false;
        }
        return result;
    }
}
