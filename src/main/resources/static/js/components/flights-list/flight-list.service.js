(function(){

    var flightListService = function($http){
        var getFlightList = function(fromIata,
                                     toIata,
                                     checkin,
                                     checkout,
                                     adultNum,
                                     childNum,
                                     typeOfFlight,
                                     airportSize,
                                     tripDistance,
                                     stops,
                                     maxPrice,
                                     flightDuration,
                                     connectingTimeStart,
                                     connectingTimeEnd,
                                     departureTimeStart,
                                     departureTimeEnd,
                                     arrivalTimeStart,
                                     arrivalTimeEnd){
            var flightUrl = "/allflights/" + fromIata
                +"/"+ toIata
                +"/"+ checkin
                +"/"+ checkout
                +"/"+ adultNum
                +"/"+ childNum
                +"/"+ typeOfFlight
                +"/"+ airportSize
                +"/"+ tripDistance
                +"/"+ stops
                +"/"+ maxPrice
                +"/"+ flightDuration
                +"/"+ connectingTimeStart
                +"/"+ connectingTimeEnd
                +"/"+ departureTimeStart
                +"/"+ departureTimeEnd
                +"/"+ arrivalTimeStart
                +"/"+ arrivalTimeEnd;
            return $http.get(flightUrl)
                .then(function(response){
                   return response.data;
                });
        };
        return {
            getFlightList: getFlightList

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("flightListService", flightListService);

}());
