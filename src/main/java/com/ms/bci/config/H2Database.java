package com.ms.bci.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:~/testdb;DB_CLOSE_ON_EXIT=FALSE";
        String user = "sa";
        String password = "";

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexión establecida: " + conn);
    }
}

