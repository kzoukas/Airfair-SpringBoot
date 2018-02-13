package com.thesis.tuc.services.rest.controllers;

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

    @Autowired
    private FlightService flightService;
    @Autowired
    private filterService filterService;

    @GetMapping(path="/allflights")
    public List<Flight> getAll(){

        return flightService.getAllFlights();
    }

    @GetMapping(path="/flightInfo")
    public List<FlightInfo> getAlls(){

        return flightService.getAllFlightInfo();
    }
    @GetMapping(path="/allflights/filters")
    public List<Flight> getAllFilter(){

         return filterService.getFilteredFlights();
    }

}
