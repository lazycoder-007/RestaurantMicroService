package com.wynk.project.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetails {

    @Id
    Integer orderId;
    OrderStatus status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
