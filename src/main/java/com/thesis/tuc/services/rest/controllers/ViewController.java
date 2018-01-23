package com.thesis.tuc.services.rest.controllers;

import com.thesis.tuc.services.rest.responseDTOs.FlightDTO;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ViewController {

    private FlightRepository flightRepository;
    public ViewController(FlightRepository flightRepository) {

        this.flightRepository = flightRepository;
    }
@GetMapping(path="/allflights")
    public List<Flight> getAll(){
        List<Flight> flights=this.flightRepository.findAll();

        return flights;
    }
}
