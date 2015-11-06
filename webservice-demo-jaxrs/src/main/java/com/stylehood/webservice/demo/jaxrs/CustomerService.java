package com.stylehood.webservice.demo.jaxrs;

import com.stylehood.webservice.demo.model.Customer;
import java.util.Date;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public void createCustomer(Customer customer){
        System.out.println(new Date() + customer.getName());
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("kenny");
        System.out.println(new Date());
        return customer;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateCustomer(@PathParam("id") int id, Customer customer){
        System.out.println(id + "_" + new Date() + customer.getName());
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") int id){
        System.out.println(id);
    }
}
