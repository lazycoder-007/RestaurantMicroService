package com.wynk.project.exceptions;

public class OrderIdNotFoundException extends  RuntimeException {

    private String message;

    public OrderIdNotFoundException(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
