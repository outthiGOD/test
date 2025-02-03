package com.example.demo5.controllers;

import com.example.demo5.entity.Partner;
import com.example.demo5.factory.PartnerAddEditRepositoryFactory;
import com.example.demo5.repository.PartnerAddEditRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class PartnersAddEditController {

    @FXML
    private TextField innField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fioField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField ratingField;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> typeBox;

    private final PartnerAddEditRepository partnerAddEditRepository = PartnerAddEditRepositoryFactory.getInstance();

    private Partner currentPartner;

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setPartnerData(Partner partner) {
        this.currentPartner = partner;

        if (partner != null) {
            nameField.setText(partner.getName());
            emailField.setText(partner.getEmail());
            ratingField.setText(String.valueOf(partner.getRating()));
            addressField.setText(partner.getAddress());
            fioField.setText(partner.getFio());
            phoneNumberField.setText(partner.getPhoneNumber());
            innField.setText(String.valueOf(partner.getInn()));
            typeBox.setValue(partner.getTypeTitle() != null ? partner.getTypeTitle() : null);
        }
    }

    @FXML
    void backEvent(MouseEvent event) {
        saveButton.getScene().getWindow().hide();
    }

    @FXML
    void saveEvent(MouseEvent event) {
        try {
            String name = nameField.getText();
            String email = emailField.getText();
            String ratingText = ratingField.getText();
            String address = addressField.getText();
            String fio = fioField.getText();
            String phoneNumber = phoneNumberField.getText();
            Long inn = Long.parseLong(innField.getText());
            String selectedType = typeBox.getValue();
            String typeTitle = typeBox.getSelectionModel().getSelectedItem();

            // Проверка рейтинга
            int rating;
            try {
                rating = Integer.parseInt(ratingText);
                if (rating < 0) {
                    throw new NumberFormatException("Рейтинг должен быть неотрицательным числом.");
                }
            } catch (NumberFormatException e) {
                showAlert("Ошибка ввода", "Рейтинг должен быть неотрицательным целым числом.");
                return;
            }

            // Получение ID типа партнера
            List<String[]> partnerTypes = (List<String[]>) typeBox.getUserData();
            int typeId = partnerTypes.stream()
                    .filter(type -> type[1].equals(selectedType))
                    .findFirst()
                    .map(type -> Integer.parseInt(type[0]))
                    .orElseThrow(() -> new IllegalArgumentException("Тип партнера не найден"));

            if (currentPartner == null) {
                // Добавление нового партнера
                Partner newPartner = new Partner(null, name, email, rating, address, fio, phoneNumber, typeId, typeTitle, inn);
                partnerAddEditRepository.savePartner(newPartner);
            } else {
                // Обновление существующего партнера
                currentPartner.setName(name);
                currentPartner.setEmail(email);
                currentPartner.setRating(rating);
                currentPartner.setAddress(address);
                currentPartner.setFio(fio);
                currentPartner.setPhoneNumber(phoneNumber);
                currentPartner.setType(typeId);
                currentPartner.setInn(inn);
                partnerAddEditRepository.updatePartner(currentPartner);
            }

            saveButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        List<String[]> partnerTypes = partnerAddEditRepository.getPartnerTypes();
        typeBox.getItems().clear();
        for (String[] type : partnerTypes) {
            typeBox.getItems().add(type[1]);
        }
        typeBox.setUserData(partnerTypes);
    }
}