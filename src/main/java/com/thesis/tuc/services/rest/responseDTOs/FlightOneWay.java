package com.thesis.tuc.services.rest.responseDTOs;

public class FlightOneWay {

    private String flightSearchFromIata;
    private String flightSearchToIata;

// Direct Flight
    private String departureFrom;
    private String fromDepartureTime;
    private String fromDepartureDate;
    private String departureTo;
    private String station;
    private String duration;
    private String fromCompany;
    private String toDepartureTime;
    private String toLastDepartureTime; //so to avoid printing time +1 (next day) at the last flight time
    private String toDepartureDate;
    private Double price;

    //+ Flight up to 1 station
    private String departureToStation1;
    private String departurefromStation1;
    private String station1ArrivalTime;
    private String station1ArrivalDate;
    private String station1DepTime;
    private String station1DepDate;
    private String waitingTimeStation1;
    private String airlineToStation1;
    private String airlineToStation1imgSrc;
    private String airlineFromStation1;
    private String airlineFromStation1imgSrc;
    private String durationToStation1;
    private String durationFromStation1;

    //+ Flight up to 2 stations
    private String departureToStation2;
    private String departurefromStation2;
    private String station2ArrivalTime;
    private String station2ArrivalDate;
    private String station2DepTime;
    private String station2DepDate;
    private String waitingTimeStation2;
    private String airlineFromStation2;
    private String airlineFromStation2imgSrc;
    private String durationToStation2;
    private String durationFromStation2;

    // Direct Flight Return
    private String departureFromReturn;
    private String fromDepartureTimeReturn;
    private String fromDepartureDateReturn;
    private String departureToReturn;
    private String stationReturn;
    private String durationReturn;
    private String fromCompanyReturn;
    private String toDepartureTimeReturn;
    private String toLastDepartureTimeReturn; //so to avoid printing time +1 (next day) at the last flight time
    private String toDepartureDateReturn;
    private Double priceReturn;
    private String airlineToStation1imgSrcReturn;

    private Double finalPriceRoundTrip;

    //+ Flight up to 1 station return
    private String departureToStation1Return;
    private String departurefromStation1Return;
    private String station1ArrivalTimeReturn;
    private String station1ArrivalDateReturn;
    private String station1DepTimeReturn;
    private String station1DepDateReturn;
    private String waitingTimeStation1Return;
    private String airlineToStation1Return;
    private String airlineFromStation1Return;
    private String airlineFromStation1imgSrcReturn;
    private String durationToStation1Return;
    private String durationFromStation1Return;

    //+ Flight up to 2 stations return
    private String departureToStation2Return;
    private String departurefromStation2Return;
    private String station2ArrivalTimeReturn;
    private String station2ArrivalDateReturn;
    private String station2DepTimeReturn;
    private String station2DepDateReturn;
    private String waitingTimeStation2Return;
    private String airlineFromStation2Return;
    private String airlineFromStation2imgSrcReturn;
    private String durationToStation2Return;
    private String durationFromStation2Return;


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

    public void setToLastDepartureTime(String toLastDepartureTime) {
        this.toLastDepartureTime = toLastDepartureTime;
    }

    public String getToLastDepartureTime() {
        return toLastDepartureTime;
    }

    public String getAirlineToStation1imgSrc() {
        return airlineToStation1imgSrc;
    }

    public void setAirlineToStation1imgSrc(String airlineTotation1imgSrc) {
        this.airlineToStation1imgSrc = airlineTotation1imgSrc;
    }

    public String getAirlineFromStation1imgSrc() {
        return airlineFromStation1imgSrc;
    }

    public void setAirlineFromStation1imgSrc(String airlineFromStation1imgSrc) {
        this.airlineFromStation1imgSrc = airlineFromStation1imgSrc;
    }

    public String getAirlineFromStation2imgSrc() {
        return airlineFromStation2imgSrc;
    }

    public void setAirlineFromStation2imgSrc(String airlineFromStation2imgSrc) {
        this.airlineFromStation2imgSrc = airlineFromStation2imgSrc;
    }

    public String getDepartureFromReturn() {
        return departureFromReturn;
    }

    public void setDepartureFromReturn(String departureFromReturn) {
        this.departureFromReturn = departureFromReturn;
    }

