package com.codecool.database.dao;

import com.codecool.model.Company;
import javassist.NotFoundException;

public interface CompanyDAO {
    void add(Company company);
    Company find(int id) throws NotFoundException;
}
