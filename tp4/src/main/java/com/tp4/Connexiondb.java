package com.tp4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexiondb {
    private static final String URL = "jdbc:mysql://localhost:3306/contact?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Supprimez la connexion statique
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC MySQL introuvable !");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // Crée une nouvelle connexion à chaque appel
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Nouvelle connexion MySQL établie");
        return conn;
    }
}