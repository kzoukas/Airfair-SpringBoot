package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightRepository extends MongoRepository<Flight,String>  {


    List<Flight> findDistinctByPriceLessThan(double maxPrice);
    List<Flight> findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(String typeOfFlightToSearch,
                                                                                                                                              Double maxPrice,
                                                                                                                                              String fromIata,
                                                                                                                                              String toIata,
                                                                                                                                              String checkin,
                                                                                                                                              String checkout,
                                                                                                                                              String adultNum,
                                                                                                                                              String childNum,
                                                                                                                                              String typeOfFlight,
                                                                                                                                              String airportSize,
                                                                                                                                              String tripDistance);
    List<Flight> findDistinctByPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(Double maxPrice,
                                                                                                                                                                                String fromIata,
                                                                                                                                                                                String toIata,
                                                                                                                                                                                String checkin,
                                                                                                                                                                                String checkout,
                                                                                                                                                                                String adultNum,
                                                                                                                                                                                String childNum,
                                                                                                                                                                                String typeOfFlight,
                                                                                                                                                                                String airportSize,
                                                                                                                                                                                String tripDistance);

    List<Flight> findFlightsDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistanceAndFromIataAndToIata(String typeOfFlightToSearch,
                                                                                                                                                                                                 Double maxPrice,
                                                                                                                                                                                                 String fromIata,
                                                                                                                                                                                                 String toIata,
                                                                                                                                                                                                 String checkin,
                                                                                                                                                                                                 String checkout,
                                                                                                                                                                                                 String adultNum,
                                                                                                                                                                                                 String childNum,
                                                                                                                                                                                                 String typeOfFlight,
                                                                                                                                                                                                 String airportSize,
                                                                                                                                                                                                 String tripDistance,
                                                                                                                                                                                                 String fromIata1,
                                                                                                                                                                                                 String toIata1);
}
