package com.wynk.project.services;

import com.wynk.project.Models.*;
import com.wynk.project.proxy.DeliverServiceProxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    DeliverServiceProxy deliverServiceProxy;

    @Test
    public void testPLaceOrderFunctionality() {
        //given
        OrderService orderService = new OrderService();
        orderService.deliverServiceProxy = deliverServiceProxy;
        Mockito.when(deliverServiceProxy.getDeliverPersonList()).thenReturn(createDummyDeliveryPersonList());
        Item item = getMockItem();
        OrderDetails expectedOrderDetails = new OrderDetails();
        expectedOrderDetails.setOrderId(2);
        expectedOrderDetails.setStatus(OrderStatus.ACCEPTED);

        //when
        Order order = orderService.placeOrder(item);

        //Then
        Assert.assertEquals(item, order.getItem());
        Assert.assertEquals(expectedOrderDetails.getOrderId(), order.getOrderDetails().getOrderId());
        Assert.assertEquals(expectedOrderDetails.getStatus(), order.getOrderDetails().getStatus());
    }

    @Test
    public void testGetOrderStatusFunctionality() {
        //Given
        OrderService orderService = new OrderService();
        orderService.deliverServiceProxy = deliverServiceProxy;
        Mockito.when(deliverServiceProxy.getDeliverPersonList()).thenReturn(createDummyDeliveryPersonList());
        orderService.placeOrder(getMockItem());

        //when
        String status = orderService.getOrderStatus(1);

        //then
        Assert.assertEquals(OrderStatus.ACCEPTED.getStatus(), status);
    }

    private Item getMockItem() {
        Item item = new Item();
        item.setName("name");
        item.setNoOfItems(5);
        return item;
    }

    private List<DeliveryPerson> createDummyDeliveryPersonList() {
        List<DeliveryPerson> deliveryPersonList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deliveryPersonList.add(new DeliveryPerson());
        }
        return deliveryPersonList;
    }

}
