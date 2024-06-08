package org.example;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root7769";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            createTable(connection);

            insertRecord(connection);

            readRecord(connection);

            updateRecord(connection);

            delete(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully");
        }
    }
      private static void insertRecord(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO users(name, email) VALUES('Suyunov Anvar','frozengmaneno2@gmail.com')";
         try (Statement statement = connection.createStatement()) {
             statement.execute(insertSQL);
             System.out.println("Record inserted successfully");
         }
      }
      private static void readRecord(Connection connection) throws SQLException {
         String selectSQL = "SELECT * FROM users";
         try (Statement statement = connection.createStatement()) {
             ResultSet resultSet = statement.executeQuery(selectSQL);

             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 String email = resultSet.getString("email");
                 System.out.println("ID: "+id+", Name: "+name+", Email: "+email);
             }
         }
      }
      private static void updateRecord(Connection connection) throws SQLException {
        String updateSQL = "UPDATE users SET name = 'Suyunov Anvar' WHERE id = 1";
        try (Statement statement = connection.createStatement()) {
            statement.execute(updateSQL);
            System.out.println("Record updated successfully");
        }
      }
      private static void delete(Connection connection)throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = 2";
        try (Statement statement = connection.createStatement()) {
            statement.execute(deleteSQL);
            System.out.println("Record deleted successfully");
        }
      }
}
