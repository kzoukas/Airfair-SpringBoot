package com.thesis.tuc.services.rest.responseDTOs;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flights")
public class Flight {
    @Id
    private String id;
    private String fromDepartureTime;
    private String fromArrivalTime;
//    private String toDepartureTime;
//    private String toArrivalTime;
    private String fromTown;
    private String toTown;
    private String fromIata;
    private String toIata;
    private String price;
    private String fromCompany;
    private String toCompany;
    private String fromDate;
    private String toDate;
    private String longitude_from;
    private String longitude_to;
    private String latitude_from;
    private String latitude_to;




    public Flight(String fromDepartureTime, String fromArrivalTime, String fromTown, String toTown, String price, String fromCompany, String toCompany, String fromDate, String toDate, String longitude_from, String longitude_to, String latitude_from, String latitude_to) {
        this.fromDepartureTime = fromDepartureTime;
        this.fromArrivalTime = fromArrivalTime;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.fromIata=fromIata;

        this.toIata= toIata;
//        this.toDepartureTime = toDepartureTime;
//        this.toArrivalTime = toArrivalTime;
        this.price = price;
        this.fromCompany = fromCompany;
        this.toCompany = toCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.longitude_from = longitude_from;
        this.longitude_to = longitude_to;
        this.latitude_from = latitude_from;

        this.latitude_to = latitude_to;
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

//    public String getToDepartureTime() {
//        return toDepartureTime;
//    }
//
//    public String getToArrivalTime() {
//        return toArrivalTime;
//    }

    public String getFromTown() {
        return fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public String getFromIata() {
        return fromIata;
    }

    public String getToIata() {
        return toIata;
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

    public String getLongitude_from() {
        return longitude_from;
    }

    public String getLongitude_to() {
        return longitude_to;
    }

    public String getLatitude_from() {
        return latitude_from;
    }

    public String getLatitude_to() {
        return latitude_to;
    }
}
