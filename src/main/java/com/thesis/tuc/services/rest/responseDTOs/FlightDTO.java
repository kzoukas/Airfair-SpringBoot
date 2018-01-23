package com.thesis.tuc.services.rest.responseDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class FlightDTO implements Serializable{


    private static final long serialVersionUID = 8155428662160926725L;
    @XmlElement(name = "flightFrom")
    private String flightFrom;

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }
}