    public String getFromDepartureTimeReturn() {
        return fromDepartureTimeReturn;
    }

    public void setFromDepartureTimeReturn(String fromDepartureTimeReturn) {
        this.fromDepartureTimeReturn = fromDepartureTimeReturn;
    }

    public String getFromDepartureDateReturn() {
        return fromDepartureDateReturn;
    }

    public void setFromDepartureDateReturn(String fromDepartureDateReturn) {
        this.fromDepartureDateReturn = fromDepartureDateReturn;
    }

    public String getDepartureToReturn() {
        return departureToReturn;
    }

    public void setDepartureToReturn(String departureToReturn) {
        this.departureToReturn = departureToReturn;
    }

    public String getStationReturn() {
        return stationReturn;
    }

    public void setStationReturn(String stationReturn) {
        this.stationReturn = stationReturn;
    }

    public String getDurationReturn() {
        return durationReturn;
    }

    public void setDurationReturn(String durationReturn) {
        this.durationReturn = durationReturn;
    }

    public String getFromCompanyReturn() {
        return fromCompanyReturn;
    }

    public void setFromCompanyReturn(String fromCompanyReturn) {
        this.fromCompanyReturn = fromCompanyReturn;
    }

    public String getToDepartureTimeReturn() {
        return toDepartureTimeReturn;
    }

    public void setToDepartureTimeReturn(String toDepartureTimeReturn) {
        this.toDepartureTimeReturn = toDepartureTimeReturn;
    }

    public String getToLastDepartureTimeReturn() {
        return toLastDepartureTimeReturn;
    }

    public void setToLastDepartureTimeReturn(String toLastDepartureTimeReturn) {
        this.toLastDepartureTimeReturn = toLastDepartureTimeReturn;
    }

    public String getToDepartureDateReturn() {
        return toDepartureDateReturn;
    }

    public void setToDepartureDateReturn(String toDepartureDateReturn) {
        this.toDepartureDateReturn = toDepartureDateReturn;
    }

    public Double getPriceReturn() {
        return priceReturn;
    }

    public void setPriceReturn(Double priceReturn) {
        this.priceReturn = priceReturn;
    }

    public String getAirlineToStation1imgSrcReturn() {
        return airlineToStation1imgSrcReturn;
    }

    public void setAirlineToStation1imgSrcReturn(String airlineToStation1imgSrcReturn) {
        this.airlineToStation1imgSrcReturn = airlineToStation1imgSrcReturn;
    }

    public Double getFinalPriceRoundTrip() {
        return finalPriceRoundTrip;
    }

    public void setFinalPriceRoundTrip(Double finalPriceRoundTrip) {
        this.finalPriceRoundTrip = finalPriceRoundTrip;
    }

    public String getDepartureToStation1Return() {
        return departureToStation1Return;
    }

    public void setDepartureToStation1Return(String departureToStation1Return) {
        this.departureToStation1Return = departureToStation1Return;
    }

    public String getDeparturefromStation1Return() {
        return departurefromStation1Return;
    }

    public void setDeparturefromStation1Return(String departurefromStation1Return) {
        this.departurefromStation1Return = departurefromStation1Return;
    }

    public String getStation1ArrivalTimeReturn() {
        return station1ArrivalTimeReturn;
    }

    public void setStation1ArrivalTimeReturn(String station1ArrivalTimeReturn) {
        this.station1ArrivalTimeReturn = station1ArrivalTimeReturn;
    }

    public String getStation1ArrivalDateReturn() {
        return station1ArrivalDateReturn;
    }

    public void setStation1ArrivalDateReturn(String station1ArrivalDateReturn) {
        this.station1ArrivalDateReturn = station1ArrivalDateReturn;
    }

    public String getStation1DepTimeReturn() {
        return station1DepTimeReturn;
    }

    public void setStation1DepTimeReturn(String station1DepTimeReturn) {
        this.station1DepTimeReturn = station1DepTimeReturn;
    }

    public String getStation1DepDateReturn() {
        return station1DepDateReturn;
    }

    public void setStation1DepDateReturn(String station1DepDateReturn) {
        this.station1DepDateReturn = station1DepDateReturn;
    }

