package com.example.demo5.factory;

import com.example.demo5.repository.PartnerRepository;

public class PartnerRepositoryFactory {
    private static PartnerRepository INSTANCE;

    public static PartnerRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PartnerRepository();
        }
        return INSTANCE;
    }
}
