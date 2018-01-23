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


    @GetMapping("/allFlights")
    public List<Flight> getAll(){
        List<Flight> flights=this.flightRepository.findAll();

        return flights;
    }

    @RequestMapping(value = "/flight", method = RequestMethod.GET)
    public List<FlightDTO> getFlights(){
        FlightDTO flightDTO1 = new FlightDTO();
        FlightDTO flightDTO2 = new FlightDTO();
        flightDTO1.setFlightFrom("Athens");
        flightDTO2.setFlightFrom("Thessalonikh");
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flightDTOS.add(flightDTO1);
        flightDTOS.add(flightDTO2);
        return flightDTOS;
    }

    @RequestMapping(value = "/customFlight", method = RequestMethod.GET)
    public List<FlightDTO> getFlights(@RequestParam("flightFrom") String flightFrom, @RequestParam("flightTo") String flightTo){
        FlightDTO flightDTO1 = new FlightDTO();
        FlightDTO flightDTO2 = new FlightDTO();
        flightDTO1.setFlightFrom(flightFrom);
        flightDTO2.setFlightFrom(flightTo);
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flightDTOS.add(flightDTO1);
        flightDTOS.add(flightDTO2);
        return flightDTOS;
    }


}
