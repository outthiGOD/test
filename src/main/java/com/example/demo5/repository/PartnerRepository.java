package com.example.demo5.repository;

import com.example.demo5.entity.Partner;
import com.example.demo5.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PartnerRepository {
    public List<Partner> getAllPartners() {
        List<Partner> partners = new ArrayList<>();

        String query = """
                SELECT 
                    p.partners_id, 
                    p.partners_title, 
                    p.partners_email, 
                    p.partners_rating, 
                    p.address_partners, 
                    p.full_name, 
                    p.partners_phone, 
                    p.partners_type, 
                    pt.partners_title AS type_title, 
                    p.partners_inn
                FROM 
                    partners p
                JOIN 
                    partners_type pt 
                ON 
                    p.partners_type = pt.partners_type_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("partners_id");
                String name = resultSet.getString("partners_title");
                String email = resultSet.getString("partners_email");
                int rating = resultSet.getInt("partners_rating");
                String address = resultSet.getString("address_partners");
                String fio = resultSet.getString("full_name");
                String phoneNumber = resultSet.getString("partners_phone");
                int type = resultSet.getInt("partners_type");
                String typeTitle = resultSet.getString("type_title");
                long inn = resultSet.getLong("partners_inn");

                partners.add(new Partner(id, name, email, rating, address, fio, phoneNumber, type, typeTitle, inn));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки данных о партнерах: " + e.getMessage());
        }

        return partners;
    }

    public int getTotalSales(int partnerId) {
        int totalSales = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT SUM(product_count) FROM history_sale WHERE partners_id = ?")) {

            statement.setInt(1, partnerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalSales = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка расчета общего объема продаж: " + e.getMessage());
        }

        return totalSales;
    }

}
