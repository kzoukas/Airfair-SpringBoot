(function(){


    var module = angular.module("flightTicketApp");
    module.factory('parameterService', function () {
        var parameters = {};
        parameters.setter = function (value) {
            parameters = value;
        };
        parameters.getter = function () {
            return parameters;
        };
        return parameters;



    });
    module.factory('getFlightListFiltered', function ($http) {
        var getFlightListFiltered = function (fromIata, toIata) {
            var repoUrl = "/allflights/" + fromIata + "/" + toIata;
            return $http.get(repoUrl)
                .then(function (response) {
                    return response.data;
                });
        };
        return {
            getFlightListFiltered: getFlightListFiltered

        };
    });

}());
