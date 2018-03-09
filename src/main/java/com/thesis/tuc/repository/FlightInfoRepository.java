package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightInfoRepository extends MongoRepository<FlightInfos,String> {

    List<FlightInfos> findByFromIataAndToIataAndTypeOfFlight(String fromIata, String toIata , String typeOfFlight);

}
