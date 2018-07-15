package com.thesis.tuc.business;
import com.thesis.tuc.repository.AirLogoRepository;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import com.thesis.tuc.services.rest.responseDTOs.AirLogo;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.text.SimpleDateFormat;

@Component
public class FlightService {

    private FlightRepository flightRepository;
    private FlightInfoRepository flightInfoRepository;
    private AirLogoRepository airLogoRepository;
    public FlightService(FlightRepository flightRepository,FlightInfoRepository flightInfoRepository,AirLogoRepository airLogoRepository) {
        this.flightRepository = flightRepository;
        this.flightInfoRepository = flightInfoRepository;
        this.airLogoRepository=airLogoRepository;
    }

    public List<FlightOneWay> getDirectFlight(String fromIata,
                                              String toIata,
                                              String checkin,
                                              String checkout,
                                              String adultNum,
                                              String childNum,
                                              String typeOfFlight,
                                              String airportSize,
                                              String tripDistance,
                                              double maxPrice,
                                              int flightDuration,
                                              int departureTimeStart,
                                              int departureTimeEnd,
                                              int arrivalTimeStart,
                                              int arrivalTimeEnd) {

        String typeOfFlightToSearch = "Direct";
        List<Flight> flights = new ArrayList<>();

        List<FlightOneWay> directFlightsList = new ArrayList<>();
        List<FlightOneWay> flightsListCheckIn = new ArrayList<>();
        List<FlightOneWay> flightsListCheckout = new ArrayList<>();
        if(typeOfFlight.equals("oneWay")) {
            flights = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            for (int i = 0; i < flights.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(fromIata, toIata, maxPrice, flights, directFlightsList, i,
                        flightDuration, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

            }

        }else if(typeOfFlight.equals("roundTrip")){
            List<Flight> flightsCheckIn = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            List<Flight> flightsCheckOut = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            for (int i = 0; i < flightsCheckIn.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(fromIata, toIata, maxPrice, flightsCheckIn, flightsListCheckIn, i,
                        flightDuration, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
            }
            for (int i = 0; i < flightsCheckOut.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(toIata, fromIata, maxPrice, flightsCheckOut, flightsListCheckout, i,
                        flightDuration, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
            }
            for (int i = 0; i < flightsListCheckIn.size(); i++) {
                for (int j = 0; j < flightsListCheckout.size(); j++) {

                    Double finalPrice = flightsListCheckIn.get(i).getPrice() + flightsListCheckout.get(j).getPrice();

                    if(finalPrice<maxPrice) {
                        FlightOneWay flight = new FlightOneWay();
                        flight.setDepartureFrom(flightsListCheckIn.get(i).getDepartureFrom());
                        flight.setDepartureTo(flightsListCheckIn.get(i).getDepartureTo());
                        flight.setFromDepartureTime(flightsListCheckIn.get(i).getFromDepartureTime());
                        flight.setToDepartureTime(flightsListCheckIn.get(i).getToDepartureTime());
                        flight.setDuration(flightsListCheckIn.get(i).getDuration());
                        flight.setStation(flightsListCheckIn.get(i).getStation());
                        flight.setFromDepartureDate(flightsListCheckIn.get(i).getFromDepartureDate());
                        flight.setFromCompany(flightsListCheckIn.get(i).getFromCompany());
                        flight.setAirlineToStation1imgSrc(flightsListCheckIn.get(i).getAirlineToStation1imgSrc());
                        flight.setToDepartureDate(flightsListCheckIn.get(i).getToDepartureDate());
                        flight.setPrice(flightsListCheckIn.get(i).getPrice());

                        flight.setDepartureFromReturn(flightsListCheckout.get(j).getDepartureFrom());
                        flight.setDepartureToReturn(flightsListCheckout.get(j).getDepartureTo());
                        flight.setFromDepartureTimeReturn(flightsListCheckout.get(j).getFromDepartureTime());
                        flight.setToDepartureTimeReturn(flightsListCheckout.get(j).getToDepartureTime());
                        flight.setDurationReturn(flightsListCheckout.get(j).getDuration());
                        flight.setStationReturn(flightsListCheckout.get(j).getStation());
                        flight.setFromDepartureDateReturn(flightsListCheckout.get(j).getFromDepartureDate());
                        flight.setFromCompanyReturn(flightsListCheckout.get(j).getFromCompany());
                        flight.setAirlineToStation1imgSrcReturn(flightsListCheckout.get(j).getAirlineToStation1imgSrc());
                        flight.setToDepartureDateReturn(flightsListCheckout.get(j).getToDepartureDate());
                        flight.setPriceReturn(flightsListCheckout.get(j).getPrice());


                        Double finalPriceRounded = Math.round(finalPrice * 100.0) / 100.0;
                        flight.setFinalPriceRoundTrip(finalPriceRounded);

                        directFlightsList.add(flight);
                    }
                }
            }

        }
        sortedListByPrice(typeOfFlight, directFlightsList);

        List<FlightOneWay> distinctElements = directFlightsList.stream().distinct().collect(Collectors.toList());
        return distinctElements;
    }

    public List<FlightOneWay> getFlightsOneStation(String fromIata,
                                                   String toIata,
                                                   String checkin,
                                                   String checkout,
                                                   String adultNum,
                                                   String childNum,
                                                   String typeOfFlight,
                                                   String airportSize,
                                                   String tripDistance,
                                                   double maxPrice,
                                                   int flightDuration,
                                                   int connectingTimeStart,
                                                   int connectingTimeEnd,
                                                   int departureTimeStart,
                                                   int departureTimeEnd,
                                                   int arrivalTimeStart,
                                                   int arrivalTimeEnd) {

        String typeOfFlightToSearch = "Direct";
        String typeOfFlightToSearch2 = "1 Stop";
        List<Flight> flights = new ArrayList<>();
        List<Flight> flightsDirectUpToOneStationList = new ArrayList<>();
        List<FlightOneWay> flightsListUpToOneStation1 = new ArrayList<>();
        List<FlightOneWay> flightsDirectUpToOneStationNewList = new ArrayList<>();


        List<FlightOneWay> flightsListOneStation = new ArrayList<>();
        List<FlightOneWay> flightsListUpToOneStation = new ArrayList<>();
        List<FlightOneWay> directflightList = getDirectFlight(fromIata, toIata,
                                                         checkin,
                                                         checkout,
                                                         adultNum,
                                                         childNum,
                                                         typeOfFlight,
                                                         airportSize,
                                                         tripDistance,
                                                         maxPrice,
                                                         flightDuration,
                                                         departureTimeStart,
                                                         departureTimeEnd,
                                                         arrivalTimeStart,
                                                         arrivalTimeEnd);
        flightsListUpToOneStation.addAll(directflightList);
        List<FlightOneWay> flightsListCheckIn = new ArrayList<>();
        List<FlightOneWay> flightsListCheckout = new ArrayList<>();
        List<FlightOneWay> flightsDirectUpToOneStationNewListCheckIn = new ArrayList<>();
        List<FlightOneWay> flightsDirectUpToOneStationNewListCheckOut = new ArrayList<>();
        if(typeOfFlight.equals("oneWay")) {
            flightsDirectUpToOneStationList=this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistanceAndFromIataAndToIata( typeOfFlightToSearch2,
                     maxPrice,
                     fromIata,
                     toIata,
                     checkin,
                     checkout,
                     adultNum,
                     childNum,
                     typeOfFlight,
                     airportSize,
                     tripDistance,
                     fromIata,
                     toIata);
            flights = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            for (int i = 0; i < flightsDirectUpToOneStationList.size(); i++) {

                    flightsDirectUpToOneStation(maxPrice, flightsDirectUpToOneStationList, flightsDirectUpToOneStationNewList, i, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

            }
            for (int i = 0; i < flights.size(); i++) {
                for (int j = 0; j < flights.size(); j++) {
                    flightsUpToOneStation(maxPrice, flights, flightsListUpToOneStation1, i, j, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
                }
            }
            flightsListUpToOneStation.addAll(flightsListUpToOneStation1);
            flightsListUpToOneStation.addAll(flightsDirectUpToOneStationNewList);

        }else if(typeOfFlight.equals("roundTrip")){
            List<Flight> flightsDirectUpToOneStationListCheckIn=this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistanceAndFromIataAndToIata( typeOfFlightToSearch2,
                            maxPrice,
                            fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance,
                            fromIata,
                            toIata);
            List<Flight> flightsDirectUpToOneStationListCheckOut=this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistanceAndFromIataAndToIata( typeOfFlightToSearch2,
                            maxPrice,
                            toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance,
                            toIata,
                            fromIata);
            List<Flight> flightsCheckIn = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            List<Flight> flightsCheckOut = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            for (int i = 0; i < flightsDirectUpToOneStationListCheckIn.size(); i++) {

                flightsDirectUpToOneStation(maxPrice, flightsDirectUpToOneStationListCheckIn, flightsDirectUpToOneStationNewListCheckIn, i, flightDuration, connectingTimeStart,
                        connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

            }
            for (int i = 0; i < flightsDirectUpToOneStationListCheckOut.size(); i++) {

                flightsDirectUpToOneStation(maxPrice, flightsDirectUpToOneStationListCheckOut, flightsDirectUpToOneStationNewListCheckOut, i, flightDuration, connectingTimeStart,
                        connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

            }
            for (int i = 0; i < flightsCheckIn.size(); i++) {
                for (int j = 0; j < flightsCheckIn.size(); j++) {
                    flightsUpToOneStation(maxPrice, flightsCheckIn, flightsListCheckIn, i, j, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
                }
            }
            for (int i = 0; i < flightsCheckOut.size(); i++) {
                for (int j = 0; j < flightsCheckOut.size(); j++) {
                    flightsUpToOneStation(maxPrice, flightsCheckOut, flightsListCheckout, i, j, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
                }
            }
            flightsListCheckIn.addAll(flightsDirectUpToOneStationNewListCheckIn);
            flightsListCheckout.addAll(flightsDirectUpToOneStationNewListCheckOut);
            for (int i = 0; i < flightsListCheckIn.size(); i++) {
                for (int j = 0; j < flightsListCheckout.size(); j++) {
                    Double finalPrice=flightsListCheckIn.get(i).getPrice() + flightsListCheckout.get(j).getPrice();
                    if(finalPrice<maxPrice){
                        FlightOneWay flight = new FlightOneWay();
                        flight.setDepartureFrom(flightsListCheckIn.get(i).getDepartureFrom());
                        flight.setDepartureTo(flightsListCheckIn.get(i).getDepartureTo());
                        flight.setFromDepartureTime(flightsListCheckIn.get(i).getFromDepartureTime());
                        flight.setToDepartureTime(flightsListCheckIn.get(i).getToDepartureTime());
                        flight.setDuration(flightsListCheckIn.get(i).getDuration());
                        flight.setStation(flightsListCheckIn.get(i).getStation());
                        flight.setFromDepartureDate(flightsListCheckIn.get(i).getFromDepartureDate());
                        flight.setFromCompany(flightsListCheckIn.get(i).getFromCompany());
                        flight.setAirlineToStation1imgSrc(flightsListCheckIn.get(i).getAirlineToStation1imgSrc());
                        flight.setToDepartureDate(flightsListCheckIn.get(i).getToDepartureDate());
                        flight.setPrice(flightsListCheckIn.get(i).getPrice());

                        flight.setStation1ArrivalTime(flightsListCheckIn.get(i).getStation1ArrivalTime());
                        flight.setStation1ArrivalDate(flightsListCheckIn.get(i).getStation1ArrivalDate());
                        flight.setStation1DepTime(flightsListCheckIn.get(i).getStation1DepTime());
                        flight.setStation1DepDate(flightsListCheckIn.get(i).getStation1DepDate());
                        flight.setDepartureToStation1(flightsListCheckIn.get(i).getDepartureToStation1());
                        flight.setDeparturefromStation1(flightsListCheckIn.get(i).getDeparturefromStation1());
                        flight.setDurationToStation1(flightsListCheckIn.get(i).getDurationToStation1());
                        flight.setDurationFromStation1(flightsListCheckIn.get(i).getDurationFromStation1());
                        flight.setWaitingTimeStation1(flightsListCheckIn.get(i).getWaitingTimeStation1());
                        flight.setAirlineToStation1(flightsListCheckIn.get(i).getAirlineToStation1());
                        flight.setAirlineFromStation1(flightsListCheckIn.get(i).getAirlineFromStation1());
                        flight.setAirlineFromStation1imgSrc(flightsListCheckIn.get(i).getAirlineFromStation1imgSrc());
                        flight.setPrice(flightsListCheckIn.get(i).getPrice());

                        flight.setDepartureFromReturn(flightsListCheckout.get(j).getDepartureFrom());
                        flight.setDepartureToReturn(flightsListCheckout.get(j).getDepartureTo());
                        flight.setFromDepartureTimeReturn(flightsListCheckout.get(j).getFromDepartureTime());
                        flight.setToDepartureTimeReturn(flightsListCheckout.get(j).getToDepartureTime());
                        flight.setDurationReturn(flightsListCheckout.get(j).getDuration());
                        flight.setStationReturn(flightsListCheckout.get(j).getStation());
                        flight.setFromDepartureDateReturn(flightsListCheckout.get(j).getFromDepartureDate());
                        flight.setFromCompanyReturn(flightsListCheckout.get(j).getFromCompany());
                        flight.setAirlineToStation1imgSrcReturn(flightsListCheckout.get(j).getAirlineToStation1imgSrc());
                        flight.setToDepartureDateReturn(flightsListCheckout.get(j).getToDepartureDate());
                        flight.setPriceReturn(flightsListCheckout.get(j).getPrice());
                        flight.setFinalPriceRoundTrip(flightsListCheckIn.get(i).getPrice() + flightsListCheckout.get(j).getPrice());

                        flight.setStation1ArrivalTimeReturn(flightsListCheckout.get(j).getStation1ArrivalTime());
                        flight.setStation1ArrivalDateReturn(flightsListCheckout.get(j).getStation1ArrivalDate());
                        flight.setStation1DepTimeReturn(flightsListCheckout.get(j).getStation1DepTime());
                        flight.setStation1DepDateReturn(flightsListCheckout.get(j).getStation1DepDate());
                        flight.setDepartureToStation1Return(flightsListCheckout.get(j).getDepartureToStation1());
                        flight.setDeparturefromStation1Return(flightsListCheckout.get(j).getDeparturefromStation1());
                        flight.setDurationFromStation1Return(flightsListCheckout.get(j).getDurationFromStation1());
                        flight.setDurationToStation1Return(flightsListCheckout.get(j).getDurationToStation1());
                        flight.setWaitingTimeStation1Return(flightsListCheckout.get(j).getWaitingTimeStation1());
                        flight.setAirlineToStation1Return(flightsListCheckout.get(j).getAirlineToStation1());
                        flight.setAirlineFromStation1Return(flightsListCheckout.get(j).getAirlineFromStation1());
                        flight.setAirlineFromStation1imgSrcReturn(flightsListCheckout.get(j).getAirlineFromStation1imgSrc());
                        flight.setPriceReturn(flightsListCheckout.get(j).getPrice());


                        Double finalPriceRounded = Math.round(finalPrice * 100.0) / 100.0;
                        flight.setFinalPriceRoundTrip(finalPriceRounded);

                        flightsListOneStation.add(flight);
                    }

                }
            }

            flightsListUpToOneStation.addAll(flightsListOneStation);
        }

        sortedListByPrice(typeOfFlight, flightsListUpToOneStation);
        List<FlightOneWay> distinctElements = flightsListUpToOneStation.stream().distinct()
                .collect(Collectors.toList());
        return distinctElements;
    }

    public List<FlightOneWay> getFlightsTwoStations(String fromIata,
                                                    String toIata,
                                                    String checkin,
                                                    String checkout,
                                                    String adultNum,
                                                    String childNum,
                                                    String typeOfFlight,
                                                    String airportSize,
                                                    String tripDistance,
                                                    double maxPrice,
                                                    int flightDuration,
                                                    int connectingTimeStart,
                                                    int connectingTimeEnd,
                                                    int departureTimeStart,
                                                    int departureTimeEnd,
                                                    int arrivalTimeStart,
                                                    int arrivalTimeEnd) {

        List<FlightOneWay> upToOneStationFlightList = getFlightsOneStation(fromIata, toIata,
                checkin,
                checkout,
                adultNum,
                childNum,
                typeOfFlight,
                airportSize,
                tripDistance,
                maxPrice,
                flightDuration,
                connectingTimeStart,
                connectingTimeEnd,
                departureTimeStart,
                departureTimeEnd,
                arrivalTimeStart,
                arrivalTimeEnd);

        List<FlightOneWay> flightsListTwoStations = new ArrayList<>();
        List<FlightOneWay> flightsListUpToTwoStations = new ArrayList<>();
        List<FlightOneWay> flightsListCheckIn = new ArrayList<>();
        List<FlightOneWay> flightsListCheckout = new ArrayList<>();
        flightsListUpToTwoStations.addAll(upToOneStationFlightList);

        List<Flight> flights = new ArrayList<>();
        if(typeOfFlight.equals("oneWay")) {
            flights = this.flightRepository.
                    findDistinctByPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            getFlightUpTo2stationsLists(maxPrice, flightDuration, connectingTimeStart, connectingTimeEnd, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd, flightsListUpToTwoStations, flights);
        }else if(typeOfFlight.equals("roundTrip")){
            List<Flight> flightsCheckIn = this.flightRepository.
                    findDistinctByPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            List<Flight> flightsCheckOut = this.flightRepository.
                    findDistinctByPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( maxPrice, toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);

            getFlightUpTo2stationsLists(maxPrice, flightDuration, connectingTimeStart, connectingTimeEnd,
                    departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd, flightsListCheckIn,
                    flightsCheckIn);
            getFlightUpTo2stationsLists(maxPrice, flightDuration, connectingTimeStart, connectingTimeEnd,
                    departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd, flightsListCheckout,
                    flightsCheckOut);
            for (int i = 0; i < flightsListCheckIn.size(); i++) {
                for (int j = 0; j < flightsListCheckout.size(); j++) {
                    Double finalPrice = flightsListCheckIn.get(i).getPrice() + flightsListCheckout.get(j).getPrice();
                    if(finalPrice<maxPrice) {
                        FlightOneWay flight = new FlightOneWay();
                        flight.setDepartureFrom(flightsListCheckIn.get(i).getDepartureFrom());
                        flight.setDepartureTo(flightsListCheckIn.get(i).getDepartureTo());
                        flight.setFromDepartureTime(flightsListCheckIn.get(i).getFromDepartureTime());
                        flight.setToDepartureTime(flightsListCheckIn.get(i).getToDepartureTime());
                        flight.setDuration(flightsListCheckIn.get(i).getDuration());
                        flight.setStation(flightsListCheckIn.get(i).getStation());
                        flight.setFromDepartureDate(flightsListCheckIn.get(i).getFromDepartureDate());
                        flight.setFromCompany(flightsListCheckIn.get(i).getFromCompany());
                        flight.setAirlineToStation1imgSrc(flightsListCheckIn.get(i).getAirlineToStation1imgSrc());
                        flight.setToDepartureDate(flightsListCheckIn.get(i).getToDepartureDate());
                        flight.setPrice(flightsListCheckIn.get(i).getPrice());

                        flight.setStation1ArrivalTime(flightsListCheckIn.get(i).getStation1ArrivalTime());
                        flight.setStation1ArrivalDate(flightsListCheckIn.get(i).getStation1ArrivalDate());
                        flight.setStation1DepTime(flightsListCheckIn.get(i).getStation1DepTime());
                        flight.setStation1DepDate(flightsListCheckIn.get(i).getStation1DepDate());
                        flight.setDepartureToStation1(flightsListCheckIn.get(i).getDepartureToStation1());
                        flight.setDeparturefromStation1(flightsListCheckIn.get(i).getDeparturefromStation1());
                        flight.setDurationToStation1(flightsListCheckIn.get(i).getDurationToStation1());
                        flight.setDurationFromStation1(flightsListCheckIn.get(i).getDurationFromStation1());
                        flight.setWaitingTimeStation1(flightsListCheckIn.get(i).getWaitingTimeStation1());
                        flight.setAirlineToStation1(flightsListCheckIn.get(i).getAirlineToStation1());
                        flight.setAirlineFromStation1(flightsListCheckIn.get(i).getAirlineFromStation1());
                        flight.setAirlineFromStation1imgSrc(flightsListCheckIn.get(i).getAirlineFromStation1imgSrc());

                        flight.setDepartureToStation2(flightsListCheckIn.get(i).getDepartureToStation2());
                        flight.setDeparturefromStation2(flightsListCheckIn.get(i).getDeparturefromStation2());
                        flight.setStation2ArrivalTime(flightsListCheckIn.get(i).getStation2ArrivalTime());
                        flight.setStation2ArrivalDate(flightsListCheckIn.get(i).getStation2ArrivalDate());
                        flight.setStation2DepTime(flightsListCheckIn.get(i).getStation2DepTime());
                        flight.setStation2DepDate(flightsListCheckIn.get(i).getStation2DepDate());
                        flight.setAirlineFromStation2(flightsListCheckIn.get(i).getAirlineFromStation2());
                        flight.setAirlineFromStation2imgSrc(flightsListCheckIn.get(i).getAirlineFromStation2imgSrc());
                        flight.setDurationToStation2(flightsListCheckIn.get(i).getDurationToStation2());
                        flight.setDurationFromStation2(flightsListCheckIn.get(i).getDurationFromStation2());
                        flight.setWaitingTimeStation2(flightsListCheckIn.get(i).getWaitingTimeStation2());
                        flight.setToLastDepartureTime(flightsListCheckIn.get(i).getToLastDepartureTime());

                        flight.setPrice(flightsListCheckIn.get(i).getPrice());

                        flight.setDepartureFromReturn(flightsListCheckout.get(j).getDepartureFrom());
                        flight.setDepartureToReturn(flightsListCheckout.get(j).getDepartureTo());
                        flight.setFromDepartureTimeReturn(flightsListCheckout.get(j).getFromDepartureTime());
                        flight.setToDepartureTimeReturn(flightsListCheckout.get(j).getToDepartureTime());
                        flight.setDurationReturn(flightsListCheckout.get(j).getDuration());
                        flight.setStationReturn(flightsListCheckout.get(j).getStation());
                        flight.setFromDepartureDateReturn(flightsListCheckout.get(j).getFromDepartureDate());
                        flight.setFromCompanyReturn(flightsListCheckout.get(j).getFromCompany());
                        flight.setAirlineToStation1imgSrcReturn(flightsListCheckout.get(j).getAirlineToStation1imgSrc());
                        flight.setToDepartureDateReturn(flightsListCheckout.get(j).getToDepartureDate());
                        flight.setPriceReturn(flightsListCheckout.get(j).getPrice());
                        flight.setFinalPriceRoundTrip(flightsListCheckIn.get(i).getPrice() + flightsListCheckout.get(j).getPrice());

                        flight.setStation1ArrivalTimeReturn(flightsListCheckout.get(j).getStation1ArrivalTime());
                        flight.setStation1ArrivalDateReturn(flightsListCheckout.get(j).getStation1ArrivalDate());
                        flight.setStation1DepTimeReturn(flightsListCheckout.get(j).getStation1DepTime());
                        flight.setStation1DepDateReturn(flightsListCheckout.get(j).getStation1DepDate());
                        flight.setDepartureToStation1Return(flightsListCheckout.get(j).getDepartureToStation1());
                        flight.setDeparturefromStation1Return(flightsListCheckout.get(j).getDeparturefromStation1());
                        flight.setDurationFromStation1Return(flightsListCheckout.get(j).getDurationFromStation1());
                        flight.setDurationToStation1Return(flightsListCheckout.get(j).getDurationToStation1());
                        flight.setWaitingTimeStation1Return(flightsListCheckout.get(j).getWaitingTimeStation1());
                        flight.setAirlineToStation1Return(flightsListCheckout.get(j).getAirlineToStation1());
                        flight.setAirlineFromStation1Return(flightsListCheckout.get(j).getAirlineFromStation1());
                        flight.setAirlineFromStation1imgSrcReturn(flightsListCheckout.get(j).getAirlineFromStation1imgSrc());
                        flight.setPriceReturn(flightsListCheckout.get(j).getPrice());

                        flight.setDepartureToStation2Return(flightsListCheckout.get(j).getDepartureToStation2());
                        flight.setDeparturefromStation2Return(flightsListCheckout.get(j).getDeparturefromStation2());
                        flight.setStation2ArrivalTimeReturn(flightsListCheckout.get(j).getStation2ArrivalTime());
                        flight.setStation2ArrivalDateReturn(flightsListCheckout.get(j).getStation2ArrivalDate());
                        flight.setStation2DepTimeReturn(flightsListCheckout.get(j).getStation2DepTime());
                        flight.setStation2DepDateReturn(flightsListCheckout.get(j).getStation2DepDate());
                        flight.setAirlineFromStation2Return(flightsListCheckout.get(j).getAirlineFromStation2());
                        flight.setAirlineFromStation2imgSrcReturn(flightsListCheckout.get(j).getAirlineFromStation2imgSrc());
                        flight.setDurationToStation2Return(flightsListCheckout.get(j).getDurationToStation2());
                        flight.setDurationFromStation2Return(flightsListCheckout.get(j).getDurationFromStation2());
                        flight.setWaitingTimeStation2Return(flightsListCheckout.get(j).getWaitingTimeStation2());
                        flight.setToLastDepartureTimeReturn(flightsListCheckout.get(j).getToLastDepartureTime());


                        Double finalPriceRounded = Math.round(finalPrice * 100.0) / 100.0;
                        flight.setFinalPriceRoundTrip(finalPriceRounded);

                        flightsListTwoStations.add(flight);
                    }
                }
            }

            flightsListUpToTwoStations.addAll(flightsListTwoStations);

        }

        sortedListByPrice(typeOfFlight, flightsListUpToTwoStations);
        List<FlightOneWay> distinctFlightList = flightsListUpToTwoStations.stream().distinct()
                .collect(Collectors.toList());
        return distinctFlightList;
    }

    private void getFlightUpTo2stationsLists(double maxPrice, int flightDuration, int connectingTimeStart,
                                             int connectingTimeEnd, int departureTimeStart, int departureTimeEnd,
                                             int arrivalTimeStart, int arrivalTimeEnd, List<FlightOneWay> flightsList,
                                             List<Flight> flightsFromDatabase) {
        for (int i = 0; i < flightsFromDatabase.size(); i++) {
            for (int j = 0; j < flightsFromDatabase.size(); j++) {
                if (flightsFromDatabase.get(i).getStation().equals("Direct") && (flightsFromDatabase.get(j).getStation().equals("1 Stop"))) {
                    upTo2directFlightFirst(maxPrice, flightsFromDatabase, flightsList, i, j, flightDuration, connectingTimeStart,
                                    connectingTimeEnd, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
//                    String stationTown=flightsFromDatabase.get(j).getStationTown();
//                    Matcher stationIata = Pattern.compile("\\(([^)]+)\\)").matcher(stationTown);
//                    while(stationIata.find()) {
//                        if (stationIata.group(1).equals(flightsFromDatabase.get(i).getFromIata())) {
//                            break;
//                        } else {
//                            upTo2directFlightFirst(maxPrice, flightsFromDatabase, flightsList, i, j, flightDuration, connectingTimeStart,
//                                    connectingTimeEnd, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
//                        }
//                    }

                } else if (flightsFromDatabase.get(i).getStation().equals("1 Stop") && (flightsFromDatabase.get(j).getStation().equals("Direct"))) {

                    upTo2directFlightSecond(maxPrice, flightsFromDatabase, flightsList, i, j, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

                }
            }
        }
    }

    private void searchDirectFlights(String fromIata,
                                     String toIata,
                                     double maxPrice,
                                     List<Flight> flights,
                                     List<FlightOneWay> flightsList,
                                     int i,
                                     int flightDuration,
                                     int departureTimeStart,
                                     int departureTimeEnd,
                                     int arrivalTimeStart,
                                     int arrivalTimeEnd) {

//        Locale locale = new Locale("el");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        Date t1,d1,dt1 = null;
        dt1 = getDateFromDepartureTime(flights, i, timeFormat, dateFormat, dt1);
        Date t2,d2,dt2 = null;
        dt2 = getDateFromArrivalTime(flights, i, timeFormat, dateFormat, dt2);
        long flightDurationDirect = dt2.getTime() - dt1.getTime();
        long diffminutes = flightDurationDirect / (60 * 1000) % 60;
        long diffHours = flightDurationDirect / (60 * 60 * 1000);
        String departureTimeString=flights.get(i).getFromDepartureTime();
        int departureTime = Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(), 2)));

        String arrivalTimeString=flights.get(i).getFromArrivalTime();
        int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

        if (flights.get(i).getFromIata().equals(fromIata)
                && (flights.get(i).getToIata().equals(toIata))
                && (flights.get(i).getStation().equals("Direct"))
                && (diffHours < flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (maxPrice > flights.get(i).getPrice()))
        {

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flights.get(i).getFromTown());
            flight.setDepartureTo(flights.get(i).getToTown());
            flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
            flight.setToDepartureTime(flights.get(i).getFromArrivalTime());
            String differHours = String.valueOf(diffHours);
            String differMinutes = String.valueOf(diffminutes);
            flight.setDuration(differHours + "h " + differMinutes + "m");
            flight.setStation("Direct");
            flight.setFromDepartureDate(flights.get(i).getFromDate());
            flight.setFromCompany(flights.get(i).getFromCompany());
            List<AirLogo> airlogos = new ArrayList<>();
            airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(i).getFromCompany());
            flight.setAirlineToStation1imgSrc(airlogos.get(0).getAirLogoSrc());
            flight.setToDepartureDate(flights.get(i).getToDate());
            flight.setPrice(flights.get(i).getPrice());

            flightsList.add(flight);
        }
    }

    private void flightsUpToOneStation(double maxPrice,
                                       List<Flight> flights,
                                       List<FlightOneWay> flightsList,
                                       int i,
                                       int j,
                                       int flightDuration,
                                       int connectingTimeStart,
                                       int connectingTimeEnd,
                                       int departureTimeStart,
                                       int departureTimeEnd,
                                       int arrivalTimeStart,
                                       int arrivalTimeEnd) {

//        Locale locale = new Locale("el");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        Date dt1 = null;
        dt1 = getDateFromArrivalTime(flights, i, timeFormat, dateFormat, dt1);
        Date dt2 = null;

        dt2 = getDateFromDepartureTime(flights, j, timeFormat, dateFormat, dt2);
        Date dt3 = null;
        dt3 = getDateFromDepartureTime(flights, i, timeFormat, dateFormat, dt3);
        Date dt4 = null;
        dt4 = getDateFromArrivalTime(flights, j, timeFormat, dateFormat, dt4);
        Date t3=null;
        try {
            t3 = timeFormat.parse(flights.get(i).getFromDepartureTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date t4=null;
        try {
            t4 = timeFormat.parse(flights.get(j).getFromArrivalTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Find the flights with 1hour differnce
        long waitingTime = dt2.getTime() - dt1.getTime();
        long waitingTimeMinutes = waitingTime / (60 * 1000) % 60;
        long waitingTimeHours = waitingTime / (60 * 60 * 1000);

        long flightDurationUpTo1 = dt4.getTime() - dt3.getTime();
        long diffminutes = flightDurationUpTo1 / (60 * 1000) % 60;
        long diffHours = flightDurationUpTo1 / (60 * 60 * 1000);

        String departureTimeString=flights.get(i).getFromDepartureTime();
        int departureTime=Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(),2)));

        String arrivalTimeString=flights.get(j).getFromArrivalTime();
        int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(),2)));

        Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

        /*    Add to list all pair of flights with station and waiting time at
              least 1 hour so the passenger have time to change plane             */
        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(j).getFlightSearchFromIata()))
                && (flights.get(i).getFlightSearchToIata().equals(flights.get(j).getFlightSearchToIata()))
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(i).getFromIata()))
                && (flights.get(j).getFlightSearchToIata().equals(flights.get(j).getToIata()))
                && (waitingTimeHours >= 1 || waitingTimeMinutes > 45)
                && (finalPrice < maxPrice)
                && (diffHours <flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (connectingTimeStart <= waitingTimeHours)
                && (connectingTimeEnd > waitingTimeHours)){

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flights.get(i).getFromTown());
            flight.setDepartureTo(flights.get(j).getToTown());

            flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
            flight.setFromDepartureDate(flights.get(i).getFromDate());
            flight.setStation1ArrivalTime(flights.get(i).getFromArrivalTime());
            flight.setStation1ArrivalDate(flights.get(i).getToDate());
            flight.setStation1DepTime(flights.get(j).getFromDepartureTime());
            flight.setStation1DepDate(flights.get(j).getFromDate());
            flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
            flight.setToDepartureDate(flights.get(j).getToDate());

            flight.setDepartureToStation1(flights.get(i).getToTown());
            flight.setDeparturefromStation1(flights.get(j).getFromTown());

            String differHours = String.valueOf(diffHours);
            String differMinutes = String.valueOf(diffminutes);
            flight.setDuration(differHours + "h " + differMinutes + "m");
            flight.setDurationToStation1(flights.get(i).getDuration());
            flight.setDurationFromStation1(flights.get(j).getDuration());
            flight.setStation("1 Stop");

            String waitingHours = String.valueOf(waitingTimeHours);
            String waitingMinutes = String.valueOf(waitingTimeMinutes);
            flight.setWaitingTimeStation1(waitingHours + "h " + waitingMinutes + "m");

            flight.setFromCompany(flights.get(i).getFromCompany());
            flight.setAirlineToStation1(flights.get(i).getFromCompany());
            List<AirLogo> airlogos = new ArrayList<>();
            airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(i).getFromCompany());
            flight.setAirlineToStation1imgSrc(airlogos.get(0).getAirLogoSrc());
//            flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
            flight.setAirlineFromStation1(flights.get(j).getFromCompany());
            airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(j).getFromCompany());
            flight.setAirlineFromStation1imgSrc(airlogos.get(0).getAirLogoSrc());
//            flight.setAirlineFromStation1imgSrc(flights.get(j).getAirlineImgSrc());

