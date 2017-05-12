package com.codecool.database.dao;

import com.codecool.database.AbstractDBHandler;
import com.codecool.model.Company;

public class CompanyDAO extends AbstractDBHandler{

    private static CompanyDAO INSTANCE;

    public static  CompanyDAO getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CompanyDAO();
        }
        return INSTANCE;
    }

    private CompanyDAO() {
    }

    @Override
    public void add(Company company) {
        try {
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO \"supplier\" (NAME, DESCRIPTION) VALUES (?, ?)");
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeUpdate();
            LOGGER.info("Add method insert order name and the description into SupplierDB.");
            LOGGER.info("Supplier name: {}, description: {}", supplier.getName(), supplier.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error occurred during order added into database: {}", e);
        }
    }
}
