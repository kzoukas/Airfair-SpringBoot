package com.thesis.tuc.business;

import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

import com.thesis.tuc.services.rest.responseDTOs.FlightInfos;
import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.springframework.stereotype.Component;
import java.util.stream.*;
import java.text.SimpleDateFormat;

@Component
public class FlightService {

    private FlightRepository flightRepository;
    private FlightInfoRepository flightInfoRepository;

    public FlightService(FlightRepository flightRepository,FlightInfoRepository flightInfoRepository) {
        this.flightRepository = flightRepository;
        this.flightInfoRepository = flightInfoRepository;
    }

    public List<FlightOneWay> getDirectFlight(String fromIata,
                                              String toIata,
                                              String checkin,
                                              String checkout,
                                              String adultNum,
                                              String childNum,
                                              String typeOfFlight,
                                              String airportSize,
                                              String tripDistance,
                                              double maxPrice,
                                              int flightDuration,
                                              int departureTimeStart,
                                              int departureTimeEnd,
                                              int arrivalTimeStart,
                                              int arrivalTimeEnd) {

        String typeOfFlightToSearch = "Απευθείας";
        List<Flight> flights = new ArrayList<>();;
        flights = getFlights(fromIata, toIata, checkin, checkout, adultNum, childNum, typeOfFlight, airportSize, tripDistance, maxPrice, typeOfFlightToSearch, flights);
        List<FlightOneWay> flightsList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i,
                        flightDuration, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
        }
        Collections.sort(flightsList, new Comparator<FlightOneWay>() {

            public int compare(FlightOneWay s1, FlightOneWay s2) {
                return (int) ((s1.getPrice()) - (s2.getPrice()));
                //return String.valueOf(s2.getPrice().substring(1)).compareTo(s1.getPrice().substring(1));
            }
            Double extractDouble(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Double.parseDouble(num.replace(".", ","));
            }
        });
        List<FlightOneWay> distinctElements = flightsList.stream().distinct()
                .collect(Collectors.toList());
        return distinctElements;
    }



    public List<FlightOneWay> getFlightsOneStation(String fromIata,
                                                   String toIata,
                                                   String checkin,
                                                   String checkout,
                                                   String adultNum,
                                                   String childNum,
                                                   String typeOfFlight,
                                                   String airportSize,
                                                   String tripDistance,
                                                   double maxPrice,
                                                   int flightDuration,
                                                   int connectingTimeStart,
                                                   int connectingTimeEnd,
                                                   int departureTimeStart,
                                                   int departureTimeEnd,
                                                   int arrivalTimeStart,
                                                   int arrivalTimeEnd) {

        String typeOfFlightToSearch = "Απευθείας";
        List<Flight> flights = new ArrayList<>();;
        flights = getFlights(fromIata, toIata, checkin, checkout, adultNum, childNum, typeOfFlight,
                airportSize, tripDistance, maxPrice, typeOfFlightToSearch, flights);
        List<FlightOneWay> flightsList = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i,
                        flightDuration, departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
                //Add to list all pair of flights with station and waiting time
                // at least 1 hour so the passenger have time to change plane
                for (int j = 0; j < flights.size(); j++) {

                    flightsUpToOneStation(maxPrice, flights, flightsList, i, j, flightDuration, connectingTimeStart,
                            connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);
                }
        }
        Collections.sort(flightsList, new Comparator<FlightOneWay>() {

            public int compare(FlightOneWay s1, FlightOneWay s2) {
                return (int) ((s1.getPrice()) - (s2.getPrice()));
                //return String.valueOf(s2.getPrice().substring(1)).compareTo(s1.getPrice().substring(1));
            }

            Double extractDouble(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Double.parseDouble(num.replace(".", ","));
            }
        });
        List<FlightOneWay> distinctElements = flightsList.stream().distinct()
                .collect(Collectors.toList());
        return distinctElements;
    }

    public List<FlightOneWay> getFlightsTwoStations(String fromIata,
                                                    String toIata,
                                                    String checkin,
                                                    String checkout,
                                                    String adultNum,
                                                    String childNum,
                                                    String typeOfFlight,
                                                    String airportSize,
                                                    String tripDistance,
                                                    double maxPrice,
                                                    int flightDuration,
                                                    int connectingTimeStart,
                                                    int connectingTimeEnd,
                                                    int departureTimeStart,
                                                    int departureTimeEnd,
                                                    int arrivalTimeStart,
                                                    int arrivalTimeEnd) {

        List<Flight> flights = this.flightRepository.
                findDistinctByPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance( maxPrice,fromIata,
                        toIata,
                        checkin,
                        checkout,
                        adultNum,
                        childNum,
                        typeOfFlight,
                        airportSize,
                        tripDistance);
        List<FlightOneWay> flightsList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
                // Direct flight add to list
                searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i, flightDuration,
                        departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

                for (int j = 0; j < flights.size(); j++) {

                    if (flights.get(i).getStation().equals("Απευθείας") && (flights.get(j).getStation().equals("Απευθείας"))) {

                        flightsUpToOneStation(maxPrice, flights, flightsList, i, j, flightDuration, connectingTimeStart,
                                connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

                    } else if (flights.get(i).getStation().equals("Απευθείας") && (flights.get(j).getStation().equals("1 Στάση"))) {

                        upTo2directFlightFirst(maxPrice, flights, flightsList, i, j, flightDuration, connectingTimeStart,
                                connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

                    } else if (flights.get(i).getStation().equals("1 Στάση") && (flights.get(j).getStation().equals("Απευθείας"))) {

                        upTo2directFlightSecond(maxPrice, flights, flightsList, i, j, flightDuration, connectingTimeStart,
                                connectingTimeEnd,departureTimeStart, departureTimeEnd, arrivalTimeStart, arrivalTimeEnd);

                    }
                }
        }

        Collections.sort(flightsList, new Comparator<FlightOneWay>() {

            public int compare(FlightOneWay s1, FlightOneWay s2) {
                return (int) ((s1.getPrice()) - (s2.getPrice()));
                //return String.valueOf(s2.getPrice().substring(1)).compareTo(s1.getPrice().substring(1));
            }

            Double extractDouble(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Double.parseDouble(num.replace(".", ","));
            }
        });
        List<FlightOneWay> distinctFlightList = flightsList.stream().distinct()
                .collect(Collectors.toList());
        return distinctFlightList;
    }

    private void searchDirectFlights(String fromIata,
                                     String toIata,
                                     double maxPrice,
                                     List<Flight> flights,
                                     List<FlightOneWay> flightsList,
                                     int i,
                                     int flightDuration,
                                     int departureTimeStart,
                                     int departureTimeEnd,
                                     int arrivalTimeStart,
                                     int arrivalTimeEnd) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

        Date t1,d1,dt1 = null;
        try {
            t1 = timeFormat.parse(flights.get(i).getFromDepartureTime());
            d1 = dateFormat.parse(flights.get(i).getFromDate().substring(4));
            dt1 = combineDateTime(d1, t1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date t2,d2,dt2 = null;
        try {
            t2 = timeFormat.parse(flights.get(i).getFromArrivalTime());
            d2 = dateFormat.parse(flights.get(i).getToDate().substring(4));
            dt2 = combineDateTime(d2, t2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long flightDurationDirect = dt2.getTime() - dt1.getTime();
        long diffminutes = flightDurationDirect / (60 * 1000) % 60;
        long diffHours = flightDurationDirect / (60 * 60 * 1000);
        String departureTimeString=flights.get(i).getFromDepartureTime();
        int departureTime = Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(), 2)));

        String arrivalTimeString=flights.get(i).getFromArrivalTime();
        int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

        if (flights.get(i).getFromIata().equals(fromIata)
                && (flights.get(i).getToIata().equals(toIata))
                && (flights.get(i).getStation().equals("Απευθείας"))
                && (diffHours < flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (maxPrice > flights.get(i).getPrice())
                ||(flights.get(i).getFromIata().equals(toIata)
                && (flights.get(i).getToIata().equals(fromIata))
                && (flights.get(i).getStation().equals("Απευθείας"))
                && (diffHours < flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (maxPrice > flights.get(i).getPrice()) ))
        {

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flights.get(i).getFromTown());
            flight.setDepartureTo(flights.get(i).getToTown());
            flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
            flight.setToDepartureTime(flights.get(i).getFromArrivalTime());
            String differHours = String.valueOf(diffHours);
            String differMinutes = String.valueOf(diffminutes);
            flight.setDuration(differHours + "h " + differMinutes + "m");
            flight.setStation("Direct");
            flight.setFromDepartureDate(flights.get(i).getFromDate());
            flight.setFromCompany(flights.get(i).getFromCompany());
            flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
            flight.setToDepartureDate(flights.get(i).getToDate());
            flight.setPrice(flights.get(i).getPrice());

            flightsList.add(flight);
        }
    }

    private void flightsUpToOneStation(double maxPrice,
                                       List<Flight> flights,
                                       List<FlightOneWay> flightsList,
                                       int i,
                                       int j,
                                       int flightDuration,
                                       int connectingTimeStart,
                                       int connectingTimeEnd,
                                       int departureTimeStart,
                                       int departureTimeEnd,
                                       int arrivalTimeStart,
                                       int arrivalTimeEnd) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time1 = flights.get(i).getFromArrivalTime();
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
        Date d3 = null;
        try {
            d3 = format.parse(flights.get(i).getFromDepartureTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d4 = null;
        try {
            d4 = format.parse(flights.get(j).getFromArrivalTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Find the flights with 1hour differnce
        long waitingTime = d2.getTime() - d1.getTime();
        long waitingTimeMinutes = waitingTime / (60 * 1000) % 60;
        long waitingTimeHours = waitingTime / (60 * 60 * 1000);

        long flightDurationUpTo1 = d4.getTime() - d3.getTime();
        long diffminutes = flightDurationUpTo1 / (60 * 1000) % 60;
        long diffHours = flightDurationUpTo1 / (60 * 60 * 1000);

        String departureTimeString=flights.get(i).getFromDepartureTime();
        int departureTime = Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(), 2)));

        String arrivalTimeString=flights.get(j).getFromArrivalTime();
        int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

        Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

        //Add to list all pair of flights with station and waiting time at least 1 hour so the passenger have time to change plane
        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(j).getFlightSearchFromIata()))
                && (flights.get(i).getFlightSearchToIata().equals(flights.get(j).getFlightSearchToIata()))
                && (flights.get(i).getFlightSearchFromIata().equals(flights.get(i).getFromIata()))
                && (flights.get(j).getFlightSearchToIata().equals(flights.get(j).getToIata()))
                && (waitingTimeHours >= 1 || waitingTimeMinutes > 45)
                && (finalPrice < maxPrice)
                && (diffHours < flightDuration)
                && (departureTimeStart <= departureTime)
                && (departureTimeEnd > departureTime)
                && (arrivalTimeStart <= arrivalTime)
                && (arrivalTimeEnd > arrivalTime)
                && (connectingTimeStart <= waitingTimeHours)
                && (connectingTimeEnd > waitingTimeHours)){

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flights.get(i).getFromTown());
            flight.setDepartureTo(flights.get(j).getToTown());

            flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
            flight.setFromDepartureDate(flights.get(i).getFromDate());
            flight.setStation1ArrivalTime(flights.get(i).getFromArrivalTime());
            flight.setStation1ArrivalDate(flights.get(i).getToDate());
            flight.setStation1DepTime(flights.get(j).getFromDepartureTime());
            flight.setStation1DepDate(flights.get(j).getFromDate());
            flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
            flight.setToDepartureDate(flights.get(j).getToDate());

            flight.setDepartureToStation1(flights.get(i).getToTown());
            flight.setDeparturefromStation1(flights.get(j).getFromTown());

            String differHours = String.valueOf(diffHours);
            String differMinutes = String.valueOf(diffminutes);
            flight.setDuration(differHours + "h " + differMinutes + "m");
            flight.setDurationToStation1(flights.get(i).getDuration());
            flight.setDurationFromStation1(flights.get(j).getDuration());
            flight.setStation("1 Stop");

            String waitingHours = String.valueOf(waitingTimeHours);
            String waitingMinutes = String.valueOf(waitingTimeMinutes);
            flight.setWaitingTimeStation1(waitingHours + "h " + waitingMinutes + "m");

            flight.setFromCompany(flights.get(i).getFromCompany());
            flight.setAirlineToStation1(flights.get(i).getFromCompany());
            flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
            flight.setAirlineFromStation1(flights.get(j).getFromCompany());
            flight.setAirlineFromStation1imgSrc(flights.get(j).getAirlineImgSrc());

            finalPrice = Math.round(finalPrice * 100.0) / 100.0;
            flight.setPrice(finalPrice);

            flightsList.add(flight);

        }

    }

    private void upTo2directFlightFirst(double maxPrice,
                                        List<Flight> flights,
                                        List<FlightOneWay> flightsList,
                                        int i,
                                        int j,
                                        int flightDuration,
                                        int connectingTimeStart,
                                        int connectingTimeEnd,
                                        int departureTimeStart,
                                        int departureTimeEnd,
                                        int arrivalTimeStart,
                                        int arrivalTimeEnd) {

        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())) {

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

            Date t1,d1,dt1 = null;
            try {
                t1 = timeFormat.parse(flights.get(i).getFromDepartureTime());
                d1 = dateFormat.parse(flights.get(i).getFromDate().substring(4));
                dt1 = combineDateTime(d1, t1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t2,d2,dt2 = null;
            try {
                t2 = timeFormat.parse(flights.get(i).getFromArrivalTime());
                d2 = dateFormat.parse(flights.get(i).getToDate().substring(4));
                dt2 = combineDateTime(d2, t2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t3,d3,dt3 = null;
            try {
                t3 = timeFormat.parse(flights.get(j).getFromDepartureTime());
                d3 = dateFormat.parse(flights.get(j).getFromDate().substring(4));
                dt3 = combineDateTime(d3, t3);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t4,d4,dt4 = null;
            try {
                t4 = timeFormat.parse(flights.get(j).getStationArrivalTime());
                d4 = dateFormat.parse(flights.get(j).getStationArrivalDate().substring(4));
                dt4 = combineDateTime(d4, t4);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t5,d5,dt5 = null;
            try {
                t5 = timeFormat.parse(flights.get(j).getStationDepTime());
                d5 = dateFormat.parse(flights.get(j).getStationDepDate().substring(4));
                dt5 = combineDateTime(d5, t5);
            } catch (ParseException e) {
                e.printStackTrace();
            }
             Date t6,d6,dt6 = null;
            try {
                t6 = timeFormat.parse(flights.get(j).getFromArrivalTime());
                d6 = dateFormat.parse(flights.get(j).getToDate().substring(4));
                dt6 = combineDateTime(d6, t6);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Find the flights with 1hour differnce
            long waitingTimeStation1 = dt3.getTime() - dt2.getTime();
            long waitingTimeMinutes1 = waitingTimeStation1 / (60 * 1000) % 60;
            long waitingTimeHours1 = waitingTimeStation1 / (60 * 60 * 1000);

            long waitingTimeStation2 = dt5.getTime() - dt4.getTime();
            long waitingTimeMinutes2 = waitingTimeStation2 / (60 * 1000) % 60;
            long waitingTimeHours2 = waitingTimeStation2 / (60 * 60 * 1000);

            long flightDurationUpTo2 = dt6.getTime() - dt1.getTime();
            long diffminutes = flightDurationUpTo2 / (60 * 1000) % 60;
            long diffHours = flightDurationUpTo2 / (60 * 60 * 1000);

            String departureTimeString=flights.get(i).getFromDepartureTime();
            int departureTime = Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(), 2)));

            String arrivalTimeString=flights.get(j).getFromArrivalTime();
            int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

            Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

            if (    (flightDurationUpTo2>0)
                    &&(waitingTimeHours1 >= 1 || waitingTimeMinutes1 > 45)
                    && (waitingTimeHours2 >= 1 || waitingTimeMinutes2 > 45)
                    && (connectingTimeStart <= waitingTimeHours1)
                    && (connectingTimeEnd > waitingTimeHours1)
                    && (connectingTimeStart <= waitingTimeHours2)
                    && (connectingTimeEnd > waitingTimeHours2)
                    && (finalPrice < maxPrice)
                    && (diffHours < flightDuration)
                    && (departureTimeStart <= departureTime)
                    && (departureTimeEnd > departureTime)
                    && (arrivalTimeStart <= arrivalTime)
                    && (arrivalTimeEnd > arrivalTime)) {

                FlightOneWay flight = new FlightOneWay();
                flight.setDepartureFrom(flights.get(i).getFromTown());
                flight.setDepartureTo(flights.get(j).getToTown());

                flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
                flight.setFromDepartureDate(flights.get(i).getFromDate());
                flight.setFromCompany(flights.get(i).getFromCompany());
                flight.setStation("2 Stops");

                flight.setDepartureToStation1(flights.get(i).getToTown());
                flight.setDeparturefromStation1(flights.get(j).getFromTown());
                flight.setStation1ArrivalTime(flights.get(i).getFromArrivalTime());
                flight.setStation1ArrivalDate(flights.get(i).getToDate());
                flight.setStation1DepTime(flights.get(j).getFromDepartureTime());
                flight.setStation1DepDate(flights.get(j).getFromDate());


                String differHours = String.valueOf(diffHours);
                String differMinutes = String.valueOf(diffminutes);
                flight.setDuration(differHours + "h " + differMinutes + "m");
                flight.setDurationToStation1(flights.get(i).getDuration());
                flight.setWaitingTimeStation1(waitingTimeHours1 + "h " + waitingTimeMinutes1 + "m");

                flight.setAirlineToStation1(flights.get(i).getFromCompany());
                flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
                flight.setAirlineFromStation1(flights.get(j).getFromCompany());
                flight.setAirlineFromStation1imgSrc(flights.get(j).getAirlineImgSrc());


                flight.setDepartureToStation2(flights.get(j).getStationTown());
                flight.setDeparturefromStation2(flights.get(j).getStationTown());
                flight.setStation2ArrivalTime(flights.get(j).getStationArrivalTime());
                flight.setStation2ArrivalDate(flights.get(j).getStationArrivalDate());
                flight.setStation2DepTime(flights.get(j).getStationDepTime());
                flight.setStation2DepDate(flights.get(j).getStationDepDate());
                flight.setAirlineFromStation2(flights.get(j).getFromCompany());
                flight.setAirlineFromStation2imgSrc(flights.get(j).getAirlineImgSrc());
                flight.setAirlineFromStation2(flights.get(j).getFromCompany());

                long durationToStation2Format = dt4.getTime() - dt3.getTime();
                long diff2minutes = durationToStation2Format / (60 * 1000) % 60;
                long diff2Hours = durationToStation2Format / (60 * 60 * 1000);
                flight.setDurationToStation2(diff2Hours + "h " + diff2minutes + "m");
                flight.setWaitingTimeStation2(waitingTimeHours2 + "h " + waitingTimeMinutes2 + "m");

                long durationFromStation2Format = dt6.getTime() - dt5.getTime();
                long diff3minutes = durationFromStation2Format / (60 * 1000) % 60;
                long diff3Hours = durationFromStation2Format / (60 * 60 * 1000);
                flight.setDurationFromStation2(diff3Hours + "h " + diff3minutes + "m");
                flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToLastDepartureTime(flights.get(j).getToTime());
                flight.setToDepartureDate(flights.get(j).getToDate());
                finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                flight.setPrice(finalPrice);

                flightsList.add(flight);

            }


        }
    }

    private void upTo2directFlightSecond(double maxPrice,
                                         List<Flight> flights,
                                         List<FlightOneWay> flightsList,
                                         int i,
                                         int j,
                                         int flightDuration,
                                         int connectingTimeStart,
                                         int connectingTimeEnd,
                                         int departureTimeStart,
                                         int departureTimeEnd,
                                         int arrivalTimeStart,
                                         int arrivalTimeEnd) {

        if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())) {

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

            Date t1, d1, dt1 = null;
            try {
                t1 = timeFormat.parse(flights.get(i).getFromDepartureTime());
                d1 = dateFormat.parse(flights.get(i).getFromDate().substring(4));
                dt1 = combineDateTime(d1, t1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t2, d2, dt2 = null;
            try {
                t2 = timeFormat.parse(flights.get(i).getStationArrivalTime());
                d2 = dateFormat.parse(flights.get(i).getStationArrivalDate().substring(4));
                dt2 = combineDateTime(d2, t2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t3, d3, dt3 = null;
            try {
                t3 = timeFormat.parse(flights.get(i).getStationDepTime());
                d3 = dateFormat.parse(flights.get(i).getStationDepDate().substring(4));
                dt3 = combineDateTime(d3, t3);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t4, d4, dt4 = null;
            try {
                t4 = timeFormat.parse(flights.get(i).getFromArrivalTime());
                d4 = dateFormat.parse(flights.get(i).getToDate().substring(4));
                dt4 = combineDateTime(d4, t4);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t5, d5, dt5 = null;
            try {
                t5 = timeFormat.parse(flights.get(j).getFromDepartureTime());
                d5 = dateFormat.parse(flights.get(j).getFromDate().substring(4));
                dt5 = combineDateTime(d5, t5);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date t6, d6, dt6 = null;
            try {
                t6 = timeFormat.parse(flights.get(j).getFromArrivalTime());
                d6 = dateFormat.parse(flights.get(j).getToDate().substring(4));
                dt6 = combineDateTime(d6, t6);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long waitingTimeStation1 = dt3.getTime() - dt2.getTime();
            long waitingTimeMinutes1 = waitingTimeStation1 / (60 * 1000) % 60;
            long waitingTimeHours1 = waitingTimeStation1 / (60 * 60 * 1000);
            // Find the flights with 1hour differnce
            long waitingTimeStation2 = dt5.getTime() - dt4.getTime();
            long waitingTimeMinutes2 = waitingTimeStation2 / (60 * 1000) % 60;
            long waitingTimeHours2 = waitingTimeStation2 / (60 * 60 * 1000);

            long flightDurationUpTo2 = dt6.getTime() - dt1.getTime();
            long diffminutes = flightDurationUpTo2 / (60 * 1000) % 60;
            long diffHours = flightDurationUpTo2 / (60 * 60 * 1000);

            String departureTimeString=flights.get(i).getFromDepartureTime();
            int departureTime = Integer.parseInt(departureTimeString.substring(0, Math.min(departureTimeString.length(), 2)));

            String arrivalTimeString=flights.get(i).getFromArrivalTime();
            int arrivalTime = Integer.parseInt(arrivalTimeString.substring(0, Math.min(arrivalTimeString.length(), 2)));

            Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

            if ((flightDurationUpTo2>0)
                    &&(waitingTimeHours1 >= 1 || waitingTimeMinutes1 > 45)
                    && (waitingTimeHours2 >= 1 || waitingTimeMinutes2 > 45)
                    && (connectingTimeStart <= waitingTimeHours1)
                    && (connectingTimeEnd > waitingTimeHours1)
                    && (connectingTimeStart <= waitingTimeHours2)
                    && (connectingTimeEnd > waitingTimeHours2)
                    && (finalPrice < maxPrice)
                    && (diffHours < flightDuration)
                    && (departureTimeStart <= departureTime)
                    && (departureTimeEnd > departureTime)
                    && (arrivalTimeStart <= arrivalTime)
                    && (arrivalTimeEnd > arrivalTime)) {

                FlightOneWay flight = new FlightOneWay();
                flight.setDepartureFrom(flights.get(i).getFromTown());
                flight.setDepartureTo(flights.get(j).getToTown());

                flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
                flight.setFromDepartureDate(flights.get(i).getFromDate());
                flight.setFromCompany(flights.get(i).getFromCompany());
                flight.setStation("2 Stops");

                flight.setDepartureToStation1(flights.get(i).getStationTown());
                flight.setDeparturefromStation1(flights.get(i).getStationTown());
                flight.setStation1ArrivalTime(flights.get(i).getStationArrivalTime());
                flight.setStation1ArrivalDate(flights.get(i).getStationArrivalDate());
                flight.setStation1DepTime(flights.get(i).getStationDepTime());
                flight.setStation1DepDate(flights.get(i).getStationDepDate());


                String differHours = String.valueOf(diffHours);
                String differMinutes = String.valueOf(diffminutes);
                flight.setDuration(differHours + "h " + differMinutes + "m");
                long durationToStation1Format = dt2.getTime() - dt1.getTime();
                long diff2minutes = durationToStation1Format / (60 * 1000) % 60;
                long diff2Hours = durationToStation1Format / (60 * 60 * 1000);
                flight.setDurationToStation1(diff2Hours + "h " + diff2minutes + "m");
                flight.setWaitingTimeStation1(waitingTimeHours1 + "h " + waitingTimeMinutes1 + "m");

                flight.setAirlineToStation1(flights.get(i).getFromCompany());
                flight.setAirlineToStation1imgSrc(flights.get(i).getAirlineImgSrc());
                flight.setAirlineFromStation1(flights.get(i).getFromCompany());
                flight.setAirlineFromStation1imgSrc(flights.get(i).getAirlineImgSrc());


                flight.setDepartureToStation2(flights.get(i).getToTown());
                flight.setDeparturefromStation2(flights.get(j).getFromTown());
                flight.setStation2ArrivalTime(flights.get(i).getToTime());
                flight.setStation2ArrivalDate(flights.get(i).getToDate());
                flight.setStation2DepTime(flights.get(j).getFromDepartureTime());
                flight.setStation2DepDate(flights.get(j).getFromDate());
                flight.setAirlineFromStation2(flights.get(j).getFromCompany());
                flight.setAirlineFromStation2imgSrc(flights.get(j).getAirlineImgSrc());

                long durationToStation2Format = dt4.getTime() - dt3.getTime();
                long diff3minutes = durationToStation2Format / (60 * 1000) % 60;
                long diff3Hours = durationToStation2Format / (60 * 60 * 1000);
                flight.setDurationToStation2(diff3Hours + "h " + diff3minutes + "m");
                flight.setWaitingTimeStation2(waitingTimeHours2 + "h " + waitingTimeMinutes2 + "m");


                long durationFromStation2Format = dt6.getTime() - dt5.getTime();
                long diff4minutes = durationFromStation2Format / (60 * 1000) % 60;
                long diff4Hours = durationFromStation2Format / (60 * 60 * 1000);
                flight.setDurationFromStation2(diff4Hours + "h " + diff4minutes + "m");
                flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToLastDepartureTime(flights.get(j).getFromArrivalTime());
                flight.setToDepartureDate(flights.get(j).getToDate());
                finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                flight.setPrice(finalPrice);

                flightsList.add(flight);

            }
        }
    }

    private List<Flight> getFlights(String fromIata, String toIata, String checkin, String checkout, String adultNum,
                                    String childNum, String typeOfFlight, String airportSize, String tripDistance,
                                    double maxPrice, String typeOfFlightToSearch, List<Flight> flights) {
        if(typeOfFlight=="oneWay") {
            flights = this.flightRepository.
                    findDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
        }else{
            List<Flight> flightsCheckIn = this.flightRepository.
                    findDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, fromIata,
                            toIata,
                            checkin,
                            checkout,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            List<Flight> flightsCheckOut = this.flightRepository.
                    findDistinctByStationAndPriceLessThanAndFlightSearchFromIataAndFlightSearchToIataAndCheckinAndCheckoutAndAdultNumAndChildNumAndTypeOfFlightAndAirportSizeAndTripDistance(typeOfFlightToSearch, maxPrice, toIata,
                            fromIata,
                            checkout,
                            checkin,
                            adultNum,
                            childNum,
                            typeOfFlight,
                            airportSize,
                            tripDistance);
            flights.addAll(flightsCheckIn);
            flights.addAll(flightsCheckOut);
        }
        return flights;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private Date combineDateTime(Date date, Date time) {
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

        Date result = calendarA.getTime();
        return result;
    }

    public boolean flightSearched(String fromIata, String toIata, String typeOfFlight){

        List<FlightInfos> flightsInfos = this.flightInfoRepository.findByFromIataAndToIataAndTypeOfFlight(fromIata,toIata ,typeOfFlight);

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
