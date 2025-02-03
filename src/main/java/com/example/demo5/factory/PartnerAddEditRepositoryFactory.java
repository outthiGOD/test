package com.example.demo5.factory;

import com.example.demo5.repository.PartnerAddEditRepository;

public class PartnerAddEditRepositoryFactory {
    private static PartnerAddEditRepository INSTANCE;

    public static PartnerAddEditRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PartnerAddEditRepository();
        }
        return INSTANCE;
    }
}
