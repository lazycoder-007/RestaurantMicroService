package com.wynk.project.Models;

import java.util.Date;

public class DeliveryPerson {
    Integer id;
    DeliveryPersonStatus status;
    Integer orderId;
    Date timeAtWhichHeStarted;
    Long timeRemainingInSeconds;

    public DeliveryPerson() {
        this.id = null;
        this.status = DeliveryPersonStatus.INACTIVE;
        this.orderId = null;
        this.timeAtWhichHeStarted = null;
        this.timeRemainingInSeconds = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeliveryPersonStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryPersonStatus status) {
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getTimeAtWhichHeStarted() {
        return timeAtWhichHeStarted;
    }

    public void setTimeAtWhichHeStarted(Date timeAtWhichHeStarted) {
        this.timeAtWhichHeStarted = timeAtWhichHeStarted;
    }

    public Long getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }

    public void setTimeRemainingInSeconds() {
    }
}
