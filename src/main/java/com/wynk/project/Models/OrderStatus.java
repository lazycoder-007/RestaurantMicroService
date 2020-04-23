package com.wynk.project.Models;

public enum OrderStatus {

    ACCEPTED("Accepted"), NOTACCEPTED("NotAccepted"), COMPLETED("Completed");

    String status;

    OrderStatus()
    {

    }

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
