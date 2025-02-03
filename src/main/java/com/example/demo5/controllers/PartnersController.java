package com.example.demo5.controllers;

import com.example.demo5.PartnersApp;
import com.example.demo5.entity.Partner;
import com.example.demo5.repository.PartnerRepository;

import com.example.history.PartnersHistoryApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PartnersController {

    @FXML
    private Button addButton;

    @FXML
    private Button historyButton;

    @FXML
    private MenuItem editButton;

    @FXML
    private ListView<Node> partnerListView;

    private final PartnerRepository partnerRepository = new PartnerRepository();

    private void refreshPartnerList() {
        partnerListView.getItems().clear();

        List<Partner> partners = partnerRepository.getAllPartners();
        for (Partner partner : partners) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo5/partner_item.fxml"));
                Node item = loader.load();

                PartnerItemController itemController = loader.getController();
                itemController.setPartnerData(partner);

                item.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        openAddEditWindow(partner);
                    }
                });

                partnerListView.getItems().add(item);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Ошибка загрузки элемента списка: " + e.getMessage());
            }
        }
    }

    private void openAddEditWindow(Partner partner) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo5/partnersAddEdit.fxml"));
            Parent root = loader.load();

            // Передача данных в окно редактирования
            PartnersAddEditController controller = loader.getController();
            if (partner != null) {
                controller.setPartnerData(partner);
            }

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(partner == null ? "Add Partner" : "Edit Partner");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Обновляем список после закрытия окна
            refreshPartnerList();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка открытия окна добавления/редактирования: " + e.getMessage());
        }
    }

    @FXML
    void addEvent(MouseEvent event) {
        openAddEditWindow(null);
    }

    @FXML
    private void toHistoryEvent(MouseEvent event) {
        try {
            PartnersHistoryApp historyApp = new PartnersHistoryApp();
            historyApp.start(new javafx.stage.Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        refreshPartnerList();
    }
}
