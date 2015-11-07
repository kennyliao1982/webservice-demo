package com.stylehood.webservice.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {

    protected Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        return conn;
    }

    protected void closeDBResources(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void closeDBResources(Statement stmt, Connection conn) {
        this.closeDBResources(null, stmt, conn);
    }
}
