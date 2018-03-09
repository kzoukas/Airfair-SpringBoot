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
}());
