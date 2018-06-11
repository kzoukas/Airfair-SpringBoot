package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightInfoRepository extends MongoRepository<FlightInfos,String> {

    List<FlightInfos> findByFromIataAndToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(String fromIata,
                                                             String toIata ,
                                                             String checkin,
                                                             String checkout,
                                                             String adultNum,
                                                             String childNum,
                                                             String typeOfFlight,
                                                             String airportSize,
                                                             String tripDistance);

}
