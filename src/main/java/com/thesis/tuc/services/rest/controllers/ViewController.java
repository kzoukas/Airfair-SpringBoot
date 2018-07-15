package com.thesis.tuc.services.rest.controllers;

import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thesis.tuc.business.FlightService;
import com.thesis.tuc.business.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path = "/")
public class ViewController {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    private FlightService flightService;


    @GetMapping(path="/allflights" +
            "/{fromIata}" +
            "/{toIata}" +
            "/{checkin}" +
            "/{checkout}" +
            "/{adultNum}" +
            "/{childNum}" +
            "/{typeOfFlight}" +
            "/{airportSize}" +
            "/{tripDistance}" +
            "/{stops}" +
            "/{maxPrice}" +
            "/{flightDuration}" +
            "/{connectingTimeStart}" +
            "/{connectingTimeEnd}" +
            "/{departureTimeStart}" +
            "/{departureTimeEnd}" +
            "/{arrivalTimeStart}" +
            "/{arrivalTimeEnd}")
    public List<FlightOneWay> getAll(@PathVariable String fromIata,
                                     @PathVariable String toIata,
                                     @PathVariable String checkin,
                                     @PathVariable String checkout,
                                     @PathVariable String adultNum,
                                     @PathVariable String childNum,
                                     @PathVariable String typeOfFlight,
                                     @PathVariable String airportSize,
                                     @PathVariable String tripDistance,
                                     @PathVariable int stops,
                                     @PathVariable double maxPrice,
                                     @PathVariable int flightDuration,
                                     @PathVariable int connectingTimeStart,
                                     @PathVariable int connectingTimeEnd,
                                     @PathVariable int departureTimeStart,
                                     @PathVariable int departureTimeEnd,
                                     @PathVariable int arrivalTimeStart,
                                     @PathVariable int arrivalTimeEnd
    ){
        if(stops==1) {
            return flightService.getFlightsOneStation(fromIata,
                    toIata,
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
                    arrivalTimeEnd
            );
        }else if(stops==2){
            return flightService.getFlightsTwoStations(fromIata,
                    toIata,
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
        }
        else {
            return flightService.getDirectFlight(fromIata,
                    toIata,
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
        }
    }
    @GetMapping(path="/allInfos/{fromIata}/{toIata}/{checkin}/{checkout}/{adultNum}/{childNum}/{typeOfFlight}/{airportSize}/{tripDistance}")
    public boolean getAllInfos(@PathVariable String fromIata,
                               @PathVariable String toIata,
                               @PathVariable String checkin,
                               @PathVariable String checkout,
                               @PathVariable String adultNum,
                               @PathVariable String childNum,
                               @PathVariable String typeOfFlight,
                               @PathVariable String airportSize,
                               @PathVariable String tripDistance){

            return flightService.flightSearched( fromIata,
                    toIata ,
                    checkin,
                    checkout,
                    adultNum,
                    childNum,
                    typeOfFlight,
                    airportSize,
                    tripDistance);

    }


}
