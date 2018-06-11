
(function(){

    var flightInfoService = function($http){
        var getFlightInfo = function(fromIata,
                                     toIata ,
                                     checkin,
                                     checkout,
                                     adultNum,
                                     childNum,
                                     typeOfFlight,
                                     airportSize,
                                     tripDistance){
            var repoUrl = "/allInfos/" + fromIata
                +"/"+ toIata
                +"/"+ checkin
                +"/"+ checkout
                +"/"+ adultNum
                +"/"+ childNum
                +"/"+ typeOfFlight
                +"/"+ airportSize
                +"/"+ tripDistance;
            return $http.get(repoUrl)
                .then(function(response){
                    return response.data;
                });
        };
        return {
            getFlightInfo: getFlightInfo

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("flightInfoService", flightInfoService);

}());
