package com.thesis.tuc.services.rest.responseDTOs;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flights")
public class Flight {
    @Id
    private String id;
    private String fromDepartureTime;
    private String fromArrivalTime;
    private String toDepartureTime;
    private String toArrivalTime;
    private String price;
    private String fromCompany;
    private String toCompany;
    private String fromDate;
    private String toDate;

    public Flight(String fromDepartureTime, String fromArrivalTime, String toDepartureTime, String toArrivalTime, String price, String fromCompany, String toCompany, String fromDate, String toDate) {
        this.fromDepartureTime = fromDepartureTime;
        this.fromArrivalTime = fromArrivalTime;
        this.toDepartureTime = toDepartureTime;
        this.toArrivalTime = toArrivalTime;
        this.price = price;
        this.fromCompany = fromCompany;
        this.toCompany = toCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getId() {
        return id;
    }

    public String getFromDepartureTime() {
        return fromDepartureTime;
    }

    public String getFromArrivalTime() {
        return fromArrivalTime;
    }

    public String getToDepartureTime() {
        return toDepartureTime;
    }

    public String getToArrivalTime() {
        return toArrivalTime;
    }

    public String getPrice() {
        return price;
    }

    public String getFromCompany() {
        return fromCompany;
    }

    public String getToCompany() {
        return toCompany;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
