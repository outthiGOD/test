package com.example.history.entity;

import java.time.LocalDate;

public class History {
    private int id;
    private String partnerName;
    private String productName;
    private LocalDate saleDate;
    private int productCount;

    public History(int id, String partnerName, String productName, LocalDate saleDate, int productCount) {
        this.id = id;
        this.partnerName = partnerName;
        this.productName = productName;
        this.saleDate = saleDate;
        this.productCount = productCount;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", partnerName='" + partnerName + '\'' +
                ", productName='" + productName + '\'' +
                ", saleDate='" + saleDate + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
