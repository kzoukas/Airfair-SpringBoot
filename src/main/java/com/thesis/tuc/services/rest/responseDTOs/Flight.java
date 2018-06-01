package com.thesis.tuc.services.rest.responseDTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flights")
public class Flight {
    @Id
    private String id;
    private String flightSearchFromIata;
    private String flightSearchToIata;
    private String checkin;
    private String checkout;
    private String adultNum;
    private String childNum;
    private String typeOfFlight;
    private String airportSize;
    private String tripDistance;
    private String fromDepartureTime;
    private String fromArrivalTime;
    private String fromTown;
    private String toTown;
    private String fromIata;
    private String toIata;
    private Double price;
    private String fromCompany;
    private String airlineImgSrc;
    private String toCompany;
    private String fromDate;
    private String toDate;
    private String duration;
    private String station;
    private String stationTown;
    private String stationArrivalTime;
    private String stationArrivalDate;
    private String waitingTime;
    private String stationDepTime;
    private String stationDepDate;
    private String toTime;



    public Flight(String flightSearchFromIata,
                  String flightSearchToIata,
                  String checkin,
                  String checkout,
                  String adultNum,
                  String childNum,
                  String typeOfFlight,
                  String airportSize,
                  String tripDistance,
                  String fromDepartureTime,
                  String fromArrivalTime,
                  String fromTown,
                  String toTown,
                  String fromIata,
                  String toIata,
                  Double price,
                  String fromCompany,
                  String airlineImgSrc,
                  String toCompany,
                  String fromDate,
                  String toDate,
                  String duration,
                  String station,
                  String stationTown,
                  String stationArrivalTime,
                  String stationArrivalDate,
                  String waitingTime,
                  String stationDepTime,
                  String stationDepDate,
                  String toTime) {
        this.flightSearchFromIata = flightSearchFromIata;
        this.flightSearchToIata = flightSearchToIata;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adultNum = adultNum;
        this.childNum = childNum;
        this.typeOfFlight = typeOfFlight;
        this.airportSize = airportSize;
        this.tripDistance = tripDistance;
        this.fromDepartureTime = fromDepartureTime;
        this.fromArrivalTime = fromArrivalTime;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.fromIata = fromIata;
        this.toIata = toIata;
        this.price = price;
        this.fromCompany = fromCompany;
        this.airlineImgSrc=airlineImgSrc;
        this.toCompany = toCompany;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.duration = duration;
        this.station = station;
        this.stationTown = stationTown;
        this.stationArrivalTime = stationArrivalTime;
        this.stationArrivalDate = stationArrivalDate;
        this.waitingTime = waitingTime;
        this.stationDepTime = stationDepTime;
        this.stationDepDate = stationDepDate;
        this.toTime = toTime;
    }


    public String getId() {
        return id;
    }

    public String getFlightSearchFromIata() {
        return flightSearchFromIata;
    }

    public String getFlightSearchToIata() {
        return flightSearchToIata;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getAdultNum() {
        return adultNum;
    }

    public String getChildNum() {
        return childNum;
    }

    public String getTypeOfFlight() {
        return typeOfFlight;
    }

    public String getAirportSize() {
        return airportSize;
    }

    public String getTripDistance() {
        return tripDistance;
    }

    public String getFromDepartureTime() {
        return fromDepartureTime;
    }

    public String getFromArrivalTime() {
        return fromArrivalTime;
    }

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

    public Double getPrice() {
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

    public String getDuration() {
        return duration;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStationTown() {
        return stationTown;
    }

    public String getStationArrivalTime() {
        return stationArrivalTime;
    }

    public String getStationArrivalDate() {
        return stationArrivalDate;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public String getStationDepTime() {
        return stationDepTime;
    }

    public String getStationDepDate() {
        return stationDepDate;
    }

    public String getToTime() {
        return toTime;
    }

    public String getAirlineImgSrc() {
        return airlineImgSrc;
    }



}
