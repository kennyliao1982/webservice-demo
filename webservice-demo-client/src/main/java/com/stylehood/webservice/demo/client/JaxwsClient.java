package com.stylehood.webservice.demo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylehood.webservice.demo.jaxws.Customer;
import com.stylehood.webservice.demo.jaxws.CustomerService;
import com.stylehood.webservice.demo.jaxws.CustomerService_Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.webservicex.GlobalWeather;

public class JaxwsClient extends HttpServlet {

    private final CustomerService customerService = new CustomerService_Service().getCustomerServicePort();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String command = req.getParameter("command");

        if ("createCustomer".equals(command)) {
            this.createCustomer(req, res);
        } else if ("getCustomer".equals(command)) {
            this.getCustomer(req, res);
        } else if ("updateCustomer".equals(command)) {
            this.updateCustomer(req, res);
        } else if ("getWeather".equals(command)) {
            this.getWeather(req, res);
        }

    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setCity(city);
        customer.setPhone(phone);
        customer.setEmail(email);
        Customer newCustomer = customerService.createCustomer(customer);

        outputJson(res, newCustomer);
    }

    private void getCustomer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        Customer customer = customerService.getCustomer(customerId);
        outputJson(res, customer);
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        String email = req.getParameter("email");

        Customer customer = new Customer();
        customer.setEmail(email);
        Customer newCustomer = customerService.updateCustomer(customerId, customer);
        outputJson(res, newCustomer);
    }

    private void getWeather(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String country = req.getParameter("country");
        String city = req.getParameter("city");

        String weather = new GlobalWeather().getGlobalWeatherSoap().getWeather(city, country);
        
        PrintWriter pw = res.getWriter();
        pw.write(weather);
        pw.flush();
        pw.close();
    }

    private void outputJson(HttpServletResponse res, Object obj) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.writeValue(res.getWriter(), obj);
    }
}
