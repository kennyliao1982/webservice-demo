
package com.stylehood.jaxws.demo;

import javax.jws.WebService;
import javax.jws.WebParam;


@WebService
public class CustomerService{

    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
