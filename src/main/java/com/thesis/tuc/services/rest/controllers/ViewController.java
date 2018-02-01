package com.thesis.tuc.services.rest.controllers;

import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;
import com.thesis.tuc.repository.FlightInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ViewController {

    private FlightRepository flightRepository;
    private FlightInfoRepository flightInfoRepository;
    public ViewController(FlightRepository flightRepository,FlightInfoRepository flightInfoRepository) {

        this.flightRepository = flightRepository;

        this.flightInfoRepository = flightInfoRepository;
    }
    @GetMapping(path="/allflights")
    public List<Flight> getAll(){
        List<Flight> flights=this.flightRepository.findAll();

        return flights;
    }

    @GetMapping(path="/flightInfo")
    public List<FlightInfo> getAlls(){
        List<FlightInfo> flights2=this.flightInfoRepository.findAll();

        return flights2;
    }
}
