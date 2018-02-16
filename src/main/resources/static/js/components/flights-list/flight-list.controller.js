(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("flightList", {
        templateUrl: 'js/components/flights-list/flight-list.html',
        controllerAs: "model",
        controller: flightListController
    });
    flightListController.$inject=['$scope', '$routeParams','$interval', 'flightListService'];
    function flightListController($scope, $routeParams,$interval, flightListService){

        var onRepo = function(data){
            $scope.flightList = data;
        };
        var onError = function(reason){
            $scope.error = reason;
        };
        callService();
        $interval(callService,10000);
        function callService(){
            flightListService.getFlightList()
                .then(onRepo, onError);
        }
    };
}());