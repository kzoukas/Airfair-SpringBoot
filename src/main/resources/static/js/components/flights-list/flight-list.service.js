
(function(){

    var flightListService = function($http){
        var getFlightList = function(fromIata,toIata,stops,maxPrice,flightDuration,connectingTime,departureTimeStart,departureTimeEnd,arrivalTimeStart,arrivalTimeEnd){
            var repoUrl = "/allflights/" + fromIata +"/"+ toIata +"/"+ stops +"/"+ maxPrice +"/"+ flightDuration+ "/" + connectingTime+ "/" + departureTimeStart+ "/" + departureTimeEnd+ "/" + arrivalTimeStart+ "/" + arrivalTimeEnd;
            return $http.get(repoUrl)
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
