(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("flightInfoBar", {
        templateUrl: 'js/components/flight-info-bar/flight-info-bar.html',
        controllerAs: "model",
        controller: flightInfoBarController
    });
    flightInfoBarController.$inject=['$scope','flightInfoService'];

    function flightInfoBarController($scope, flightInfoService){

        var onRepo = function(data){
            $scope.flightInfoList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        flightInfoService.getFlightInfo()
            .then(onRepo, onError);

    };
}());