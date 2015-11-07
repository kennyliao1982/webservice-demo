package com.stylehood.webservice.demo.jaxws;

import com.stylehood.webservice.demo.dao.CustomerDao;
import com.stylehood.webservice.demo.dao.PurchaseOrderDao;
import com.stylehood.webservice.demo.model.Customer;
import com.stylehood.webservice.demo.model.PurchaseOrder;
import java.util.List;
import javax.jws.WebService;

@WebService(serviceName="CustomerService")
public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();
    private final PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDao();

    public Customer createCustomer(Customer customer) {
        Customer newCustomer = null;
        try {
            newCustomer = customerDao.createCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCustomer;
    }

    public Customer getCustomer(int customerId) {
        Customer customer = null;
        try {
            customer = customerDao.queryById(customerId);
            List<PurchaseOrder> orders = purchaseOrderDao.queryByCustomerId(customerId);
            if (customer != null) {
                customer.setOrders(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer updateCustomer(int customerId, Customer customer) {
        Customer updatedCustomer = null;
        try {
            customerDao.updateEmail(customerId, customer.getEmail());
            updatedCustomer = customerDao.queryById(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedCustomer;
    }
}
