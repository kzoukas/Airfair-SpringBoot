package com.thesis.tuc.business;

import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightInfoService {

    private FlightInfoRepository flightInfoRepository;

    public FlightInfoService(FlightInfoRepository flightInfoRepository) {
        this.flightInfoRepository = flightInfoRepository;
    }

    public boolean flightSearched(String fromIata,
                                  String toIata ,
                                  String checkin,
                                  String checkout,
                                  String adultNum,
                                  String childNum,
                                  String typeOfFlight,
                                  String airportSize,
                                  String tripDistance){

        List<FlightInfos> flightsInfos = this.flightInfoRepository.findByFromIataAndToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( fromIata,
                toIata ,
                checkin,
                checkout,
                adultNum,
                childNum,
                typeOfFlight,
                airportSize,
                tripDistance);

        for (int i = 0; i < flightsInfos.size(); i++) {
            if(flightsInfos.get(i).getFlightSearched().equals("no")){
                return false;
            }else{
                return true;
            }

        }

        return false;

    }

}
