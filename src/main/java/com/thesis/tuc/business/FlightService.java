package com.thesis.tuc.business;

import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;
import java.util.Date;
import java.text.SimpleDateFormat;

@Component
public class FlightService {


    private FlightRepository flightRepository;
    private FlightInfoRepository flightInfoRepository;

    public FlightService(FlightRepository flightRepository, FlightInfoRepository flightInfoRepository) {

        this.flightRepository = flightRepository;

        this.flightInfoRepository = flightInfoRepository;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = this.flightRepository.findAll();
        List<Flight> flightsTIA = new ArrayList<>();


//        for (int j = i+1; j < list.size(); j++) {
//            // compare list.get(i) and list.get(j)
//        }
        for (int i = 0; i < flights.size(); i++) {
            for (int j = i+1; j < flights.size(); j++) {

//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                String time1= flights.get(i).getFromArrivalTime();
                Date d1 = null;
                try {
                    d1 = format.parse(time1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date d2 = null;
                try {
                    d2 = format.parse(flights.get(j).getFromDepartureTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long elapsed = d2.getTime() - d1.getTime();

                if (flights.get(i).getToIata().equals(flights.get(j).getFromIata()) && (elapsed>1)) {

                    Flight flight = flights.get(i);
                    Flight flight2 = flights.get(j);
                    flightsTIA.add(flight);
                    flightsTIA.add(flight2);
                }
            }
        }

//        for (Flight flight : flights) {
//            if (flight.getFromDepartureTime().equals("08:40")) {
//
//
//                flightsTIA.add(flight);
//            }
//        }
        List<Flight> distinctElements = flightsTIA.stream().distinct().collect(Collectors.toList());
        Collections.sort(distinctElements, new Comparator<Flight>() {

            public int compare(Flight s1, Flight s2) {
                return extractInt(s1.getPrice()) - extractInt(s2.getPrice());
                //return String.valueOf(s2.getPrice().substring(1)).compareTo(s1.getPrice().substring(1));
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
        return distinctElements;
    }

    public List<FlightInfo> getAllFlightInfo() {
        List<FlightInfo> flights2 = this.flightInfoRepository.findAll();

        return flights2;
    }
}
