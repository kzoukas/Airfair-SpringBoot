package com.thesis.tuc.business;

import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

@Component
public class filterService {

    private FlightRepository flightRepository;

    public filterService(FlightRepository flightRepository, FlightInfoRepository flightInfoRepository) {

        this.flightRepository = flightRepository;


    }
    public List<Flight> getFilteredFlights(String fromIata,String toIata) {
        List<Flight> flights = this.flightRepository.findByFromIataAndToIata( fromIata,toIata);

        return flights;

    }

}
