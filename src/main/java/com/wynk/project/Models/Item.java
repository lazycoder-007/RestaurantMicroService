package com.wynk.project.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    String name;
    Integer noOfItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }
}
