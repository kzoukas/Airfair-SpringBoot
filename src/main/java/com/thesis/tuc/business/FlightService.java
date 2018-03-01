package com.thesis.tuc.business;

import com.thesis.tuc.repository.FlightInfoRepository;
import com.thesis.tuc.repository.FlightRepository;
import com.thesis.tuc.services.rest.responseDTOs.Flight;
import com.thesis.tuc.services.rest.responseDTOs.FlightInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

import com.thesis.tuc.services.rest.responseDTOs.FlightOneWay;
import org.springframework.stereotype.Component;

import java.util.stream.*;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

@Component
public class FlightService {


    private FlightRepository flightRepository;
    private FlightInfoRepository flightInfoRepository;

    public FlightService(FlightRepository flightRepository, FlightInfoRepository flightInfoRepository) {

        this.flightRepository = flightRepository;

        this.flightInfoRepository = flightInfoRepository;
    }

    public List<FlightOneWay> getDirectFlight(String fromIata, String toIata, double maxPrice, int flightDuration) {

        String typeOfFlight = "Direct";
        List<Flight> flights = this.flightRepository.findByStationAndPriceLessThan(typeOfFlight, maxPrice);
        List<FlightOneWay> flightsList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {
            // Direct flight add to list
            searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i);
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

    public List<FlightOneWay> getFlightsOneStation(String fromIata, String toIata, double maxPrice, int flightDuration) {

        String typeOfFlight = "Direct";
        List<Flight> flights = this.flightRepository.findByStationAndPriceLessThan(typeOfFlight, maxPrice);
        List<FlightOneWay> flightsList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {

            // Direct flight add to list
            searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i);

            //Add to list all pair of flights with station and waiting time at least 1 hour so the passenger have time to change plane
            for (int j = i + 1; j < flights.size(); j++) {
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

                long flightDuration1station = d4.getTime() - d3.getTime();
                long diffminutes = flightDuration1station / (60 * 1000) % 60;
                long diffHours = flightDuration1station / (60 * 60 * 1000);

                Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

                //Add to list all pair of flights with station and waiting time at least 1 hour so the passenger have time to change plane
                if (flights.get(i).getToIata().equals(flights.get(j).getFromIata()) && (waitingTimeHours >= 1 || waitingTimeMinutes > 45) && (maxPrice > finalPrice) && (diffHours < flightDuration)) {

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
                    flight.setAirlineFromStation1(flights.get(j).getFromCompany());

                    finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                    flight.setPrice(finalPrice);

                    flightsList.add(flight);

                }
            }
        }
        // add elements to al, including duplicates
//        Set<FlightOneWay> distinctElements = new HashSet<>();
//        distinctElements.addAll(flightsTIA);
//        flightsTIA.clear();
//        flightsTIA.addAll(distinctElements);
        // Sort by price
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

    public List<FlightOneWay> getFlightsTwoStations(String fromIata, String toIata, double maxPrice, int flightDuration) {

        List<Flight> flights = this.flightRepository.findByPriceLessThan(maxPrice);
        List<FlightOneWay> flightsList = new ArrayList<>();

        for (int i = 0; i < flights.size(); i++) {

            // Direct flight add to list
            searchDirectFlights(fromIata, toIata, maxPrice, flights, flightsList, i);

            for (int j = i + 1; j < flights.size(); j++) {

                if (flights.get(i).getToIata().equals(flights.get(j).getFromIata())) {

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
                    long waitingTimeStation1 = d2.getTime() - d1.getTime();
                    long waitingTimeMinutes = waitingTimeStation1 / (60 * 1000) % 60;
                    long waitingTimeHours = waitingTimeStation1 / (60 * 60 * 1000);

                    long flightDuration1station = d4.getTime() - d3.getTime();
                    long diffminutes = flightDuration1station / (60 * 1000) % 60;
                    long diffHours = flightDuration1station / (60 * 60 * 1000);

                    Double finalPrice = flights.get(i).getPrice() + flights.get(j).getPrice();

                    if (flights.get(i).getStation().equals("Direct")&&(flights.get(j).getStation().equals("1 Stop"))&&(waitingTimeHours >= 1 || waitingTimeMinutes > 45) ) {



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
                        //flight.setDurationFromStation1(flights.get(j).getDuration());
                        String waitingHours = String.valueOf(waitingTimeHours);
                        String waitingMinutes = String.valueOf(waitingTimeMinutes);
                        flight.setWaitingTimeStation1(waitingHours + "h " + waitingMinutes + "m");
                        flight.setAirlineToStation1(flights.get(i).getFromCompany());
                        flight.setAirlineFromStation1(flights.get(j).getFromCompany());


                        flight.setDepartureToStation2(flights.get(j).getStationTown());
                        flight.setDeparturefromStation2(flights.get(j).getStationTown());
                        flight.setStation2ArrivalTime(flights.get(j).getStationArrivalTime());
                        flight.setStation2ArrivalDate(flights.get(j).getStationArrivalDate());
                        flight.setStation2DepTime(flights.get(j).getStationDepTime());
                        flight.setStation2DepDate(flights.get(j).getStationDepDate());
                        flight.setAirlineFromStation2(flights.get(j).getFromCompany());

                        Date d5 = null;
                        try {
                            d5 = format.parse(flights.get(j).getStationArrivalTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date d6 = null;
                        try {
                            d6 = format.parse(flights.get(j).getStationDepTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long durationToStation2Format = d5.getTime() - d2.getTime();
                        long diff2minutes = durationToStation2Format / (60 * 1000) % 60;
                        long diff2Hours = durationToStation2Format / (60 * 60 * 1000);
                        flight.setDurationToStation2(diff2Hours + "h " + diff2minutes + "m");

                        long waitingHoursStation2Format = d6.getTime() - d5.getTime();
                        long  waiting2Minutes= waitingHoursStation2Format / (60 * 1000) % 60;
                        long  waiting2Hours= waitingHoursStation2Format / (60 * 60 * 1000);
                        String waiting2StringHours = String.valueOf(waiting2Hours);
                        String waiting2StringMinutes = String.valueOf(waiting2Minutes);
                        flight.setWaitingTimeStation2(waiting2StringHours + "h " + waiting2StringMinutes + "m");
                        flight.setAirlineFromStation2(flights.get(j).getFromCompany());

                        long durationFromStation2Format = d4.getTime() - d6.getTime();
                        long diff3minutes = durationFromStation2Format / (60 * 1000) % 60;
                        long diff3Hours = durationFromStation2Format / (60 * 60 * 1000);
                        flight.setDurationFromStation2(diff3Hours + "h " + diff3minutes + "m");
                        flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
                        flight.setToDepartureDate(flights.get(j).getToDate());
                        finalPrice = Math.round(finalPrice * 100.0) / 100.0;
                        flight.setPrice(finalPrice);

                        flightsList.add(flight);

                    }


                }
            }


            //Add to list all pair of flights with station and waiting time at least 1 hour so the passenger have time to change plane
//            for (int j = i+1; j < flights.size(); j++) {
//                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//                String time1= flights.get(i).getFromArrivalTime();
//                Date d1 = null;
//                try {
//                    d1 = format.parse(time1);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Date d2 = null;
//                try {
//                    d2 = format.parse(flights.get(j).getFromDepartureTime());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Date d3 = null;
//                try {
//                    d3 = format.parse(flights.get(i).getFromDepartureTime());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Date d4 = null;
//                try {
//                    d4 = format.parse(flights.get(j).getFromArrivalTime());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                // Find the flights with 1hour differnce
//                long waitingTime = d2.getTime() - d1.getTime();
//                long waitingTimeMinutes=waitingTime / (60 * 1000) % 60;
//                long waitingTimeHours = waitingTime / (60 * 60 * 1000);
//
//                long flightDuration1station =d4.getTime() - d3.getTime();
//                long diffminutes = flightDuration1station / (60 * 1000) % 60;
//                long diffHours = flightDuration1station / (60 * 60 * 1000);
//
//                Double finalPrice=flights.get(i).getPrice()+flights.get(j).getPrice();
//
//                //Add to list all pair of flights with station and waiting time at least 1 hour so the passenger have time to change plane
//                if (flights.get(i).getToIata().equals(flights.get(j).getFromIata()) && (waitingTimeHours>=1||waitingTimeMinutes>45) && (maxPrice>finalPrice) && (diffHours<flightDuration)){
//
//                    FlightOneWay flight = new FlightOneWay();
//                    flight.setDepartureFrom(flights.get(i).getFromTown());
//                    flight.setDepartureTo(flights.get(j).getToTown());
//
//                    flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
//                    flight.setFromDepartureDate(flights.get(i).getFromDate());
//                    flight.setStation1ArrivalTime(flights.get(i).getFromArrivalTime());
//                    flight.setStation1ArrivalDate(flights.get(i).getToDate());
//                    flight.setStation1DepTime(flights.get(j).getFromDepartureTime());
//                    flight.setStation1DepDate(flights.get(j).getFromDate());
//                    flight.setToDepartureTime(flights.get(j).getFromArrivalTime());
//                    flight.setToDepartureDate(flights.get(j).getToDate());
//
//                    flight.setDepartureToStation1(flights.get(i).getToTown());
//                    flight.setDeparturefromStation1(flights.get(j).getFromTown());
//
//                    String differHours= String.valueOf(diffHours);
//                    String differMinutes= String.valueOf(diffminutes);
//                    flight.setDuration(differHours+"h "+ differMinutes+"m");
//                    flight.setDurationToStation1(flights.get(i).getDuration());
//                    flight.setDurationFromStation1(flights.get(j).getDuration());
//                    flight.setStation("1 Stop");
//
//                    String waitingHours= String.valueOf(waitingTimeHours);
//                    String waitingMinutes= String.valueOf(waitingTimeMinutes);
//                    flight.setWaitingTimeStation1(waitingHours+"h "+ waitingMinutes+"m");
//
//                    flight.setFromCompany(flights.get(i).getFromCompany());
//                    flight.setAirlineToStation1(flights.get(i).getFromCompany());
//                    flight.setAirlineFromStation1(flights.get(j).getFromCompany());
//
//                    finalPrice= Math.round(finalPrice*100.0)/100.0;
//                    flight.setPrice(finalPrice);
//
//                    flightsList.add(flight);
//
//                }
//            }
        }
        // add elements to al, including duplicates
//        Set<FlightOneWay> distinctElements = new HashSet<>();
//        distinctElements.addAll(flightsTIA);
//        flightsTIA.clear();
//        flightsTIA.addAll(distinctElements);
        // Sort by price
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


    private void searchDirectFlights(String fromIata, String toIata, double maxPrice, List<Flight> flights, List<FlightOneWay> flightsList, int i) {
        if (flights.get(i).getFromIata().equals(fromIata) && (flights.get(i).getToIata().equals(toIata) && (flights.get(i).getStation().equals("Direct")) && (maxPrice > flights.get(i).getPrice()))) {

            FlightOneWay flight = new FlightOneWay();
            flight.setDepartureFrom(flights.get(i).getFromTown());
            flight.setDepartureTo(flights.get(i).getToTown());
            flight.setFromDepartureTime(flights.get(i).getFromDepartureTime());
            flight.setToDepartureTime(flights.get(i).getFromArrivalTime());
            flight.setDuration(flights.get(i).getDuration());
            flight.setStation("Direct");
            flight.setFromDepartureDate(flights.get(i).getFromDate());
            flight.setFromCompany(flights.get(i).getFromCompany());
            flight.setPrice(flights.get(i).getPrice());

            flightsList.add(flight);
        }
    }


    public List<FlightInfo> getAllFlightInfo() {
        List<FlightInfo> flights2 = this.flightInfoRepository.findAll();

        return flights2;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
