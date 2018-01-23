(function(){
    var module = angular.module("thesisApp");
    module.factory('FlightTicket',['$resource', function ($resource){
        return $resource('flight',{},
            {

            }
        );
    }]);
    module.factory('CustomFlightTicket',['$resource', function ($resource){
        return $resource('customFlight',{},
            {

            }
        );
    }]);
    module.factory('AllFlights',['$resource', function ($resource){
        return $resource('allFlights',{},
            {

            }
        );
    }]);

}());


