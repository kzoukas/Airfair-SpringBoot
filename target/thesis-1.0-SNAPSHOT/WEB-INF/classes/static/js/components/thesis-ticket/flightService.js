(function(){

    var flightService = function($http){
        var getFlights = function(){
            var repoUrl = "/allflights";
            return $http.get(repoUrl)
                .then(function(response){
                   return response.data;
                });
        };
        return {
            getFlights: getFlights

        };
    };
    var module = angular.module("flightTicketApp");
    module.factory("flightService", flightService);

}());
