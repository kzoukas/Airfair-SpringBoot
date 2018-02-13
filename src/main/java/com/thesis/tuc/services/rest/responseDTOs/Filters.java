package com.thesis.tuc.services.rest.responseDTOs;

public class Filters {

    private Integer maxPrice;

    public Filters(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }
}
