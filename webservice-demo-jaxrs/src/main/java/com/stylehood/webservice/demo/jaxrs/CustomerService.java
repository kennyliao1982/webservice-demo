package com.stylehood.webservice.demo.jaxrs;

import com.stylehood.webservice.demo.dao.CustomerDao;
import com.stylehood.webservice.demo.dao.PurchaseOrderDao;
import com.stylehood.webservice.demo.model.Customer;
import com.stylehood.webservice.demo.model.PurchaseOrder;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {

    private final CustomerDao customerDao = new CustomerDao();
    private final PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public JsonResponse<Customer> createCustomer(Customer customer) {
        JsonResponse<Customer> res = new JsonResponse<>();
        try {
            Customer newCustomer = customerDao.createCustomer(customer);
            res.setData(newCustomer);
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @GET
    @Path("/{id}")
    public JsonResponse<Customer> getCustomer(@PathParam("id") int customerId) {
        JsonResponse<Customer> res = new JsonResponse<>();
        try {
            Customer customer = customerDao.queryById(customerId);
            List<PurchaseOrder> orders = purchaseOrderDao.queryByCustomerId(customerId);
            if (customer != null) {
                customer.setOrders(orders);
            }
            res.setData(customer);
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public JsonResponse<Customer> updateCustomer(@PathParam("id") int customerId, Customer customer) {
        JsonResponse<Customer> res = new JsonResponse<>();
        try {
            customerDao.updateEmail(customerId, customer.getEmail());
            Customer updatedCustomer = customerDao.queryById(customerId);
            res.setData(updatedCustomer);
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        System.out.println(id);
    }
}
