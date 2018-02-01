(function() {
    var module = angular.module("flightTicketApp");
    var flightInfoController = function($scope, $routeParams, flightInfoService) {



        var onRepo = function(data){
            $scope.flightInfoList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        flightInfoService.getFlightInfo()
            .then(onRepo, onError);



    };
    module.controller("flightInfoController", flightInfoController);
}());