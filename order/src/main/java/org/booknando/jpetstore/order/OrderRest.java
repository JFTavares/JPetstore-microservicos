package org.booknando.jpetstore.order;

import org.booknando.jpetstore.order.domain.Order;
import org.booknando.jpetstore.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderRest {

    @Autowired
    private IOrderService service;


    private final ProductMapper productMapper;

    @RequestMapping(path = "/order/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderId") int orderId) {

        Order order = service.getOrder(orderId);
        if (order == null) {
            throw new ResourceNotFoundException();
        }
        return order;
    }

    @Override
    public Product getProduct(String productId) {
        ResponseEntity<Product> response = rest.getForEntity("http://localhost:8083/catalog/product/" + productId,
                Product.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return null;
        }
        return response.getBody();
    }


    private class ProductMapper {
    }
}
