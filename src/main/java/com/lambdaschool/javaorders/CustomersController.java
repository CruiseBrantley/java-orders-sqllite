package com.lambdaschool.javaorders;

import com.lambdaschool.javaorders.models.Customers;
import com.lambdaschool.javaorders.repository.AgentsRepository;
import com.lambdaschool.javaorders.repository.CustomersRepository;
import com.lambdaschool.javaorders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersController
{
    @Autowired
    CustomersRepository custrepos;

    @Autowired
    OrdersRepository ordersrepos;

    @Autowired
    AgentsRepository agentsrepos;

    /*
    GET /customers
    GET /customers/custcode/{custcode}
    POST /customers - adds a customer
    PUT /customers/custocode/{custcode} - updates a customer based on custcode
    DELETE /customers/custcode/{custcode} - Deletes a customer based off of their custcode and deletes all their associated orders
    /customers/order - Returns all customers with their orders
    /customers/name/{custname} - Returns all orders for a particular customer based on name
    /customers/order/{custcode} - Returns all orders for a particular customer based on custcode
    /customers/{custcode} - Deletes a customer based off of their custcode and deletes all their associated orders
    */

    @GetMapping("/customers")
    public List<Customers> allcust()
    {
        return custrepos.findAll();
    }

    @GetMapping("/customers/custcode/{custcode}")
    public Customers findCustId(@PathVariable long custcode)
    {
        var foundCust = custrepos.findById(custcode);
        if (foundCust.isPresent())
        {
            return foundCust.get();
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/customers")
    public Customers newCustomer(@RequestBody Customers customer) throws URISyntaxException
    {
        return custrepos.save(customer);
    }

    @PutMapping("/customers/custcode/{custcode}")
    public Customers changeCust(@RequestBody Customers newCust, @PathVariable long custcode) throws URISyntaxException
    {
        Optional<Customers> updateCust = custrepos.findById(custcode);
        if(updateCust.isPresent())
        {
            newCust.setCustcode(custcode);
            custrepos.save(newCust);

            return newCust;
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/customers/custcode/{custcode}")
    public Customers deleteCustomer(@PathVariable long custcode)
    {
        var foundCust = custrepos.findById(custcode);
        if(foundCust.isPresent())
        {
            custrepos.deleteById(custcode);
            return foundCust.get();
        }
        else
        {
            return null;
        }
    }
}
