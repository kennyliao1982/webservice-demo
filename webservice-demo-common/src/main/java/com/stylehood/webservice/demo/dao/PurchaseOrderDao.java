package com.stylehood.webservice.demo.dao;

import com.stylehood.webservice.demo.model.PurchaseOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDao extends AbstractDao {

    public List<PurchaseOrder> queryByCustomerId(int customerId) throws Exception {
        Connection conn = super.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from purchase_order where customer_id = ?");
        pstmt.setLong(1, customerId);

        List<PurchaseOrder> orders = new ArrayList<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            PurchaseOrder order = new PurchaseOrder();
            order.setOrderNum(rs.getInt("order_num"));
            order.setCustomerId(rs.getInt("customer_id"));
            order.setProductId(rs.getInt("product_id"));
            order.setQuantity(rs.getInt("quantity"));
            order.setSalesDate(rs.getDate("sales_date"));
           
            orders.add(order);
        }
        super.closeDBResources(rs, pstmt, conn);
        return orders;
    }
    
}
