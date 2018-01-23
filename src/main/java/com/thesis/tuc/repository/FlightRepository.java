package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlightRepository extends MongoRepository<Flight,String>  {
}
