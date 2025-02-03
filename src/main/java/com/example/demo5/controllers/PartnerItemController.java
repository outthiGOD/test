package com.example.demo5.controllers;

import com.example.demo5.entity.Partner;
import com.example.demo5.repository.PartnerRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PartnerItemController {

    @FXML
    private Label partnerTypeName;

    @FXML
    private Label partnerFio;

    @FXML
    private Label partnerPhone;

    @FXML
    private Label partnerRating;

    @FXML
    private Label partnerDiscount;

    private Partner partner;

    private final PartnerRepository partnerRepository = new PartnerRepository();

    public void setPartnerData(Partner partner) {

        partnerTypeName.setText(partner.getTypeTitle() + " | " + partner.getName());
        partnerFio.setText(partner.getFio());
        partnerPhone.setText(partner.getPhoneNumber());
        partnerRating.setText("Рейтинг: " + partner.getRating());
        partnerDiscount.setText(calculateDiscount(partner.getId()) + "%");
    }

    private int calculateDiscount(int partnerId) {
        int totalSales = getTotalSales(partnerId);

        if (totalSales < 10000) return 0;
        if (totalSales < 50000) return 5;
        if (totalSales < 300000) return 10;
        return 15;
    }

    private int getTotalSales(int partnerId) {
        return partnerRepository.getTotalSales(partnerId);
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Partner getPartner() {
        return partner;
    }

}
