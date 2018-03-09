
(function(){

    var flightInfoService = function($http){
        var getFlightInfo = function(fromIata,toIata,typeOfFlight){
            var repoUrl = "/allInfos/" + fromIata +"/"+ toIata +"/"+ typeOfFlight;
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
