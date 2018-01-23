(function() {
    var module = angular.module("flightTicketApp");
    var flightController = function($scope, $routeParams, flightService) {
        var onRepo = function(data){
            $scope.flightList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        flightService.getFlights()
            .then(onRepo, onError);
    };
    module.controller("flightController", flightController);
}());