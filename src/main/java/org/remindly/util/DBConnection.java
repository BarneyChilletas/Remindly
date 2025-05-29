package org.remindly.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class DBConnection {
    private static Connection conn = null;




    public static Connection connect() {
        if (conn != null) {
            return conn;
        }
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
            String dbName = props.getProperty("db.name");
            String dbUser = props.getProperty("db.user");
            String dbPassword = props.getProperty("db.pass");
            String port = props.getProperty("db.port");
            String url = "jdbc:postgresql://" + props.getProperty("db.host") + ":" + port + "/" + dbName;
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.print("Error connecting to PostgreSQL database: ");
            e.printStackTrace();
        }catch (IOException e){
            System.out.print("Error downloading file properties ");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver");
            e.printStackTrace();
        }

        return conn;
    }

}
