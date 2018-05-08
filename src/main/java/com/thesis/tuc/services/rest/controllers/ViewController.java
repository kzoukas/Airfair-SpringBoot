package com.thesis.tuc.services.rest.controllers;

import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thesis.tuc.business.FlightService;
import com.thesis.tuc.business.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ViewController {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    private FlightService flightService;


    @GetMapping(path="/allflights/{fromIata}/{toIata}/{stops}/{maxPrice}/{flightDuration}/{connectingTime}/{departureTimeStart}/{departureTimeEnd}/{arrivalTimeStart}/{arrivalTimeEnd}")
    public List<FlightOneWay> getAll(@PathVariable String fromIata, @PathVariable String toIata, @PathVariable int stops, @PathVariable double maxPrice, @PathVariable int flightDuration, @PathVariable int connectingTime, @PathVariable int departureTimeStart, @PathVariable int departureTimeEnd, @PathVariable int arrivalTimeStart, @PathVariable int arrivalTimeEnd){
        if(stops==1) {
            return flightService.getFlightsOneStation(fromIata, toIata, maxPrice, flightDuration,connectingTime,departureTimeStart,departureTimeEnd,arrivalTimeStart,arrivalTimeEnd);
        }else if(stops==2){
            return flightService.getFlightsTwoStations(fromIata, toIata, maxPrice, flightDuration,connectingTime,departureTimeStart,departureTimeEnd,arrivalTimeStart,arrivalTimeEnd);
        }
        else {
            return flightService.getDirectFlight(fromIata, toIata, maxPrice, flightDuration,departureTimeStart,departureTimeEnd,arrivalTimeStart,arrivalTimeEnd);
        }
    }
    @GetMapping(path="/allInfos/{fromIata}/{toIata}/{typeOfFlight}")
    public boolean getAllInfos(@PathVariable String fromIata, @PathVariable String toIata, @PathVariable String typeOfFlight){

            return flightService.flightSearched(fromIata, toIata, typeOfFlight);

    }
}
