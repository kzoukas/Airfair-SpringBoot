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




}
