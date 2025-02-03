package com.example.demo5.repository;

import com.example.demo5.entity.Partner;
import com.example.demo5.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartnerAddEditRepository {

    public boolean savePartner(Partner partner) {
        String query = "INSERT INTO partners (partners_title, partners_email, partners_rating, address_partners, full_name, partners_phone, partners_type, partners_inn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, partner.getName());
            preparedStatement.setString(2, partner.getEmail());
            preparedStatement.setInt(3, partner.getRating());
            preparedStatement.setString(4, partner.getAddress());
            preparedStatement.setString(5, partner.getFio());
            preparedStatement.setString(6, partner.getPhoneNumber());
            preparedStatement.setInt(7, partner.getType());
            preparedStatement.setLong(8, partner.getInn());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при сохранении партнера: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePartner(Partner partner) {
        String query = "UPDATE partners SET partners_email = ?, partners_rating = ?, address_partners = ?, full_name = ?, partners_phone = ?, partners_type = ?, partners_inn = ? " +
                "WHERE partners_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, partner.getEmail());
            preparedStatement.setInt(2, partner.getRating());
            preparedStatement.setString(3, partner.getAddress());
            preparedStatement.setString(4, partner.getFio());
            preparedStatement.setString(5, partner.getPhoneNumber());
            preparedStatement.setInt(6, partner.getType());
            preparedStatement.setLong(7, partner.getInn());
            preparedStatement.setInt(8, partner.getId());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при обновлении партнера: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> getPartnerTypes() {
        List<String[]> partnerTypes = new ArrayList<>();
        String query = "SELECT partners_type_id, partners_title FROM partners_type";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                partnerTypes.add(new String[]{
                        String.valueOf(resultSet.getInt("partners_type_id")),
                        resultSet.getString("partners_title")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка загрузки типов партнеров: " + e.getMessage());
        }

        return partnerTypes;
    }
}
