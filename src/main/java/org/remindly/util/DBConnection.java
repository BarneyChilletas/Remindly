package org.remindly.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private static Connection conn = null;
    private static final String dbName = "remindly";
    private static final String user = "postgres";
    private static final String pass = "bany2007";



    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:postgresql://localhost:5432/" + dbName;
                conn = DriverManager.getConnection(url, user, pass);
            }

        } catch (Exception e) {
            System.out.print("Error: ");
            e.printStackTrace();
        }

        return conn;
    }

}
