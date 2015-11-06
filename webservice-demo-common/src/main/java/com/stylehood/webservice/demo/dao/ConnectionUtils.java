package com.stylehood.webservice.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM Customer");

        // Process the result set.
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(4);

            System.out.println(id + " " + name);
        }
        rs.close();
        stmt.close();
        conn.close();

        return conn;
    }
    
    public static void main(String[] aa) throws Exception{
     ConnectionUtils.getConnection();
    }
}
