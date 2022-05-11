package com.diploma.controllers;

import java.io.File;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.diploma.dao.implementation.UserDaoImplementation;
import com.diploma.dao.implementation.WorkLogDaoImplementation;
import com.diploma.helpers.FormHelper;
import com.diploma.metrics.WorkLogMetricsCalculation;
import com.diploma.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class WorkLogReportsFormController {


    @FXML
    private Button choosePathButton;

    @FXML
    private Button generateReportButton;

    @FXML
    private TextField pathField;

    @FXML
    private ComboBox<String> userCombobox;

    @FXML
    private Pane workLogReportsPane;

    private FormHelper fh;

    private WorkLogDaoImplementation wdi;

    private UserDaoImplementation udi;

    private Map<String, User> userMap;

    @FXML
    void initialize() throws SQLException {
        FormHelper.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/OrderAccounting.db");
        this.fh = new FormHelper();
        this.wdi = new WorkLogDaoImplementation(FormHelper.connection);
        this.udi = new UserDaoImplementation(FormHelper.connection);
        this.userMap = new HashMap<>();
        try{
            for(User u : udi.getUsers()){
                userMap.put(u.getFirstName() + " " + u.getLastName(), u);
            }
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Users not found");
            alert.show();
        }
        ObservableList<String> userComboboxOptions = FXCollections.observableArrayList();
        userComboboxOptions.addAll(userMap.keySet());
        userCombobox.setItems(userComboboxOptions);
    }

    @FXML
    void onChoosePathButtonClick(ActionEvent event) {
        try{
            String path = WorkLogMetricsCalculation.getPath(workLogReportsPane);
            this.pathField.setText(path);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }

    }

    @FXML
    void onGenerateReportButtonClick(ActionEvent event) {
        if(fh.validateFields(workLogReportsPane)){
            try{
                User user = userMap.get(userCombobox.getValue());
                WorkLogMetricsCalculation.generateReport(user, this.pathField.getText());
                System.out.println("not error");
            } catch (Exception ex){
                System.out.println("error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        }
    }

}
