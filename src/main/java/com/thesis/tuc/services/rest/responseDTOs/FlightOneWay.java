package com.thesis.tuc.services.rest.responseDTOs;

public class FlightOneWay {

    private String flightSearchFromIata;
    private String flightSearchToIata;
    private String departureFrom;
    private String fromDepartureTime;
    private String fromDepartureDate;
    private String departureTo;
    private String station;
    private String duration;
    private String fromCompany;
    private String toDepartureTime;
    private String toDepartureDate;

    private String departureToStation1;
    private String departurefromStation1;
    private String station1ArrivalTime;
    private String station1ArrivalDate;
    private String station1DepTime;
    private String station1DepDate;
    private String waitingTimeStation1;
    private String airlineToStation1;
    private String airlineFromStation1;
    private String durationToStation1;
    private String durationFromStation1;

    private String departureToStation2;
    private String departurefromStation2;
    private String station2ArrivalTime;
    private String station2ArrivalDate;
    private String station2DepTime;
    private String station2DepDate;
    private String waitingTimeStation2;

    private String airlineFromStation2;
    private String durationToStation2;
    private String durationFromStation2;

    private Double price;


    public FlightOneWay() {

    }

    public String getFlightSearchFromIata() {
        return flightSearchFromIata;
    }

    public void setFlightSearchFromIata(String flightSearchFromIata) {
        this.flightSearchFromIata = flightSearchFromIata;
    }

    public String getFlightSearchToIata() {
        return flightSearchToIata;
    }

    public void setFlightSearchToIata(String flightSearchToIata) {
        this.flightSearchToIata = flightSearchToIata;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getDepartureToStation1() {
        return departureToStation1;
    }

    public void setDepartureToStation1(String departureToStation1) {
        this.departureToStation1 = departureToStation1;
    }

    public String getDeparturefromStation1() {
        return departurefromStation1;
    }

    public void setDeparturefromStation1(String departurefromStation1) {
        this.departurefromStation1 = departurefromStation1;
    }

    public String getDepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(String departureTo) {
        this.departureTo = departureTo;
    }

    public String getDepartureToStation2() {
        return departureToStation2;
    }

    public void setDepartureToStation2(String departureToStation2) {
        this.departureToStation2 = departureToStation2;
    }

    public String getDeparturefromStation2() {
        return departurefromStation2;
    }

    public void setDeparturefromStation2(String departurefromStation2) {
        this.departurefromStation2 = departurefromStation2;
    }

    public String getFromDepartureTime() {
        return fromDepartureTime;
    }

    public void setFromDepartureTime(String fromDepartureTime) {
        this.fromDepartureTime = fromDepartureTime;
    }

    public String getFromDepartureDate() {
        return fromDepartureDate;
    }

    public void setFromDepartureDate(String fromDepartureDate) {
        this.fromDepartureDate = fromDepartureDate;
    }

    public String getStation1ArrivalTime() {
        return station1ArrivalTime;
    }

    public void setStation1ArrivalTime(String station1ArrivalTime) {
        this.station1ArrivalTime = station1ArrivalTime;
    }

    public String getStation1ArrivalDate() {
        return station1ArrivalDate;
    }

    public void setStation1ArrivalDate(String station1ArrivalDate) {
        this.station1ArrivalDate = station1ArrivalDate;
    }

    public String getStation1DepTime() {
        return station1DepTime;
    }

    public void setStation1DepTime(String station1DepTime) {
        this.station1DepTime = station1DepTime;
    }

    public String getStation1DepDate() {
        return station1DepDate;
    }

    public void setStation1DepDate(String station1DepDate) {
        this.station1DepDate = station1DepDate;
    }

    public String getWaitingTimeStation1() {
        return waitingTimeStation1;
    }

    public void setWaitingTimeStation1(String waitingTimeStation1) {
        this.waitingTimeStation1 = waitingTimeStation1;
    }

    public String getToDepartureDate() {
        return toDepartureDate;
    }

    public void setToDepartureDate(String toDepartureDate) {
        this.toDepartureDate = toDepartureDate;
    }

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getToDepartureTime() {
        return toDepartureTime;
    }

    public void setToDepartureTime(String toDepartureTime) {
        this.toDepartureTime = toDepartureTime;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAirlineToStation1() {
        return airlineToStation1;
    }

    public void setAirlineToStation1(String airlineToStation1) {
        this.airlineToStation1 = airlineToStation1;
    }

    public String getAirlineFromStation1() {
        return airlineFromStation1;
    }

    public void setAirlineFromStation1(String airlineFromStation1) {
        this.airlineFromStation1 = airlineFromStation1;
    }

    public String getDurationToStation1() {
        return durationToStation1;
    }

    public void setDurationToStation1(String durationToStation1) {
        this.durationToStation1 = durationToStation1;
    }

    public String getDurationFromStation1() {
        return durationFromStation1;
    }

    public void setDurationFromStation1(String durationFromStation1) {
        this.durationFromStation1 = durationFromStation1;
    }

    public String getStation2ArrivalTime() {
        return station2ArrivalTime;
    }

    public void setStation2ArrivalTime(String station2ArrivalTime) {
        this.station2ArrivalTime = station2ArrivalTime;
    }

    public String getStation2ArrivalDate() {
        return station2ArrivalDate;
    }

    public void setStation2ArrivalDate(String station2ArrivalDate) {
        this.station2ArrivalDate = station2ArrivalDate;
    }

    public String getStation2DepTime() {
        return station2DepTime;
    }

    public void setStation2DepTime(String station2DepTime) {
        this.station2DepTime = station2DepTime;
    }

    public String getStation2DepDate() {
        return station2DepDate;
    }

    public void setStation2DepDate(String station2DepDate) {
        this.station2DepDate = station2DepDate;
    }

    public String getWaitingTimeStation2() {
        return waitingTimeStation2;
    }

    public void setWaitingTimeStation2(String waitingTimeStation2) {
        this.waitingTimeStation2 = waitingTimeStation2;
    }


    public String getAirlineFromStation2() {
        return airlineFromStation2;
    }

    public void setAirlineFromStation2(String airlineFromStation2) {
        this.airlineFromStation2 = airlineFromStation2;
    }

    public String getDurationToStation2() {
        return durationToStation2;
    }

    public void setDurationToStation2(String durationToStation2) {
        this.durationToStation2 = durationToStation2;
    }

    public String getDurationFromStation2() {
        return durationFromStation2;
    }

    public void setDurationFromStation2(String durationFromStation2) {
        this.durationFromStation2 = durationFromStation2;
    }
}
