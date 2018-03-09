package com.thesis.tuc.services.rest.responseDTOs;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flightinfos")
public class FlightInfos {
    @Id
    private String id;
    private String fromIata;
    private String toIata;
    private String fromDate;
    private String toDate;
    private String typeOfFlight;
    private String flightSearched;

    public FlightInfos(String id, String fromIata, String toIata, String fromDate, String toDate, String typeOfFlight, String flightSearched) {

        this.fromIata = fromIata;
        this.toIata = toIata;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getTypeOfFlight() {
        return typeOfFlight;
    }

    public String getFlightSearched() {
        return flightSearched;
    }
}
