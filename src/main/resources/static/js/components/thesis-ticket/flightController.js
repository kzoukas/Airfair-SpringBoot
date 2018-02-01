(function() {
    var module = angular.module("flightTicketApp");
    var flightController = function($scope, $routeParams,$interval, flightService, flightInfoService) {


        var onRepo = function(data){
            $scope.flightList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        callService();
        $interval(callService,10000);
        function callService(){
            flightService.getFlights()
                .then(onRepo, onError);
        }




        var onRepo2 = function(data){
            $scope.flightInfoList = data;
        };
        var onError2 = function(reason){
            $scope.error = reason;
        };
        flightInfoService.getFlightInfo()
            .then(onRepo2, onError2);


    };
    module.controller("flightController", flightController);
}());