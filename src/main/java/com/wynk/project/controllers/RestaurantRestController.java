package com.wynk.project.controllers;

import com.wynk.project.Models.DeliveryPerson;
import com.wynk.project.Models.Item;
import com.wynk.project.Models.Order;
import com.wynk.project.Models.OrderDetails;
import com.wynk.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRestController {

    @Autowired
    OrderService orderService;

    /**
     * To place the order
     *
     * @param item item to be ordered
     * @return the order with the status
     */
    @RequestMapping(method = RequestMethod.POST, value = "/order")
    public Order placeOrder(@RequestBody Item item) {
        return orderService.placeOrder(item);
    }

    /**
     * To get the status of an order given with order Id
     *
     * @param orderId the order id to get the status of
     * @return the status of the order
     */
    @RequestMapping(method = RequestMethod.GET, value = "/order/{orderId}")
    public String getOrderStatus(@PathVariable Integer orderId) {
        return orderService.getOrderStatus(orderId);
    }

    /**
     * To update the status of the order given the orderId and the updated status
     *
     * @param orderDetails containing order Id and the updated order status
     * @return the updates status
     */
    @RequestMapping(method = RequestMethod.POST, value = "/updateOrder")
    public String updateOrderStatus(@RequestBody OrderDetails orderDetails) {
        return orderService.updateOrderStatus(orderDetails);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getActiveDeliveryPerson")
    public List<DeliveryPerson> getActiveDeliveryPersons()
    {
        return orderService.getActiveDeliveryPersons();
    }
}
