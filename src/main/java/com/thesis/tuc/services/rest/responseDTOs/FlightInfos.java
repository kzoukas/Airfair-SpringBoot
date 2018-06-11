package com.thesis.tuc.services.rest.responseDTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flightinfos")
public class FlightInfos {
    @Id
    private String id;
    private String fromIata;
    private String toIata;
    private String checkin;
    private String checkout;
    private String adultNum;
    private String childNum;
    private String typeOfFlight;
    private String airportSize;
    private String tripDistance;
    private String flightSearched;

    public FlightInfos(String id, String fromIata,
                       String toIata ,
                       String checkin,
                       String checkout,
                       String adultNum,
                       String childNum,
                       String typeOfFlight,
                       String airportSize,
                       String tripDistance,
                       String flightSearched){

        this.fromIata = fromIata;
        this.toIata = toIata;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adultNum = adultNum;
        this.childNum = childNum;
        this.airportSize = airportSize;
        this.tripDistance = tripDistance;
        this.typeOfFlight = typeOfFlight;
        this.flightSearched = flightSearched;
    }

    public String getId() {
        return id;
    }

    public String getFromIata() {
        return fromIata;
    }

    public String getToIata() {
        return toIata;
    }


    public String getTypeOfFlight() {
        return typeOfFlight;
    }

    public String getFlightSearched() {
        return flightSearched;
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

    public String getAirportSize() {
        return airportSize;
    }

    public String getTripDistance() {
        return tripDistance;
    }
}
