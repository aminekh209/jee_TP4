package com.tp4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactFacade {

    // Crée un nouveau contact dans la base
    public void addContact(Contact contact) {
        String sql = "INSERT INTO contact (FIRSTNAME, LASTNAME, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstname());
            stmt.setString(2, contact.getLastname());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setString(5, contact.getAddress());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprime un contact par son ID
    public void deleteContact(int contactId) {
        String sql = "DELETE FROM contact WHERE ID_CONTACT = ?";

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Met à jour un contact existant
    public void updateContact(Contact contact) {
        String sql = "UPDATE contact SET FIRSTNAME=?, LASTNAME=?, EMAIL=?, PHONE=?, ADDRESS=? WHERE ID_CONTACT=?";

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstname());
            stmt.setString(2, contact.getLastname());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setString(5, contact.getAddress());
            stmt.setInt(6, contact.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Recherche un contact par mot-clé
    public List<Contact> searchContact(String keyword) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contact WHERE FIRSTNAME LIKE ? OR LASTNAME LIKE ?";

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contacts.add(mapRowToContact(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Récupère tous les contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contact";

        try (Connection conn = Connexiondb.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(mapRowToContact(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Récupère un contact par son ID
    public Contact findContactById(int contactId) {
        String sql = "SELECT * FROM contact WHERE ID_CONTACT = ?";
        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToContact(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode utilitaire pour transformer une ligne de ResultSet en Contact
    private Contact mapRowToContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("ID_CONTACT"));
        contact.setFirstname(rs.getString("FIRSTNAME"));
        contact.setLastname(rs.getString("LASTNAME"));
        contact.setEmail(rs.getString("EMAIL"));
        contact.setPhone(rs.getString("PHONE"));
        contact.setAddress(rs.getString("ADDRESS"));
        return contact;
    }
}
