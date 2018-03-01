package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightRepository extends MongoRepository<Flight,String>  {

    List<Flight> findByFromIataAndToIata(String fromIata, String toIata);
    List<Flight> findByPriceLessThan(double maxPrice);
    List<Flight> findByStationAndPriceLessThan(String typeOfFlight,Double maxPrice);
    List<Flight> findByPriceLessThan(Double maxPrice);

}
