(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("flightInfoBar", {
        templateUrl: 'js/components/flight-info-bar/flight-info-bar.html',
        controllerAs: "model",
        controller: flightInfoBarController
    });
    flightInfoBarController.$inject=['$scope','parameterService'];

    function flightInfoBarController($scope, parameterService){
        var model = this;
        model.params=parameterService.getter();

        $scope.fromTown=model.params.from;
        $scope.toTown=model.params.to;
        $scope.checkin=model.params.checkin;
        $scope.checkout=model.params.checkout;
        $scope.typeOfFlight=model.params.typeOfFlight;
        $scope.adultNum=model.params.adultNum;
        $scope.childNum=model.params.childNum;
        $scope.typeOfFlight=model.params.typeOfFlight;


    };
}());