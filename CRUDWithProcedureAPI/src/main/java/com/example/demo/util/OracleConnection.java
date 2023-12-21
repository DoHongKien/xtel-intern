package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class OracleConnection {

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kien03", "2003");
        } catch (SQLException ex) {
            return null;
        }
    }
}
