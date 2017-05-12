package com.codecool.database.dao;

import com.codecool.database.ConnectionPropertyValues;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AbstractDBHandler {

    private static ConnectionPropertyValues configReader = new ConnectionPropertyValues();
    private static HashMap DBprops = configReader.getPropValuesOfDB();
    private static final String DATABASE = "jdbc:postgresql://" + DBprops.get("url") + "/" + DBprops.get("database");
    private static final String DB_USER = String.valueOf(DBprops.get("user"));
    private static final String DB_PASSWORD = String.valueOf(DBprops.get("password"));
    protected static Connection connection = null;

    //Connects to the database
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        }
        return connection;
    }

    //Handles native SQL commands
    void executeQuery (String query) {
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
