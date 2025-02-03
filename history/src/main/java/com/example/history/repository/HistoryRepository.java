package com.example.history.repository;

import com.example.history.entity.History;
import com.example.history.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {

    public List<String> getAllPartnerNames() {
        List<String> partnerNames = new ArrayList<>();

        String query = "SELECT DISTINCT partners_title FROM partners";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                partnerNames.add(resultSet.getString("partners_title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки списка партнеров: " + e.getMessage());
        }

        return partnerNames;
    }

    public List<History> getHistoryByPartner(String partnerName) {
        List<History> historyList = new ArrayList<>();
        String query;

        if (partnerName == null) {
            query = """
                SELECT 
                    h.history_sale_id, 
                    h.sale_date, 
                    h.product_count, 
                    p.partners_title AS partner_name, 
                    pr.product_title AS product_name
                FROM 
                    history_sale h
                JOIN 
                    partners p ON h.partners_id = p.partners_id
                JOIN 
                    products pr ON h.product_id = pr.product_id
            """;
        } else {
            query = """
                SELECT 
                    h.history_sale_id, 
                    h.sale_date, 
                    h.product_count, 
                    p.partners_title AS partner_name, 
                    pr.product_title AS product_name
                FROM 
                    history_sale h
                JOIN 
                    partners p ON h.partners_id = p.partners_id
                JOIN 
                    products pr ON h.product_id = pr.product_id
                WHERE 
                    p.partners_title = ?
            """;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (partnerName != null) {
                statement.setString(1, partnerName);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("history_sale_id");
                String fetchedPartnerName = resultSet.getString("partner_name");
                String productName = resultSet.getString("product_name");
                LocalDate saleDate = resultSet.getDate("sale_date").toLocalDate();
                int productCount = resultSet.getInt("product_count");

                historyList.add(new History(id, fetchedPartnerName, productName, saleDate, productCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки данных из history_sale: " + e.getMessage());
        }

        return historyList;
    }
}
