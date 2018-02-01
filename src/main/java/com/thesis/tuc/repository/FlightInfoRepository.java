package com.thesis.tuc.repository;


import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FlightInfoRepository extends MongoRepository<FlightInfo,String> {
}
