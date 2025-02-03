package com.example.demo5.entity;

public class Partner {
    private Integer id;
    private String name;
    private String email;
    private Integer rating;
    private String address;
    private String fio;
    private String phoneNumber;
    private Integer type;
    private String typeTitle; // Название типа партнера
    private Long inn;

    public Partner(Integer id, String name, String email, Integer rating, String address, String fio, String phoneNumber, Integer type, String typeTitle, Long inn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.address = address;
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.typeTitle = typeTitle;
        this.inn = inn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }
}
