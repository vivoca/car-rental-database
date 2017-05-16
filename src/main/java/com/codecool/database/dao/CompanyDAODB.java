package com.codecool.database.dao;

import com.codecool.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDAODB extends AbstractDBHandler implements CompanyDAO {

    private static CompanyDAODB INSTANCE;

    public static CompanyDAODB getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CompanyDAODB();
        }
        return INSTANCE;
    }

    private CompanyDAODB() {
    }

    @Override
    public void add(Company company) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO \"company\" (NAME) VALUES (?)");
            stmt.setString(1, company.getName());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company find ( int id) {
        String query = "SELECT * FROM company WHERE ID='" + id + "';";

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                return new Company(resultSet.getInt("ID"), resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
