package com.tp4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactFacade {

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

    public boolean deleteContact(int contactId) {
        String sql = "DELETE FROM contact WHERE id_contact = ?"; // Vérifiez le nom de la colonne
        
        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, contactId);
            int rowsDeleted = stmt.executeUpdate();
            System.out.println(rowsDeleted + " ligne(s) supprimée(s)");
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Erreur suppression contact: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void updateContact(Contact contact, int id) {
        // La requête SQL qui met à jour les données du contact dans la base
        String sql = "UPDATE contact SET firstname = ?, lastname = ?, email = ?, phone = ?, address = ? WHERE id_contact = ?";

        try (Connection conn = Connexiondb.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Assigner les valeurs du contact dans la requête
            stmt.setString(1, contact.getFirstname());
            stmt.setString(2, contact.getLastname());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setString(5, contact.getAddress());
            stmt.setInt(6, id);  // Utiliser l'ID reçu en paramètre pour mettre à jour l'enregistrement

            // Exécuter la mise à jour
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Affiche l'erreur en cas de problème
        }
    }



    public List<Contact> searchContact(String keyword) {
        List<Contact> results = new ArrayList<>();
        String sql = "SELECT * FROM contact WHERE firstname LIKE ? OR lastname LIKE ? OR email LIKE ?";

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";  // Utiliser % pour la recherche avec LIKE
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id_contact"));
                contact.setFirstname(rs.getString("firstName"));
                contact.setLastname(rs.getString("lastName"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setAddress(rs.getString("address"));
                results.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        
        try (Connection conn = Connexiondb.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM contact")) {
            
            while (rs.next()) {
                contacts.add(new Contact(
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des contacts:");
            e.printStackTrace();
        }
        return contacts;
    }

    public Contact findContactById(int id) {
        String sql = "SELECT * FROM contact WHERE ID_CONTACT = ?";
        Contact contact = null;

        try (Connection conn = Connexiondb.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contact = new Contact();
                contact.setId(rs.getInt("ID_CONTACT"));
                contact.setFirstname(rs.getString("FIRSTNAME"));
                contact.setLastname(rs.getString("LASTNAME"));
                contact.setEmail(rs.getString("EMAIL"));
                contact.setPhone(rs.getString("PHONE"));
                contact.setAddress(rs.getString("ADDRESS"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }
}
