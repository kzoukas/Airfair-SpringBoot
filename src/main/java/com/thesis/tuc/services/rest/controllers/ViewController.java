package com.thesis.tuc.services.rest.controllers;
import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thesis.tuc.business.FlightService;
import com.thesis.tuc.business.filterService;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.Filters;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;
import com.thesis.tuc.repository.FlightInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ViewController {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    private FlightService flightService;
    @Autowired
    private filterService filterService;

    @GetMapping(path="/allflights/{fromIata}/{toIata}/{stops}/{maxPrice}/{flightDuration}")
    public List<FlightOneWay> getAll(@PathVariable String fromIata, @PathVariable String toIata, @PathVariable int stops, @PathVariable double maxPrice, @PathVariable int flightDuration){
        if(stops==1) {
            return flightService.getFlightsOneStation(fromIata, toIata, maxPrice, flightDuration);
        }else if(stops==2){
            return flightService.getFlightsTwoStations(fromIata, toIata, maxPrice, flightDuration);
        }
        else {
            return flightService.getDirectFlight(fromIata, toIata, maxPrice, flightDuration);
        }
    }


    @GetMapping(path="/flightInfo")
    public List<FlightInfo> getAlls(){

        return flightService.getAllFlightInfo();
    }
//    @GetMapping(path="/allflights/filters")
//    public List<Flight> getAllFilter(){
//
//         return filterService.getFilteredFlights();
//    }
    @GetMapping(path="/allflights/{fromIata}/{toIata}")
    public List<Flight> getAllFilteredFlights(@PathVariable String fromIata, @PathVariable String toIata){
        logger.debug("Inside",fromIata,toIata);
        return filterService.getFilteredFlights(fromIata , toIata);
    }

}
