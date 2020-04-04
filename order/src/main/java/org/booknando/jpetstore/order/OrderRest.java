package org.booknando.jpetstore.order;

import org.booknando.jpetstore.order.domain.Order;
import org.booknando.jpetstore.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRest {

    @Autowired
    private IOrderService service;


    @RequestMapping(path = "/order/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderId") int orderId) {
        return service.getOrder(orderId);
    }

}
