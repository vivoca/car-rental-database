package com.codecool.database.dao;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginHandlerDAODB extends AbstractDBHandler {

    //adds a new user entry to the database
    public void add (String username, String password, String email) throws SQLException {
        try {
            String salt = generateSalt();
            PreparedStatement stmt;
            stmt = getConnection().prepareStatement("INSERT INTO logintable (USERNAME, EMAIL, PASSWORD, SALT) VALUES (?, ?, ?, ?)");

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hash(password + salt));
            stmt.setString(4, salt);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // checks if the input params are the same as the corresponding database params
    public boolean authenticate (String username,  String inputPassword) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String inputHash = hash(inputPassword + getSalt(username));
        return inputHash.equals(getPassword(username));
    }

    public int getId (String username) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM logintable WHERE username=?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getInt("ID");
        }
        return 0;
    }

    //returns the salt which refers to the username param in the database
    private String getSalt(String username) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM logintable WHERE username=?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            return rs.getString("SALT");
        }
        return null;
    }

    //checks if a username is already used by another user in the database
    public Boolean checkIfUsernameExists(String username) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM logintable WHERE username=?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public Boolean checkIfEmailExists(String email) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM logintable WHERE email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next();

    }

    private String hash(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    private String generateSalt() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return hash(new BigInteger(1, bytes).toString(20));
    }

    private String getPassword(String username) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM logintable WHERE username=?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            return rs.getString("PASSWORD");
        }
        return null;
    }



}