    public String getWaitingTimeStation1Return() {
        return waitingTimeStation1Return;
    }

    public void setWaitingTimeStation1Return(String waitingTimeStation1Return) {
        this.waitingTimeStation1Return = waitingTimeStation1Return;
    }

    public String getAirlineToStation1Return() {
        return airlineToStation1Return;
    }

    public void setAirlineToStation1Return(String airlineToStation1Return) {
        this.airlineToStation1Return = airlineToStation1Return;
    }

    public String getAirlineFromStation1Return() {
        return airlineFromStation1Return;
    }

    public void setAirlineFromStation1Return(String airlineFromStation1Return) {
        this.airlineFromStation1Return = airlineFromStation1Return;
    }

    public String getAirlineFromStation1imgSrcReturn() {
        return airlineFromStation1imgSrcReturn;
    }

    public void setAirlineFromStation1imgSrcReturn(String airlineFromStation1imgSrcReturn) {
        this.airlineFromStation1imgSrcReturn = airlineFromStation1imgSrcReturn;
    }

    public String getDurationToStation1Return() {
        return durationToStation1Return;
    }

    public void setDurationToStation1Return(String durationToStation1Return) {
        this.durationToStation1Return = durationToStation1Return;
    }

    public String getDurationFromStation1Return() {
        return durationFromStation1Return;
    }

    public void setDurationFromStation1Return(String durationFromStation1Return) {
        this.durationFromStation1Return = durationFromStation1Return;
    }

    public String getDepartureToStation2Return() {
        return departureToStation2Return;
    }

    public void setDepartureToStation2Return(String departureToStation2Return) {
        this.departureToStation2Return = departureToStation2Return;
    }

    public String getDeparturefromStation2Return() {
        return departurefromStation2Return;
    }

    public void setDeparturefromStation2Return(String departurefromStation2Return) {
        this.departurefromStation2Return = departurefromStation2Return;
    }

    public String getStation2ArrivalTimeReturn() {
        return station2ArrivalTimeReturn;
    }

    public void setStation2ArrivalTimeReturn(String station2ArrivalTimeReturn) {
        this.station2ArrivalTimeReturn = station2ArrivalTimeReturn;
    }

    public String getStation2ArrivalDateReturn() {
        return station2ArrivalDateReturn;
    }

    public void setStation2ArrivalDateReturn(String station2ArrivalDateReturn) {
        this.station2ArrivalDateReturn = station2ArrivalDateReturn;
    }

    public String getStation2DepTimeReturn() {
        return station2DepTimeReturn;
    }

    public void setStation2DepTimeReturn(String station2DepTimeReturn) {
        this.station2DepTimeReturn = station2DepTimeReturn;
    }

    public String getStation2DepDateReturn() {
        return station2DepDateReturn;
    }

    public void setStation2DepDateReturn(String station2DepDateReturn) {
        this.station2DepDateReturn = station2DepDateReturn;
    }

    public String getWaitingTimeStation2Return() {
        return waitingTimeStation2Return;
    }

    public void setWaitingTimeStation2Return(String waitingTimeStation2Return) {
        this.waitingTimeStation2Return = waitingTimeStation2Return;
    }

    public String getAirlineFromStation2Return() {
        return airlineFromStation2Return;
    }

    public void setAirlineFromStation2Return(String airlineFromStation2Return) {
        this.airlineFromStation2Return = airlineFromStation2Return;
    }

    public String getAirlineFromStation2imgSrcReturn() {
        return airlineFromStation2imgSrcReturn;
    }

    public void setAirlineFromStation2imgSrcReturn(String airlineFromStation2imgSrcReturn) {
        this.airlineFromStation2imgSrcReturn = airlineFromStation2imgSrcReturn;
    }

    public String getDurationToStation2Return() {
        return durationToStation2Return;
    }

    public void setDurationToStation2Return(String durationToStation2Return) {
        this.durationToStation2Return = durationToStation2Return;
    }

    public String getDurationFromStation2Return() {
        return durationFromStation2Return;
    }

    public void setDurationFromStation2Return(String durationFromStation2Return) {
        this.durationFromStation2Return = durationFromStation2Return;
    }
}
