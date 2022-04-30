package com.diploma.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class ViewAllCardsFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> bonusesColumn;

    @FXML
    private TableColumn<?, ?> clientColumn;

    @FXML
    private TableColumn<?, ?> discountPercentageColumn;

    @FXML
    private TableColumn<?, ?> isDiscountColumn;

    @FXML
    void initialize() {

    }

}
