package com.example.history.controller;

import com.example.history.entity.History;
import com.example.history.repository.HistoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class HistoryController {

    @FXML
    private TableView<History> historyTable;

    @FXML
    private TableColumn<History, String> partnerNameColumn;

    @FXML
    private TableColumn<History, String> productNameColumn;

    @FXML
    private TableColumn<History, LocalDate> saleDateColumn;

    @FXML
    private TableColumn<History, Integer> productCountColumn;

    @FXML
    private ComboBox<String> partnerComboBox;

    @FXML
    private Button findButton;

    private final HistoryRepository historyRepository = new HistoryRepository();

    @FXML
    public void initialize() {
        // Связывание колонок с полями сущности
        partnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("productCount"));

        // Загрузка партнеров в ComboBox
        loadPartners();

        // Загрузка всех данных по умолчанию
        loadHistoryData(null);
    }

    private void loadPartners() {
        List<String> partners = historyRepository.getAllPartnerNames();
        partnerComboBox.getItems().add("Все");
        partnerComboBox.getItems().addAll(partners);
        partnerComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void filterHistoryByPartner(ActionEvent event) {
        String selectedPartner = partnerComboBox.getValue();
        if ("Все".equals(selectedPartner)) {
            loadHistoryData(null);
        } else {
            loadHistoryData(selectedPartner);
        }
    }

    private void loadHistoryData(String partnerName) {
        List<History> historyList = historyRepository.getHistoryByPartner(partnerName);
        historyTable.getItems().setAll(historyList);
    }
}
