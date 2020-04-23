package com.wynk.project.services;

import com.wynk.project.Models.DeliveryPerson;
import com.wynk.project.Models.Item;
import com.wynk.project.Models.OrderDetails;
import com.wynk.project.Models.Order;

import java.util.List;

public interface IOrderService {

    public Order placeOrder(Item item);

    public String getOrderStatus(Integer orderId);

    public String updateOrderStatus(OrderDetails orderDetails);

    public List<DeliveryPerson> getActiveDeliveryPersons();
}
