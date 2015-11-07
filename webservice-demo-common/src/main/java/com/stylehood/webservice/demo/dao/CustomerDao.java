package com.stylehood.webservice.demo.dao;

import com.stylehood.webservice.demo.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao extends AbstractDao {

    public Customer queryById(int id) throws Exception {
        Connection conn = super.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from customer where customer_id = ?");
        pstmt.setLong(1, id);

        Customer customer = null;
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setCity(rs.getString("city"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
        }
        super.closeDBResources(rs, pstmt, conn);
        return customer;
    }

    public void updateEmail(int id, String email) throws Exception {
        Connection conn = super.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("update customer set email = ? where customer_id = ?");
        pstmt.setString(1, email);
        pstmt.setLong(2, id);
        pstmt.executeUpdate();
        super.closeDBResources(pstmt, conn);
    }

    public Customer createCustomer(Customer customer) throws Exception {
        Connection conn = super.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into customer (customer_id, discount_code, zip, name, city, phone, email) values (?,?,?,?,?,?,?)");
        int newId = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(5, 13));
        customer.setId(newId);
        
        pstmt.setLong(1, newId);
        pstmt.setString(2, "M");
        pstmt.setString(3, "95117");
        pstmt.setString(4, customer.getName());
        pstmt.setString(5, customer.getCity());
        pstmt.setString(6, customer.getPhone());
        pstmt.setString(7, customer.getEmail());

        pstmt.executeUpdate();
        super.closeDBResources(pstmt, conn);
        return customer;
    }
    
}
