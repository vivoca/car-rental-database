package com.codecool.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class BuildTable {
    public static void build() {
        ConnectionPropertyValues configReader = new ConnectionPropertyValues();
        HashMap DBprops = configReader.getPropValuesOfDB();

        String DATABASE = "jdbc:postgresql://" + DBprops.get("url") + "/" + DBprops.get("database");
        String DB_USER = String.valueOf(DBprops.get("user"));
        String DB_PASSWORD = String.valueOf(DBprops.get("password"));

        try (Connection connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD)){
            Statement statement = connection.createStatement();

            String createLoginTable =   "DROP TABLE IF EXISTS logintable CASCADE;" +
                                        "CREATE TABLE logintable" +
                                        "(" +
                                        "ID SERIAL PRIMARY KEY," +
                                        "USERNAME varchar(255) UNIQUE," +
                                        "EMAIL VARCHAR(255)," +
                                        "PASSWORD varchar(255) NOT NULL UNIQUE," +
                                        "SALT  varchar(255) NOT NULL UNIQUE" +
                                        ");";

            statement.execute(createLoginTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
