package com.lambdaschool.javaorders;

import com.lambdaschool.javaorders.models.Orders;
import com.lambdaschool.javaorders.repository.AgentsRepository;
import com.lambdaschool.javaorders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController
{
    @Autowired
    OrdersRepository custrepos;

    @Autowired
    OrdersRepository ordersrepos;

    @Autowired
    AgentsRepository agentsrepos;

    /*
    GET /orders - return all the orders
    GET /orders/ordnum/{ordnum}
    POST /orders - adds an order
    PUT /orders/ordnum/{ordnum} - updates an order based on ordnum
    DELETE /orders/ordnum/{ordnum} - deletes an order based off its ordnum
     */

    @GetMapping("/orders")
    public List<Orders> allOrders() throws URISyntaxException
    {
        return ordersrepos.findAll();
    }

    @GetMapping("/orders/ordnum/{ordnum}")
    public Orders specificOrder(@PathVariable long ordnum) throws URISyntaxException
    {
        var foundOrd = ordersrepos.findById(ordnum);
        if (foundOrd.isPresent())
        {
            return foundOrd.get();
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/orders")
    public Orders newOrder(@RequestBody Orders newOrder) throws URISyntaxException
    {
        return ordersrepos.save(newOrder);
    }

    @PutMapping("/orders/ordnum/{ordnum}")
    public Orders changeOrders(@RequestBody Orders newVending, @PathVariable long ordnum) throws URISyntaxException
    {
        Optional<Orders> updatedVending = ordersrepos.findById(ordnum);
        if (updatedVending.isPresent())
        {
            newVending.setOrdnum(ordnum);
            ordersrepos.save(newVending);

            return newVending;
        }
        else
        {
            return updatedVending.get();
        }
    }

    @DeleteMapping("/orders/ordnum/{ordnum}")
    public Orders deleteCustomer(@PathVariable long ordnum)
    {
        var foundOrd = custrepos.findById(ordnum);
        if(foundOrd.isPresent())
        {
            custrepos.deleteById(ordnum);
            return foundOrd.get();
        }
        else
        {
            return null;
        }
    }

}
