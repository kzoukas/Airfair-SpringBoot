package com.thesis.tuc.services.rest.responseDTOs;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flightinfos")
public class FlightInfo {

    @Id
    private  String id;
    private String fromTown;
    private String toTown;
    private String fromIata;
    private String toIata;
    private String fromDate;
    private String toDate;
    private String roundTrip;



    public FlightInfo(String fromTown, String toTown, String fromIata, String toIata, String fromDate, String toDate, String roundTrip) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.fromIata = fromIata;
        this.toIata = toIata;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.roundTrip = roundTrip;
    }

    public String getId() {
        return id;
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

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

}
