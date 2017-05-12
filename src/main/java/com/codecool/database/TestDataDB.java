package com.codecool.database;

import com.codecool.database.dao.CompanyDAODB;
import com.codecool.model.Company;
import javassist.NotFoundException;

public class TestDataDB {

    public static void populateData() {
        CompanyDAODB companyDB = CompanyDAODB.getINSTANCE();
        BuildTable.build();

        Company rentalcars = new Company("Rental Cars");
        companyDB.add(rentalcars);
        Company europcar = new Company("EuropCar");
        companyDB.add(europcar);
        Company cityclubrentals = new Company("City Club Rentals");
        companyDB.add(cityclubrentals);

    }
}