            finalPrice = Math.round(finalPrice * 100.0) / 100.0;
            flight.setPrice(finalPrice);

            flightsList.add(flight);

        }

    }

    private void flightsDirectUpToOneStation(double maxPrice,
                                       List<Flight> flightsDirectUpToOneStationList,
                                       List<FlightOneWay> flightsDirectUpToOneStationNewList,
                                       int i,
                                       int flightDuration,
                                       int connectingTimeStart,
                                       int connectingTimeEnd,
                                       int departureTimeStart,
                                       int departureTimeEnd,
                                       int arrivalTimeStart,
                                       int arrivalTimeEnd) {

//        Locale locale = new Locale("el");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        Date t1, d1, dt1 = null;
        dt1 = getDateFromDepartureTime(flightsDirectUpToOneStationList, i, timeFormat, dateFormat, dt1);
        Date t2, d2, dt2 = null;
        dt2 = getDateStationArrivalTime(flightsDirectUpToOneStationList, i, timeFormat, dateFormat, dt2);
        Date t3, d3, dt3 = null;
        dt3 = getDateStationDepTime(flightsDirectUpToOneStationList, i, timeFormat, dateFormat, dt3);
        Date t4, d4, dt4 = null;
        dt4 = getDateFromArrivalTime(flightsDirectUpToOneStationList, i, timeFormat, dateFormat, dt4);


        // Find the flights with 1hour differnce
        long waitingTimeStation = dt3.getTime() - dt2.getTime();
        long waitingTimeMinutes = waitingTimeStation / (60 * 1000) % 60;
        long waitingTimeHours = waitingTimeStation / (60 * 60 * 1000);

        long flightDurationUpTo2 = dt4.getTime() - dt1.getTime();
        long diffminutes = flightDurationUpTo2 / (60 * 1000) % 60;
        long diffHours = flightDurationUpTo2 / (60 * 60 * 1000);

        long flightDurationUpTo2Station1 = dt2.getTime() - dt1.getTime();
        long diffminutes1 = flightDurationUpTo2Station1 / (60 * 1000) % 60;
        long diffHours1 = flightDurationUpTo2Station1 / (60 * 60 * 1000);

        long flightDurationUpTo2Station2 = dt4.getTime() - dt3.getTime();
        long diffminutes2 = flightDurationUpTo2Station2 / (60 * 1000) % 60;
        long diffHours2 = flightDurationUpTo2Station2 / (60 * 60 * 1000);

        String departureTimeString=flightsDirectUpToOneStationList.get(i).getFromDepartureTime();
        int departureTime=Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(),2)));

        String arrivalTimeString=flightsDirectUpToOneStationList.get(i).getFromArrivalTime();
        int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(),2)));

        Double finalPrice = flightsDirectUpToOneStationList.get(i).getPrice();

        /*    Add to list all pair of flights with station and waiting time at
              least 1 hour so the passenger have time to change plane             */
        if ((waitingTimeHours >= 1 || waitingTimeMinutes > 45)
                && (finalPrice < maxPrice)
                && (diffHours <flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (connectingTimeStart <= waitingTimeHours)
                && (connectingTimeEnd > waitingTimeHours)){

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flightsDirectUpToOneStationList.get(i).getFromTown());
            flight.setDepartureTo(flightsDirectUpToOneStationList.get(i).getToTown());

            flight.setFromDepartureTime(flightsDirectUpToOneStationList.get(i).getFromDepartureTime());
            flight.setFromDepartureDate(flightsDirectUpToOneStationList.get(i).getFromDate());
            flight.setStation1ArrivalTime(flightsDirectUpToOneStationList.get(i).getStationArrivalTime());
            flight.setStation1ArrivalDate(flightsDirectUpToOneStationList.get(i).getStationArrivalDate());
            flight.setStation1DepTime(flightsDirectUpToOneStationList.get(i).getStationDepTime());
            flight.setStation1DepDate(flightsDirectUpToOneStationList.get(i).getStationDepDate());
            flight.setToDepartureTime(flightsDirectUpToOneStationList.get(i).getFromArrivalTime());
            flight.setToDepartureDate(flightsDirectUpToOneStationList.get(i).getToDate());

            flight.setDepartureToStation1(flightsDirectUpToOneStationList.get(i).getStationTown());
            flight.setDeparturefromStation1(flightsDirectUpToOneStationList.get(i).getStationTown());

            String differHours = String.valueOf(diffHours);
            String differMinutes = String.valueOf(diffminutes);
            flight.setDuration(differHours + "h " + differMinutes + "m");

            String differHours1 = String.valueOf(diffHours1);
            String differMinutes1 = String.valueOf(diffminutes1);
            flight.setDurationToStation1(differHours1 + "h " + differMinutes1 + "m");

            String differHours2 = String.valueOf(diffHours2);
            String differMinutes2 = String.valueOf(diffminutes2);
            flight.setDurationFromStation1(differHours2 + "h " + differMinutes2 + "m");

            //flight.setDurationFromStation1(flightsDirectUpToOneStationList.get(i).getDuration());
            flight.setStation("1 Stop");

            String waitingHours = String.valueOf(waitingTimeHours);
            String waitingMinutes = String.valueOf(waitingTimeMinutes);
            flight.setWaitingTimeStation1(waitingHours + "h " + waitingMinutes + "m");

            flight.setFromCompany(flightsDirectUpToOneStationList.get(i).getFromCompany());
            flight.setAirlineToStation1(flightsDirectUpToOneStationList.get(i).getFromCompany());
            List<AirLogo> airlogos = new ArrayList<>();
            airlogos=this.airLogoRepository.findDistinctByCompanyName(flightsDirectUpToOneStationList.get(i).getFromCompany());
            flight.setAirlineToStation1imgSrc(airlogos.get(0).getAirLogoSrc());
            flight.setAirlineFromStation1(flightsDirectUpToOneStationList.get(i).getFromCompanyAfterStation());
            airlogos=this.airLogoRepository.findDistinctByCompanyName(flightsDirectUpToOneStationList.get(i).getFromCompanyAfterStation());
            flight.setAirlineFromStation1imgSrc(airlogos.get(0).getAirLogoSrc());

            finalPrice = Math.round(finalPrice * 100.0) / 100.0;
            flight.setPrice(finalPrice);

            flightsDirectUpToOneStationNewList.add(flight);

        }

    }

    private void upTo2directFlightFirst(double maxPrice,
                                        List<Flight> flights,
                                        List<FlightOneWay> flightsList,
                                        int i,
                                        int j,
                                        int flightDuration,
                                        int connectingTimeStart,
                                        int connectingTimeEnd,
                                        int departureTimeStart,
                                        int departureTimeEnd,
                                        int arrivalTimeStart,
                                        int arrivalTimeEnd) {

        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(j).getFlightSearchFromIata()))
                && (flights.get(i).getFlightSearchToIata().equals(flights.get(j).getFlightSearchToIata()))
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(i).getFromIata()))
                && (flights.get(j).getFlightSearchToIata().equals(flights.get(j).getToIata()))) {

//            Locale locale = new Locale("el");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

            Date t1,d1,dt1 = null;
            dt1 = getDateFromDepartureTime(flights, i, timeFormat, dateFormat, dt1);
            Date t2,d2,dt2 = null;
            dt2 = getDateFromArrivalTime(flights, i, timeFormat, dateFormat, dt2);
            Date t3,d3,dt3 = null;
            dt3 = getDateFromDepartureTime(flights, j, timeFormat, dateFormat, dt3);
            Date t4,d4,dt4 = null;
            dt4 = getDateStationArrivalTime(flights, j, timeFormat, dateFormat, dt4);
            Date t5,d5,dt5 = null;
            dt5 = getDateStationDepTime(flights, j, timeFormat, dateFormat, dt5);
            Date t6,d6,dt6 = null;
            dt6 = getDateFromArrivalTime(flights, j, timeFormat, dateFormat, dt6);

            // Find the flights with 1hour differnce
            long waitingTimeStation1 = dt3.getTime() - dt2.getTime();
            long waitingTimeMinutes1 = waitingTimeStation1 / (60 * 1000) % 60;
            long waitingTimeHours1 = waitingTimeStation1 / (60 * 60 * 1000);

            long waitingTimeStation2 = dt5.getTime() - dt4.getTime();
            long waitingTimeMinutes2 = waitingTimeStation2 / (60 * 1000) % 60;
            long waitingTimeHours2 = waitingTimeStation2 / (60 * 60 * 1000);

            long flightDurationUpTo2 = dt6.getTime() - dt1.getTime();
            long diffminutes = flightDurationUpTo2 / (60 * 1000) % 60;
            long diffHours = flightDurationUpTo2 / (60 * 60 * 1000);

            String departureTimeString=flights.get(i).getFromDepartureTime();
            int departureTime=Integer.parseInt(departureTimeString.substring(0,Math.min(departureTimeString.length(),2)));

            String arrivalTimeString=flights.get(j).getFromArrivalTime();
            int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(),2)));

            Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

            if (    (flightDurationUpTo2>0)
                    &&(waitingTimeHours1 >= 1 || waitingTimeMinutes1 > 45)
                    && (waitingTimeHours2 >= 1 || waitingTimeMinutes2 > 45)
                    && (connectingTimeStart <= waitingTimeHours1)
                    && (connectingTimeEnd > waitingTimeHours1)
                    && (connectingTimeStart <= waitingTimeHours2)
                    && (connectingTimeEnd > waitingTimeHours2)
                    && (finalPrice < maxPrice)
                    && (diffHours < flightDuration)
                    && (departureTimeStart <= departureTime)
                    && (departureTimeEnd > departureTime)
                    && (arrivalTimeStart <= arrivalTime)
                    && (arrivalTimeEnd > arrivalTime)) {

                FlightOneWay flight = new FlightOneWay();
                flight.setDepartureFrom(flights.get(i).getFromTown());
                flight.setDepartureTo(flights.get(j).getToTown());

                flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
                flight.setFromDepartureDate(flights.get(i).getFromDate());
                flight.setFromCompany(flights.get(i).getFromCompany());
                flight.setStation("2 Stops");

                flight.setDepartureToStation1(flights.get(i).getToTown());
                flight.setDeparturefromStation1(flights.get(j).getFromTown());
                flight.setStation1ArrivalTime(flights.get(i).getFromArrivalTime());
                flight.setStation1ArrivalDate(flights.get(i).getToDate());
                flight.setStation1DepTime(flights.get(j).getFromDepartureTime());
                flight.setStation1DepDate(flights.get(j).getFromDate());


                String differHours = String.valueOf(diffHours);
                String differMinutes = String.valueOf(diffminutes);
                flight.setDuration(differHours + "h " + differMinutes + "m");
                flight.setDurationToStation1(flights.get(i).getDuration());
                flight.setWaitingTimeStation1(waitingTimeHours1 + "h " + waitingTimeMinutes1 + "m");

                List<AirLogo> airlogos = new ArrayList<>();
                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(i).getFromCompany());
                flight.setAirlineToStation1(flights.get(i).getFromCompany());
                if (airlogos.size()==0){
                    flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
                }else {
                    flight.setAirlineToStation1imgSrc(airlogos.get(0).getAirLogoSrc());
                }

                flight.setAirlineFromStation1(flights.get(j).getFromCompany());
                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(j).getFromCompany());
                if (airlogos.size()==0){
                    flight.setAirlineFromStation1imgSrc(flights.get(j).getAirlineImgSrc());
                }else {
                    flight.setAirlineFromStation1imgSrc(airlogos.get(0).getAirLogoSrc());
                }


                flight.setDepartureToStation2(flights.get(j).getStationTown());
                flight.setDeparturefromStation2(flights.get(j).getStationTown());
                flight.setStation2ArrivalTime(flights.get(j).getStationArrivalTime());
                flight.setStation2ArrivalDate(flights.get(j).getStationArrivalDate());
                flight.setStation2DepTime(flights.get(j).getStationDepTime());
                flight.setStation2DepDate(flights.get(j).getStationDepDate());
                flight.setAirlineFromStation2(flights.get(j).getFromCompanyAfterStation());
                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(j).getFromCompanyAfterStation());
                if (airlogos.size()==0){
                    flight.setAirlineFromStation2imgSrc(flights.get(j).getAirlineImgSrc());
                }else {
                    flight.setAirlineFromStation2imgSrc(airlogos.get(0).getAirLogoSrc());
                }



                long durationToStation2Format = dt4.getTime() - dt3.getTime();
                long diff2minutes = durationToStation2Format / (60 * 1000) % 60;
                long diff2Hours = durationToStation2Format / (60 * 60 * 1000);
                flight.setDurationToStation2(diff2Hours + "h " + diff2minutes + "m");
                flight.setWaitingTimeStation2(waitingTimeHours2 + "h " + waitingTimeMinutes2 + "m");

                long durationFromStation2Format = dt6.getTime() - dt5.getTime();
                long diff3minutes = durationFromStation2Format / (60 * 1000) % 60;
                long diff3Hours = durationFromStation2Format / (60 * 60 * 1000);
                flight.setDurationFromStation2(diff3Hours + "h " + diff3minutes + "m");
                flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToLastDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToDepartureDate(flights.get(j).getToDate());
                finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                flight.setPrice(finalPrice);

                flightsList.add(flight);

            }


        }
    }

    private void upTo2directFlightSecond(double maxPrice,
                                         List<Flight> flights,
                                         List<FlightOneWay> flightsList,
                                         int i,
                                         int j,
                                         int flightDuration,
                                         int connectingTimeStart,
                                         int connectingTimeEnd,
                                         int departureTimeStart,
                                         int departureTimeEnd,
                                         int arrivalTimeStart,
                                         int arrivalTimeEnd) {

        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(j).getFlightSearchFromIata()))
                && (flights.get(i).getFlightSearchToIata().equals(flights.get(j).getFlightSearchToIata()))
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(i).getFromIata()))
                && (flights.get(j).getFlightSearchToIata().equals(flights.get(j).getToIata()))) {

//            Locale locale = new Locale("el");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

            Date t1, d1, dt1 = null;
            dt1 = getDateFromDepartureTime(flights, i, timeFormat, dateFormat, dt1);
            Date t2, d2, dt2 = null;
            dt2 = getDateStationArrivalTime(flights, i, timeFormat, dateFormat, dt2);
            Date t3, d3, dt3 = null;
            dt3 = getDateStationDepTime(flights, i, timeFormat, dateFormat, dt3);
            Date t4, d4, dt4 = null;
            dt4 = getDateFromArrivalTime(flights, i, timeFormat, dateFormat, dt4);
            Date t5, d5, dt5 = null;
            dt5 = getDateFromDepartureTime(flights, j, timeFormat, dateFormat, dt5);
            Date t6, d6, dt6 = null;
            dt6 = getDateFromArrivalTime(flights, j, timeFormat, dateFormat, dt6);
            long waitingTimeStation1 = dt3.getTime() - dt2.getTime();
            long waitingTimeMinutes1 = waitingTimeStation1 / (60 * 1000) % 60;
            long waitingTimeHours1 = waitingTimeStation1 / (60 * 60 * 1000);
            // Find the flights with 1hour differnce
            long waitingTimeStation2 = dt5.getTime() - dt4.getTime();
            long waitingTimeMinutes2 = waitingTimeStation2 / (60 * 1000) % 60;
            long waitingTimeHours2 = waitingTimeStation2 / (60 * 60 * 1000);

            long flightDurationUpTo2 = dt6.getTime() - dt1.getTime();
            long diffminutes = flightDurationUpTo2 / (60 * 1000) % 60;
            long diffHours = flightDurationUpTo2 / (60 * 60 * 1000);

            String departureTimeString=flights.get(i).getFromDepartureTime();
            int departureTime=Integer.parseInt(departureTimeString.substring(0,Math.min(departureTimeString.length(),2)));

            String arrivalTimeString=flights.get(i).getFromArrivalTime();
            int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

            Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

            if ((flightDurationUpTo2>0)
                    &&(waitingTimeHours1 >= 1 || waitingTimeMinutes1 > 45)
                    && (waitingTimeHours2 >= 1 || waitingTimeMinutes2 > 45)
                    && (connectingTimeStart <= waitingTimeHours1)
                    && (connectingTimeEnd > waitingTimeHours1)
                    && (connectingTimeStart <= waitingTimeHours2)
                    && (connectingTimeEnd > waitingTimeHours2)
                    && (finalPrice < maxPrice)
                    && (diffHours < flightDuration)
                    && (departureTimeStart <= departureTime)
                    && (departureTimeEnd > departureTime)
                    && (arrivalTimeStart <= arrivalTime)
                    && (arrivalTimeEnd > arrivalTime)) {

                FlightOneWay flight = new FlightOneWay();
                flight.setDepartureFrom(flights.get(i).getFromTown());
                flight.setDepartureTo(flights.get(j).getToTown());

                flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
                flight.setFromDepartureDate(flights.get(i).getFromDate());
                flight.setFromCompany(flights.get(i).getFromCompany());
                flight.setStation("2 Stops");

                flight.setDepartureToStation1(flights.get(i).getStationTown());
                flight.setDeparturefromStation1(flights.get(i).getStationTown());
                flight.setStation1ArrivalTime(flights.get(i).getStationArrivalTime());
                flight.setStation1ArrivalDate(flights.get(i).getStationArrivalDate());
                flight.setStation1DepTime(flights.get(i).getStationDepTime());
                flight.setStation1DepDate(flights.get(i).getStationDepDate());


                String differHours = String.valueOf(diffHours);
                String differMinutes = String.valueOf(diffminutes);
                flight.setDuration(differHours + "h " + differMinutes + "m");
                long durationToStation1Format = dt2.getTime() - dt1.getTime();
                long diff2minutes = durationToStation1Format / (60 * 1000) % 60;
                long diff2Hours = durationToStation1Format / (60 * 60 * 1000);
                flight.setDurationToStation1(diff2Hours + "h " + diff2minutes + "m");
                flight.setWaitingTimeStation1(waitingTimeHours1 + "h " + waitingTimeMinutes1 + "m");

                List<AirLogo> airlogos = new ArrayList<>();
                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(i).getFromCompany());
                flight.setAirlineToStation1(flights.get(i).getFromCompany());
                if (airlogos.size()==0){
                    flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
                }else {
                    flight.setAirlineToStation1imgSrc(airlogos.get(0).getAirLogoSrc());
                }

                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(i).getFromCompanyAfterStation());

                flight.setAirlineFromStation1(flights.get(i).getFromCompanyAfterStation());
                if (airlogos.size()==0){
                    flight.setAirlineFromStation1imgSrc(flights.get(i).getAirlineImgSrc());
                }else {
                    flight.setAirlineFromStation1imgSrc(airlogos.get(0).getAirLogoSrc());
                }



                flight.setDepartureToStation2(flights.get(i).getToTown());
                flight.setDeparturefromStation2(flights.get(j).getFromTown());
                flight.setStation2ArrivalTime(flights.get(i).getFromArrivalTime());
                flight.setStation2ArrivalDate(flights.get(i).getToDate());
                flight.setStation2DepTime(flights.get(j).getFromDepartureTime());
                flight.setStation2DepDate(flights.get(j).getFromDate());
                flight.setAirlineFromStation2(flights.get(j).getFromCompany());
                airlogos=this.airLogoRepository.findDistinctByCompanyName(flights.get(j).getFromCompany());
                if (airlogos.size()==0){
                    flight.setAirlineFromStation2imgSrc(flights.get(j).getAirlineImgSrc());
                }else {
                    flight.setAirlineFromStation2imgSrc(airlogos.get(0).getAirLogoSrc());
                }


                long durationToStation2Format = dt4.getTime() - dt3.getTime();
                long diff3minutes = durationToStation2Format / (60 * 1000) % 60;
                long diff3Hours = durationToStation2Format / (60 * 60 * 1000);
                flight.setDurationToStation2(diff3Hours + "h " + diff3minutes + "m");
                flight.setWaitingTimeStation2(waitingTimeHours2 + "h " + waitingTimeMinutes2 + "m");


                long durationFromStation2Format = dt6.getTime() - dt5.getTime();
                long diff4minutes = durationFromStation2Format / (60 * 1000) % 60;
                long diff4Hours = durationFromStation2Format / (60 * 60 * 1000);
                flight.setDurationFromStation2(diff4Hours + "h " + diff4minutes + "m");
                flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToLastDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToDepartureDate(flights.get(j).getToDate());
                finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                flight.setPrice(finalPrice);

                flightsList.add(flight);

            }
        }
    }

    private List<Flight> getFlights(String fromIata, String toIata, String checkin, String checkout, String adultNum,
                                    String childNum, String typeOfFlight, String airportSize, String tripDistance,
                                    double maxPrice, String typeOfFlightToSearch, List<Flight> flights) {
        if(typeOfFlight=="oneWay") {
            flights = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
        }else{
            List<Flight> flightsCheckIn = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            List<Flight> flightsCheckOut = this.flightRepository.
                    findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            flights.addAll(flightsCheckIn);
            flights.addAll(flightsCheckOut);
        }
        return flights;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private Date combineDateTime(Date date, Date time) {
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

        Date result = calendarA.getTime();
        return result;
    }

    private void sortedListByPrice(String typeOfFlight, List<FlightOneWay> flightsListUpToOneStation) {
        Collections.sort(flightsListUpToOneStation, new Comparator<FlightOneWay>() {

            public int compare(FlightOneWay s1, FlightOneWay s2) {
                if(typeOfFlight.equals("oneWay")) {
                    return (int) ((s1.getPrice()) - (s2.getPrice()));
                }else{
                    return (int) ((s1.getFinalPriceRoundTrip()) - (s2.getFinalPriceRoundTrip()));
                }
            }

            Double extractDouble(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Double.parseDouble(num.replace(".", ","));
            }
        });
    }

    private Date getDateStationDepTime(List<Flight> flights, int j, SimpleDateFormat timeFormat, SimpleDateFormat dateFormat, Date dt5) {
        Date t5;
        Date d5;
        try {
            t5 = timeFormat.parse(flights.get(j).getStationDepTime());
            d5 = dateFormat.parse(flights.get(j).getStationDepDate());
            dt5 = combineDateTime(d5, t5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt5;
    }

    private Date getDateStationArrivalTime(List<Flight> flights, int j, SimpleDateFormat timeFormat, SimpleDateFormat dateFormat, Date dt4) {
        Date t4;
        Date d4;
        try {
            t4 = timeFormat.parse(flights.get(j).getStationArrivalTime());
            d4 = dateFormat.parse(flights.get(j).getStationArrivalDate());
            dt4 = combineDateTime(d4, t4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt4;
    }

    private Date getDateFromArrivalTime(List<Flight> flights, int i, SimpleDateFormat timeFormat, SimpleDateFormat dateFormat, Date dt2) {
        Date t2;
        Date d2;
        try {
            t2 = timeFormat.parse(flights.get(i).getFromArrivalTime());
            d2 = dateFormat.parse(flights.get(i).getToDate());
            dt2 = combineDateTime(d2, t2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt2;
    }

    private Date getDateFromDepartureTime(List<Flight> flights, int i, SimpleDateFormat timeFormat, SimpleDateFormat dateFormat, Date dt1) {
        Date t1;
        Date d1;
        try {
            t1 = timeFormat.parse(flights.get(i).getFromDepartureTime());
            d1 = dateFormat.parse(flights.get(i).getFromDate());
            dt1 = combineDateTime(d1, t1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt1;
    }

    public boolean flightSearched(String fromIata,
                                  String toIata ,
                                  String checkin,
                                  String checkout,
                                  String adultNum,
                                  String childNum,
                                  String typeOfFlight,
                                  String airportSize,
                                  String tripDistance){

        List<FlightInfos> flightsInfos = this.flightInfoRepository.findByFromIataAndToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( fromIata,
                 toIata ,
                 checkin,
                 checkout,
                 adultNum,
                 childNum,
                 typeOfFlight,
                 airportSize,
                 tripDistance);

        for (int i = 0; i < flightsInfos.size(); i++) {
            if(flightsInfos.get(i).getFlightSearched().equals("no")){
                return false;
            }else{
                return true;
            }

        }

        return false;

    }
}
