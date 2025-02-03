package com.example.history.factory;

import com.example.history.repository.HistoryRepository;

public class HistoryRepositoryFactory {
    private static HistoryRepository INSTANCE;

    public static HistoryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HistoryRepository();
        }
        return INSTANCE;
    }
}
