package com.wynk.project.services;

import com.wynk.project.Models.*;
import com.wynk.project.proxy.DeliverServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    private static final String ORDER_ID_NOT_PRESENT = "OrderId Not present";

    /**
     * Order Id of the Order
     */
    private static Integer orderId = 0;

    /**
     * The ArrayList to contain all the orders in the memory
     */
    private List<Order> orderList = new ArrayList<>();

    @Autowired
    public DeliverServiceProxy deliverServiceProxy;

    @Override
    public Order placeOrder(Item item) {
        Order order = new Order();
        order.setItem(item);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(++orderId);
        orderDetails.setStatus(delegateTheOrder());
        order.setOrderDetails(orderDetails);
        orderList.add(order);
        return order;
    }

    private OrderStatus delegateTheOrder() {
        try {
            List<DeliveryPerson> deliveryPersonList = deliverServiceProxy.getDeliverPersonList();
            Optional<DeliveryPerson> deliveryPersonOptional = deliveryPersonList.stream().filter(deliveryPerson ->
                    deliveryPerson.getStatus().equals(DeliveryPersonStatus.INACTIVE)).findAny();
            if (deliveryPersonOptional.isPresent()) {
                Integer deliveryPersonId = deliveryPersonOptional.get().getId();
                deliverServiceProxy.sendOrderToDeliveryPerson(orderId, deliveryPersonId);
                return OrderStatus.ACCEPTED;
            }
        } catch (Exception e) {
            return OrderStatus.NOTACCEPTED;
        }
        return OrderStatus.NOTACCEPTED;
    }

    @Override
    public String getOrderStatus(Integer orderId) {
        Optional<Order> orderOptional = orderList.stream().filter(order -> order.getOrderDetails().getOrderId().
                equals(orderId)).findFirst();
        if (orderOptional.isPresent()) {
            return orderOptional.get().getOrderDetails().getStatus().getStatus();
        }
        return ORDER_ID_NOT_PRESENT;
    }

    @Override
    public String updateOrderStatus(OrderDetails orderDetails) {
        Optional<Order> orderOptional = orderList.stream().filter(order -> order.getOrderDetails().getOrderId().
                equals(orderDetails.getOrderId())).findAny();
        if (orderOptional.isPresent()) {
            orderOptional.get().getOrderDetails().setStatus(orderDetails.getStatus());
            return orderDetails.getStatus().getStatus();
        }
        return ORDER_ID_NOT_PRESENT;
    }

    @Override
    public List<DeliveryPerson> getActiveDeliveryPersons() {
        List<DeliveryPerson> activeDeliveryPerson = new ArrayList<>();
        try {
            List<DeliveryPerson> deliveryPersonList = deliverServiceProxy.getDeliverPersonList();
            activeDeliveryPerson = deliveryPersonList.stream().filter(deliveryPerson -> deliveryPerson.getStatus().
                    equals(DeliveryPersonStatus.ACTIVE)).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            return activeDeliveryPerson;
        }
        return activeDeliveryPerson;
    }
}
