package com.codecool.database;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionPropertyValues {
    private HashMap<String, String> connectionProperties = new HashMap<>();
    private InputStream inputStream;

    //get the database connection properties from
    //connection/connection.properties config file

    public HashMap<String, String> getPropValuesOfDB() {
        try {
            Properties prop = new Properties();
            String propFileNAme = "connection/connection.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileNAme);
            if (inputStream != null) {
                prop.load(inputStream);
            }
            connectionProperties.put("url", prop.getProperty("url"));
            connectionProperties.put("database", prop.getProperty("database"));
            connectionProperties.put("user", prop.getProperty("user"));
            connectionProperties.put("password", prop.getProperty("password"));
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connectionProperties;
    }
}
