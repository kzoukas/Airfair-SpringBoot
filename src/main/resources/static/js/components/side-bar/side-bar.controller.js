(function () {
    "use strict";

    var module = angular.module("flightTicketApp");
    module.component("sideBar", {
        templateUrl: 'js/components/side-bar/sidebar.html',
        controllerAs: "model",
        controller: sideBarController
    });
    sideBarController.$inject=['$scope','$location','parameterService','$route'];
    function sideBarController($scope,$location,parameterService,$route){
        var model = this;
        model.$onInit=initialize;
        model.stops=1;
        model.maxPrice=1200;
        model.flightDuration=15;
        model.connectingTime=12;
        model.departureTimeStart=0;
        model.departureTimeEnd=14;
        model.arrivalTimeStart=0;
        model.arrivalTimeEnd=24;


        $scope.filter = {
            stops:"1",
            maxPrice:1200,
            flightDuration:15,
            connectingTime:12,
            departureTimeStart:0,
            departureTimeEnd:14,
            arrivalTimeStart:0,
            arrivalTimeEnd:24

        };
        function initialize() {

        }
        $scope.submitFilters=function() {
            model.params=parameterService.getter();
            model.stops=$scope.filter.stops;
            model.maxPrice=$scope.filter.maxPrice ;
            model.flightDuration=$scope.filter.flightDuration;
            model.connectingTime=$scope.filter.connectingTime;
            model.arrivalTimeStart=$scope.filter.arrivalTimeStart;
            model.arrivalTimeEnd=$scope.filter.arrivalTimeEnd;
            console.log($scope.filter.arrivalTimeStart);
            console.log($scope.filter.arrivalTimeEnd);
            model.departureTimeStart=$scope.filter.departureTimeStart;
            //
            model.departureTimeEnd=$scope.filter.departureTimeEnd;
             console.log($scope.filter.departureTimeStart);
             console.log($scope.filter.departureTimeEnd);



            $location.path('/allflights').search({from:model.params.from,to:model.params.to,
                                                    checkIn:model.params.checkIn,typeOfFlight:model.params.typeOfFlight,
                                                    adults:model.params.adults,childs:model.params.childs,
                                                    stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration,
                                                    connectingTime:model.connectingTime,departureTimeStart:model.departureTimeStart,
                                                    departureTimeEnd:model.departureTimeEnd,arrivalTimeStart:model.arrivalTimeStart,
                                                    arrivalTimeEnd:model.arrivalTimeEnd});
        }
        model.params=parameterService.getter();
        $location.path('/allflights').search({from:model.params.from,to:model.params.to,
                                                 checkIn:model.params.checkIn,typeOfFlight:model.params.typeOfFlight,
                                                 adults:model.params.adults,childs:model.params.childs,
                                                 stops:model.stops,maxPrice:model.maxPrice,flightDuration:model.flightDuration,
                                                 connectingTime:model.connectingTime,departureTimeStart:model.departureTimeStart,
                                                 departureTimeEnd:model.departureTimeEnd,arrivalTimeStart:model.arrivalTimeStart,
                                                 arrivalTimeEnd:model.arrivalTimeEnd});};
}());